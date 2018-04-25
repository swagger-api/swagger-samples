package io.swagger.sample.petstore.tags;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Service
public class TagMethodResource {
    @GET
    @Path("/tagsinmethod")
    @Tag(name = "Third Tag")
    @Tag(name = "Second Tag")
    @Tag(name = "Fourth Tag Full", description = "desc", externalDocs = @ExternalDocumentation(description = "docs desc"))
    public Response getTags() {
        return Response.ok().entity("ok").build();
    }
}
