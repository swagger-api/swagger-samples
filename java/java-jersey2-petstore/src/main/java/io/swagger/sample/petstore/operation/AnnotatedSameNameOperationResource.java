package io.swagger.sample.petstore.operation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Resource With a Default Operation without Annotation
 */
@Path("/operations")
public class AnnotatedSameNameOperationResource {
    @Path("/sameOperationName")
    @GET
    @Operation(description = "Same Operation Name", responses = {
            @ApiResponse(responseCode = "405", description = "Invalid input")
    })
    public Response getUser() {
        return Response.ok().entity("SUCCESS").build();
    }

    @Path("/sameOperationName")
    @DELETE
    @Operation(description = "Same Operation Name Duplicated", responses = {
            @ApiResponse(responseCode = "405", description = "Invalid input")
    })
    public Response getUser(final String id) {
        return Response.ok().entity("SUCCESS").build();
    }
}
