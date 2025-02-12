package io.openliberty.guides.resources;

import io.openliberty.guides.models.User;
import io.openliberty.guides.repositories.UserRepository;
import io.openliberty.guides.services.UserService;

import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.JsonWebToken;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.json.JsonArray;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/users")
public class UserResource {

    @Inject
    UserService userService;

    /*@Inject
    @Claim("groups")
    private JsonArray roles;*/

    /*@Inject
    private JsonWebToken jwt;*/
    @Inject
    UserRepository userRepository;
    // GET: Obtener todos los usuarios
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    //@RolesAllowed({ "admin" })
    public Response getUsers(@Context HttpHeaders headers) {
      //  System.out.println("Roles: " + roles);
        //System.out.println("JWT: " + jwt);
        //System.out.println(userRepository.findAll());
        return Response.ok(userRepository.findByEmail("victor_villafane@microgestion.com")).build();
    }

    // GET: Obtener un usuario por ID
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({ "admin" })
    public Response getUserById(@PathParam("id") String id) {
        User user = userService.getUserById(id);
        if (user != null) {
            return Response.ok(user).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Usuario no encontrado con ID: " + id)
                    .build();
        }
    }

    // POST: Crear un nuevo usuario
    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({ "admin" })
    public Response createUser(User user) {
        userService.saveUser(user);
        return Response.status(Response.Status.CREATED)
                .entity("Usuario creado: " + user.getEmail())
                .build();
    }

    // PUT: Actualizar un usuario por correo electrónico
    @PUT
    @Path("/{email}")
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({ "admin" })
    public Response updateUser(@PathParam("email") String email, User updatedUser) {
        boolean isUpdated = userService.updateUser(email, updatedUser);
        if (isUpdated) {
            return Response.ok("Usuario actualizado: " + email).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Usuario no encontrado con email: " + email)
                    .build();
        }
    }

    // DELETE: Eliminar un usuario por correo electrónico
    @DELETE
    @Path("/{email}")
    @RolesAllowed({ "admin" })
    public Response deleteUserByEmail(@PathParam("email") String email) {
        boolean isDeleted = userService.deleteUserByEmail(email);
        if (isDeleted) {
            return Response.ok("Usuario eliminado: " + email).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Usuario no encontrado con email: " + email)
                    .build();
        }
    }

    // DELETE: Eliminar un usuario por ID
    @DELETE
    @Path("/id/{id}")
    @RolesAllowed({ "admin" })
    public Response deleteUserById(@PathParam("id") String id) {
        boolean isDeleted = userService.deleteUserById(id);
        if (isDeleted) {
            return Response.ok("Usuario eliminado por ID: " + id).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Usuario no encontrado con ID: " + id)
                    .build();
        }
    }
}
