package io.swagger.sample.petstore.link;

import io.swagger.sample.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.links.Link;
import io.swagger.v3.oas.annotations.links.LinkParameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

/**
 * Class with Links
 */
@Service
@Path("/links")
public class LinksResource {
    @Operation(operationId = "getUserWithAddress",
            responses = {
                    @ApiResponse(description = "test description",
                            content = @Content(mediaType = "*/*", schema = @Schema(ref = "#/components/schemas/User")),
                            links = {
                                    @Link(
                                            name = "address",
                                            operationId = "getAddress",
                                            parameters = @LinkParameter(
                                                    name = "userId",
                                                    expression = "$request.query.userId")),
                                    @Link(
                                            name = "user",
                                            operationId = "getUser",
                                            operationRef = "#/components/links/MyLink",
                                            parameters = @LinkParameter(
                                                    name = "userId",
                                                    expression = "$request.query.userId"))
                            })}
    )
    @GET
    public User getUser(@QueryParam("userId")final String userId) {
        return null;
    }

}
