package io.openliberty.guides.resources;

import io.openliberty.guides.models.Users;
import io.openliberty.guides.repositories.UsersRepository;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.JsonWebToken;

import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import jakarta.inject.Inject;
import jakarta.json.JsonArray;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/users")
public class UserResource {

    /*@Inject
    UserService userService;

    @Inject
    @Claim("groups")
    private JsonArray roles;*/

    /*@Inject
    private JsonWebToken jwt;*/
    @Inject
    UsersRepository usersRepository;
    // GET: Obtener todos los usuarios
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    //@RolesAllowed({ "admin" })
    public Response getUsers(@Context HttpHeaders headers) {
        return Response.ok(usersRepository.findAll().toList()).build();
    }

    // GET: Obtener un usuario por ID
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    //@RolesAllowed({ "admin" })
    public Response getUserById(@PathParam("id") String id) {
        Optional<Users> user = usersRepository.findById(id);
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
    // @RolesAllowed({ "admin" })
    public Response createUser(Users user) {
        usersRepository.save(user);
        return Response.status(Response.Status.CREATED)
                .entity("Usuario creado: " + user.getEmail())
                .build();
    }

    // PUT: Actualizar un usuario por correo electrónico
    @PUT
    @Path("/{email}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    // @RolesAllowed({ "admin" })
    public Response updateUser(@PathParam("email") String email, Users updatedUser) {
        try {
            Users existingUser = usersRepository.findByEmail(email);
            if (existingUser != null) {
                // Mantener el ID original
                updatedUser.setId(existingUser.getId());
                
                // Actualizar los campos
                existingUser.setName(updatedUser.getName());
                existingUser.setEmail(updatedUser.getEmail());
                existingUser.setProvider(updatedUser.getProvider());
                existingUser.setRole(updatedUser.getRole());
                
                // Guardar los cambios
                usersRepository.save(existingUser);
                
                return Response.ok(existingUser).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                    .entity("Usuario no encontrado con email: " + email)
                    .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Error al actualizar usuario: " + e.getMessage())
                .build();
        }
    }

    // DELETE: Eliminar un usuario por correo electrónico
    @DELETE
    @Path("/{email}")
    @RolesAllowed({ "admin" })
    public Response deleteUserByEmail(@PathParam("email") String email) {
        Users user = usersRepository.findByEmail(email);
        if (user != null) {
            usersRepository.delete(user);
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
        Optional<Users> user = usersRepository.findById(id);        
        if (user != null) {
            usersRepository.delete(user.get());
            return Response.ok("Usuario eliminado por ID: " + id).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Usuario no encontrado con ID: " + id)
                    .build();
        }
    }
}
