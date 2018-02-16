package io.swagger.sample.petstore.operation;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Resource With a Default Operation without Annotation
 */
@Path("/operations")
public class OperationWithoutAnnotationResource {
    @Path("/operationwithouannotation")
    @GET
    public String getUser() {
        return new String();
    }
}
