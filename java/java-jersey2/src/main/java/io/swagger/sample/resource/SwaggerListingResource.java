package io.swagger.sample.resource;

import io.swagger.oas.models.OpenAPI;
import io.swagger.util.Yaml;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletConfig;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/swagger.{type:json|yaml}")
public class SwaggerListingResource {
    @Context
    ServletConfig config;

    @GET
    @Produces({MediaType.APPLICATION_JSON, "application/yaml"})
    public Response getSwagger(@PathParam("type") String type) throws Exception {
        OpenAPI oas = (OpenAPI) config.getServletContext().getAttribute("oas");
        if (StringUtils.isNotBlank(type) && type.trim().equalsIgnoreCase("yaml")) {
            return Response.status(Response.Status.OK)
                    .entity(Yaml.mapper().writeValueAsString(oas))
                    .type("application/yaml")
                    .build();
        } else {
            return Response.status(Response.Status.OK)
                    .entity(oas)
                    .type(MediaType.APPLICATION_JSON_TYPE)
                    .build();
        }
    }
}
