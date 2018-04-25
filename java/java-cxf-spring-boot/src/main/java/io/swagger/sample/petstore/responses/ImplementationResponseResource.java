package io.swagger.sample.petstore.responses;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Resource with a Response at Method Level
 */
@Service
@Path("/responses")
public class ImplementationResponseResource {
    @GET
    @Path("/implementationresponse")
    @Operation(
            summary = "Simple get operation",
            description = "Defines a simple get operation with no inputs and a complex output object",
            operationId = "getWithPayloadResponse",
            deprecated = true,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "voila!",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema =
                                            @Schema(
                                                    implementation = SampleResponseSchema.class)
                                    ),
                                    @Content(
                                            mediaType = "application/json",
                                            schema =
                                            @Schema(
                                                    implementation = SecondSampleResponseSchema.class)
                                    )
                            }

                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "boo",
                            content = @Content(
                                    mediaType = "*/*",
                                    schema =
                                    @Schema(implementation = GenericError.class)
                            )
                    )
            }
    )

    public void getResponses() {
    }

    static class SampleResponseSchema {
        @Schema(description = "the user id")
        private String id;
    }

    static class SecondSampleResponseSchema {
        @Schema(description = "the user id")
        private String id;
    }

    static class GenericError {
        private int code;
        private String message;
    }
}
