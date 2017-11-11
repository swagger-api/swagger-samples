package io.swagger.sample;

import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import io.swagger.v3.oas.integration.SwaggerConfiguration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.glassfish.jersey.server.ResourceConfig;

import javax.servlet.ServletConfig;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Context;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ApplicationPath("/sample")
public class MyApplication extends ResourceConfig {

    public MyApplication(@Context ServletConfig servletConfig) {
        super();
        OpenAPI oas = new OpenAPI();
        Info info = new Info()
                .title("Swagger Sample App bootstrap code")
                .description("This is a sample server Petstore server.  You can find out more about Swagger " +
                        "at [http://swagger.io](http://swagger.io) or on [irc.freenode.net, #swagger](http://swagger.io/irc/).  For this sample, " +
                        "you can use the api key `special-key` to test the authorization filters.")
                .termsOfService("http://swagger.io/terms/")
                .contact(new Contact()
                        .email("apiteam@swagger.io"))
                .license(new License()
                        .name("Apache 2.0")
                        .url("http://www.apache.org/licenses/LICENSE-2.0.html"));

        oas.info(info);

        // SecurityScheme api-key
        SecurityScheme securitySchemeApiKey = new SecurityScheme();
        securitySchemeApiKey.setName("api-key");
        securitySchemeApiKey.setType(SecurityScheme.Type.APIKEY);
        securitySchemeApiKey.setIn(SecurityScheme.In.HEADER);

        OpenApiResource openApiResource = new OpenApiResource();

        oas.schemaRequirement(securitySchemeApiKey.getName(), securitySchemeApiKey);

        SwaggerConfiguration oasConfig = new SwaggerConfiguration()
                .openAPI(oas)
                .prettyPrint(true)
                .resourcePackages(Stream.of("io.swagger.sample.resource").collect(Collectors.toSet()));

        openApiResource.setOpenApiConfiguration(oasConfig);
        register(openApiResource);
    }
}
