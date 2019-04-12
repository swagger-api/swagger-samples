package io.swagger.petstore.utils;

import io.swagger.oas.inflector.config.OpenAPIProcessor;
import io.swagger.v3.core.util.Yaml;
import io.swagger.v3.oas.models.OpenAPI;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.net.URL;

public class HandleAuthUrlProcessor implements OpenAPIProcessor {

    @Override
    public void process(OpenAPI openAPI) {
        OpenAPI petstoreOpenAPI = null;
        try {
            // try to load from resources
            URL url = HandleAuthUrlProcessor.class.getClassLoader().getResource("openapi-full.yaml");
            if(url != null) {
                try {
                    petstoreOpenAPI = Yaml.mapper().readValue(new File(url.getFile()), OpenAPI.class);
                } catch (Exception e) {
                    // continue
                }
            }
        } catch (Exception e) {
            // continue
        }

        if (petstoreOpenAPI != null) {
            openAPI
                    .components(petstoreOpenAPI.getComponents())
                    .extensions(petstoreOpenAPI.getExtensions())
                    .info(petstoreOpenAPI.getInfo())
                    .paths(petstoreOpenAPI.getPaths())
                    .security(petstoreOpenAPI.getSecurity())
                    .externalDocs(petstoreOpenAPI.getExternalDocs())
                    .servers(petstoreOpenAPI.getServers())
                    .tags(petstoreOpenAPI.getTags());
        }

        String oauthHost = System.getenv("SWAGGER_OAUTH_HOST");
        if (StringUtils.isBlank(oauthHost)) {
            return;
        }
        openAPI.getComponents().getSecuritySchemes().get("petstore_auth").getFlows().getImplicit().setAuthorizationUrl(oauthHost + "/oauth/authorize");
    }
}
