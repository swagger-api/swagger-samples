package io.swagger.handler;

import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.oas.inflector.models.RequestContext;
import io.swagger.oas.inflector.models.ResponseContext;
import io.swagger.parser.OpenAPIParser;
import io.swagger.util.Json;
import io.swagger.v3.parser.core.models.SwaggerParseResult;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaInflectorServerCodegen", date = "2017-04-08T15:48:56.501Z")
public class ConverterController {
    public ResponseContext convertByContent(RequestContext request, JsonNode inputSpec) {
        if(inputSpec == null) {
            return new ResponseContext()
                    .status(Response.Status.BAD_REQUEST)
                    .entity( "No specification supplied in either the url or request body.  Try again?" );
            }
        String inputAsString = Json.pretty(inputSpec);
        SwaggerParseResult output = new OpenAPIParser().readContents(inputAsString, null, null);
        if(output == null) {
            return new ResponseContext().status(Response.Status.INTERNAL_SERVER_ERROR).entity( "Failed to process URL" );
        }
        if(output.getOpenAPI() == null) {
            return new ResponseContext().status(Response.Status.BAD_REQUEST).entity(output.getMessages());
        }


        MediaType outputType = getMediaType(request);
        
        return new ResponseContext()
                .contentType(outputType)
                .entity(output.getOpenAPI());
    }

    public ResponseContext convertByUrl(RequestContext request , String url) {
        if(url == null) {
            return new ResponseContext()
                    .status(Response.Status.BAD_REQUEST)
                    .entity( "No specification supplied in either the url or request body.  Try again?" );
        }
        SwaggerParseResult output = new OpenAPIParser().readLocation(url, null, null);
        if(output == null) {
            return new ResponseContext().status(Response.Status.INTERNAL_SERVER_ERROR).entity( "Failed to process URL" );
        }
        if(output.getOpenAPI() == null) {
            return new ResponseContext().status(Response.Status.BAD_REQUEST).entity(output.getMessages());
        }

        MediaType outputType = getMediaType(request);

        return new ResponseContext()
                .contentType(outputType)
                .entity(output.getOpenAPI());
    }

    private MediaType getMediaType(RequestContext request) {
        MediaType outputType = MediaType.APPLICATION_JSON_TYPE;

        boolean isJsonOK = false;
        boolean isYamlOK = false;

        MediaType yamlMediaType = new MediaType("application", "yaml");

        for (MediaType mediaType : request.getAcceptableMediaTypes()) {
            if (mediaType.equals(MediaType.APPLICATION_JSON_TYPE)) {
                isJsonOK = true;
            } else if (mediaType.equals(yamlMediaType)) {
                isYamlOK = true;
            }
        }

        if (isYamlOK && !isJsonOK) {
            outputType = yamlMediaType;
        }

        return outputType;
    }
}

