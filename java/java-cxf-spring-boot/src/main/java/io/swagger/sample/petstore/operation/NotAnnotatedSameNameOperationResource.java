package io.swagger.sample.petstore.operation;

import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Resource With a Default Operation without Annotation
 */
@Service
@Path("/operations")
public class NotAnnotatedSameNameOperationResource {
    @Path("/notannotatedoperation")
    @GET
    public String getUser() {
        return new String();
    }

    @Path("/notannotatedoperationduplicated")
    @GET
    public String getUser(final String id) {
        return new String();
    }
}
