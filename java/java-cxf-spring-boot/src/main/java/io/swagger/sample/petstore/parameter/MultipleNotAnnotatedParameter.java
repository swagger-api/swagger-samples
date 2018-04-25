package io.swagger.sample.petstore.parameter;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.stereotype.Service;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * Class with a multiple not annotated parameter.
 */
@Service
public class MultipleNotAnnotatedParameter {
    @POST
    @Path("/multiplenoannotatedparameter")
    @Operation(operationId = "create User")
    public void createUser(final String id, final String name) {

    }
}
