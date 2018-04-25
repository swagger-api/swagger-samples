package io.swagger.sample.petstore.operation;

import io.swagger.sample.model.Pet;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 * Resource with Operations Examples
 */
@Service
@Path("/operations")
public class ExternalDocumentationResource {
    @GET
    @Path("externaldocumentation/{petIdentification}")
    @Operation(summary = "Find pet by ID",
            description = "Returns a pet when 0 < ID <= 10.  ID > 10 or non integers will simulate API error conditions",
            operationId = "petId",
            externalDocs = @ExternalDocumentation(description = "External in Operation", url = "http://url.me"))
    @ExternalDocumentation(description = "External Annotation Documentation", url = "http://url.me")
    public Response getPetById(
            @Parameter(description = "ID of pet that needs to be fetched", required = true)
            @PathParam("petIdentification")final Long getPetIdentification) throws NotFoundException {
        return Response.ok().entity(new Pet()).build();
    }
}
