package io.swagger.sample.petstore.operation;

import io.swagger.sample.model.Pet;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.servers.Server;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Resource With a Hidden Operation
 */
@OpenAPIDefinition(
)
@Path("/operations")
public class ServerOperationResource {
    @Path("/serversoperation")
    @GET
    @Operation(operationId = "Pets", description = "Pets Example")
    public Pet getPet() {
        return new Pet();
    }
}
