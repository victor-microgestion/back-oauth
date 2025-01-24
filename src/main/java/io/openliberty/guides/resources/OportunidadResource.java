package io.openliberty.guides.resources;

import io.openliberty.guides.models.Oportunidad;
import io.openliberty.guides.services.OportunidadService;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/oportunidades")
public class OportunidadResource {

    @Inject
    OportunidadService oportunidadService;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({ "ejecutivo" })
    public Response getAllOportunidades() {
        List<Oportunidad> oportunidades = oportunidadService.getOportunidades();
        return Response.ok(oportunidades).build();
    }

    @GET
    @Path("/{codigo}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({ "ejecutivo" })
    public Response getOportunidadByCodigo(@PathParam("codigo") String codigo) {
        Oportunidad oportunidad = oportunidadService.getOportunidadByCodigo(codigo);
        if (oportunidad != null) {
            return Response.ok(oportunidad).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Oportunidad no encontrada con código: " + codigo)
                    .build();
        }
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({ "ejecutivo" })
    public Response createOportunidad(Oportunidad oportunidad) {
        oportunidadService.saveOportunidad(oportunidad);
        return Response.status(Response.Status.CREATED)
                .entity("Oportunidad creada con código: " + oportunidad.getCodigo())
                .build();
    }

    @PUT
    @Path("/{codigo}")
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({ "ejecutivo" })
    public Response updateOportunidad(@PathParam("codigo") String codigo, Oportunidad updatedOportunidad) {
        boolean isUpdated = oportunidadService.updateOportunidad(codigo, updatedOportunidad);
        if (isUpdated) {
            return Response.ok("Oportunidad actualizada: " + codigo).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Oportunidad no encontrada con código: " + codigo)
                    .build();
        }
    }

    @DELETE
    @Path("/{codigo}")
    @RolesAllowed({ "ejecutivo" })
    public Response deleteOportunidad(@PathParam("codigo") String codigo) {
        boolean isDeleted = oportunidadService.deleteOportunidadByCodigo(codigo);
        if (isDeleted) {
            return Response.ok("Oportunidad eliminada con código: " + codigo).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Oportunidad no encontrada con código: " + codigo)
                    .build();
        }
    }
}
