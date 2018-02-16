package io.swagger.sample.petstore.responses;

import io.swagger.sample.model.Pet;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Resource with a Response at Method Level
 */
@Path("/responses")
public class MethodResponseResource {
    @GET
    @Path("/responseinmethod")
    @Operation(summary = "Find pets",
            description = "Returns the Pets")
    @ApiResponse(responseCode = "200", description = "Status OK")
    public Response getPets() throws NotFoundException {
        return Response.ok().entity(new Pet()).build();
    }
}
