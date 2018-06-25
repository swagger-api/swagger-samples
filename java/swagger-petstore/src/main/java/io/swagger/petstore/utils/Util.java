package io.swagger.petstore.utils;

import io.swagger.oas.inflector.models.RequestContext;

import javax.ws.rs.core.MediaType;

public class Util {

    public static MediaType getMediaType(RequestContext request) {
        MediaType outputType = MediaType.APPLICATION_JSON_TYPE;

        boolean isJsonOK = false;
        boolean isYamlOK = false;

        final MediaType yamlMediaType = new MediaType("application", "yaml");

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
