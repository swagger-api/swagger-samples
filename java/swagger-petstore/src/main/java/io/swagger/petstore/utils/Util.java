package io.swagger.petstore.utils;

import io.swagger.oas.inflector.models.RequestContext;

import javax.ws.rs.core.MediaType;
import java.util.List;

public class Util {

    private static final String APPLICATION = "application";
    private static final String YAML = "yaml";

    public static MediaType getMediaType(final RequestContext request) {
        MediaType outputType = MediaType.APPLICATION_JSON_TYPE;

        final List<String> accept = request.getHeaders().get("Accept");
        String responseMediaType = "";
        if (accept != null && accept.get(0) != null) {
            responseMediaType = accept.get(0);
        }
        if (MediaType.APPLICATION_XML.equals(responseMediaType)) {
            return MediaType.APPLICATION_XML_TYPE;
        }

        boolean isJsonOK = false;
        boolean isYamlOK = false;

        final MediaType yamlMediaType = new MediaType(APPLICATION, YAML);

        for (final MediaType mediaType : request.getAcceptableMediaTypes()) {
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
