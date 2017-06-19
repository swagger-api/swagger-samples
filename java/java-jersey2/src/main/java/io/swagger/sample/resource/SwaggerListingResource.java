package io.swagger.sample.resource;

import io.swagger.oas.models.OpenAPI;

import javax.servlet.ServletConfig;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

@Path("/swagger")
public class SwaggerListingResource {
    @Context
    ServletConfig config;

    @GET
    @Produces("application/json")
    public Response getSwagger(/*@PathParam("type") String type*/) {
        OpenAPI oas = (OpenAPI)config.getServletContext().getAttribute("oas");
        return Response.status(Response.Status.OK)
                .entity(oas)
                .build();
    }
}
