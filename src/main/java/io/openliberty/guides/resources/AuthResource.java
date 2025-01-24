package io.openliberty.guides.resources;

import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import io.openliberty.guides.models.User;
import io.openliberty.guides.providers.GoogleOAuthProvider;
import io.openliberty.guides.providers.OAuthProvider;
import io.openliberty.guides.services.LogoutService;
import io.openliberty.guides.services.OAuthService;
import io.openliberty.guides.services.UserService;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.ibm.websphere.security.jwt.JwtBuilder;
import com.ibm.websphere.security.jwt.Claims;

@Path("/oauth")
public class AuthResource {

    private final LogoutService logoutService = new LogoutService();

    @Inject
    UserService userService;

    @GET
    @Path("/logout")
    public Response logout(@QueryParam("provider") String provider) {
        String logoutUrl = logoutService.performLogout(provider);
        // Redirige al usuario al endpoint de logout
        return Response.seeOther(URI.create(logoutUrl)).build();
    }

    @POST
    @Path("/exchange-code")
    @Consumes(MediaType.APPLICATION_JSON) // Acepta JSON
    @Produces(MediaType.APPLICATION_JSON) // Devuelve JSON
    @PermitAll
    public Response exchangeCode(Map<String, String> payload) {
        String code = payload.get("code"); // Asegúrate de que "code" sea enviado correctamente
        OAuthProvider provider = new GoogleOAuthProvider();
        OAuthService oauthService = new OAuthService(provider);
        try {
            // Intercambio del código por tokens usando Google API
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://oauth2.googleapis.com/token"))
                    .header("Content-Type", "application/x-www-form-urlencoded") // Tipo esperado por Google API
                    .POST(HttpRequest.BodyPublishers.ofString(
                            "code=" + URLEncoder.encode(code, StandardCharsets.UTF_8) +
                                    "&client_id=CLIENTE"
                                    +
                                    "&client_secret=SECRETO" +
                                    "&redirect_uri=http://localhost:5173" +
                                    "&grant_type=authorization_code"))
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            // Parsear la respuesta de Google
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> tokenResponse = objectMapper.readValue(response.body(), Map.class);
            Map<String, Object> userInfo = oauthService.getUserInfo(tokenResponse.get("access_token").toString());

            String email = (String) userInfo.get("email");

            // Verificar si el usuario ya existe y obtenerlo si es así
            User user = userService.userExists(email);
            if (user == null) {
                // Crear un objeto User con los nuevos atributos
                user = new User(
                        (String) userInfo.get("name"), // Nombre del usuario
                        email, // Email del usuario
                        "google", // Proveedor (hardcoded como "google")
                        "user" // Rol por defecto ("user" en este caso)
                );

                // Guardar el usuario en la base de datos
                userService.saveUser(user);
                System.out.println("Nuevo usuario creado: " + user);
            } else {
                System.out.println("El usuario ya existe en la base de datos: " + email);
            }

            // Generar el JWT con el rol del usuario
            Set<String> roles = new HashSet<>();
            roles.add(user.getRole()); // Agregar el rol del usuario al token
            String jwt = buildJwt(email, roles);
            System.out.println("JWT generado: " + jwt);

            // Agregar el token a la respuesta
            userInfo.put("token", jwt);

            // Devuelve el JSON automáticamente serializado
            return Response.ok(userInfo).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    private String buildJwt(String userName, Set<String> roles) throws Exception {
        // tag::jwtBuilder[]
        return JwtBuilder.create("jwtFrontEndBuilder")
                // end::jwtBuilder[]
                .claim(Claims.SUBJECT, userName)
                .claim("upn", userName)
                // tag::claim[]
                .claim("groups", roles.toArray(new String[roles.size()]))
                .claim("aud", "systemService")
                // end::claim[]
                .buildJwt()
                .compact();

    }
    // end::buildJwt[]
}