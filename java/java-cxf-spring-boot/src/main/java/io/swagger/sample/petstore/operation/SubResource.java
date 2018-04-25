package io.swagger.sample.petstore.operation;

import io.swagger.sample.model.Pet;
import io.swagger.v3.oas.annotations.Operation;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * SubResource
 */
public class SubResource implements InterfaceResource {
    @Override
    @Operation(summary = "Find pet by ID Operation in SubResource",
            description = "Returns a pet in SubResource",
            operationId = "OverridenResource"
    )
    public Response getPetByIde(final Long petId) {
        return Response.ok().entity(new Pet()).build();
    }
}
