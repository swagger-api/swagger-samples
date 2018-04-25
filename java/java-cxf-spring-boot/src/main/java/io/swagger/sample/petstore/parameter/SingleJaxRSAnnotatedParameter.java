package io.swagger.sample.petstore.parameter;

import io.swagger.sample.model.User;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

/**
 * Class with a single parameter annotated with jaxrs.
 */
@Service
@Path("/parameter")
public class SingleJaxRSAnnotatedParameter {
    @GET
    @Path("/singlejaxrsannotatedparameter")
    @Operation(operationId = "create User")
    public User findUser(@QueryParam("id") final String id) {
        return new User();
    }
}
