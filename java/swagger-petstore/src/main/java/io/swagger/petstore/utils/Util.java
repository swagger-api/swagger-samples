package io.swagger.petstore.utils;

import io.swagger.oas.inflector.models.RequestContext;

import javax.ws.rs.core.MediaType;

public class Util {

    private static final String APPLICATION = "application";
    private static final String YAML = "yaml";

    public static MediaType getMediaType(final RequestContext request) {
        MediaType outputType = MediaType.APPLICATION_JSON_TYPE;

        if (request.getMediaType().equals(MediaType.APPLICATION_ATOM_XML_TYPE)) {
            return request.getMediaType();
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
