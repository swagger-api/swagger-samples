package io.swagger.sample.petstore.operation;

import io.swagger.sample.model.Pet;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 * Resource with Complete Operations Examples
 */
@Service
@Path("/operation")
public class OperationResource implements InterfaceResource {
    @Override
    @Operation(summary = "Find pet by ID Operation in SubResource",
            description = "Returns a pet in SubResource"
    )
    public Response getPetByIde(final Long petIde) {
        return Response.ok().entity(new Pet()).build();
    }

    @GET
    @Path("/operationsresource")
    @Operation(summary = "Find pet by ID",
            description = "combinatedfullyannotatedoperation/{petIde}",
            operationId = "petIde",
            responses = {
                    @ApiResponse(
                            description = "The pet", content = @Content(
                            schema = @Schema(implementation = Pet.class)
                    )),
                    @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
                    @ApiResponse(responseCode = "404", description = "Pet not found")
            })
    public Response getPetByIde(
            @Parameter(description = "ID of pet that needs to be fetched", required = true)
            @QueryParam("petIde") final Long petIde, final String message) throws NotFoundException {
        return Response.ok().entity(new Pet()).build();
    }

    @Path("/operationsresource")
    @POST
    public String getUser(final String id) {
        return new String();
    }

    @Path("/operationsresource")
    @PUT
    @Operation(operationId = "combinated sameOperationName",
            description = "combinatedsameOperationName")
    public String getPerson() {
        return new String();
    }

    @Path("/operationsresource")
    @HEAD
    @Operation(operationId = "combinatedsameOperationNameDuplicated",
            description = "combinatedsameOperationNameDuplicated")
    public String getPerson(final String id) {
        return new String();
    }

    @Path("/operationsresource2")
    @GET
    public String getUser() {
        return new String();
    }
}
