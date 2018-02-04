package io.swagger.sample.petstore.responses;

import io.swagger.sample.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Resource with the response in the Operation Annotation
 */
@Path("/responses")
public class OperationResponseResource {
    @GET
    @Path("/responseinoperation")
    @Operation(summary = "Find Users",
            description = "Returns the Users",
            responses = {@ApiResponse(responseCode = "200", description = "Status OK")})
    public Response getUsers() throws NotFoundException {
        return Response.ok().entity(new User()).build();
    }

}
