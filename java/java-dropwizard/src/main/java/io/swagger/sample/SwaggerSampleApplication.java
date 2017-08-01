package io.swagger.sample;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.swagger.jaxrs2.integration.resources.OpenApiResource;
import io.swagger.oas.integration.OpenApiConfiguration;
import io.swagger.oas.models.OpenAPI;
import io.swagger.oas.models.info.Contact;
import io.swagger.oas.models.info.Info;
import io.swagger.oas.models.info.License;
import io.swagger.sample.resource.PetResource;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SwaggerSampleApplication extends Application <SwaggerSampleConfiguration> {
  public static void main(String[] args) throws Exception {
    new SwaggerSampleApplication().run(args);
  }

  @Override
  public void initialize(Bootstrap<SwaggerSampleConfiguration> bootstrap) { }

  @Override   
  public String getName() {
      return "swagger-sample";
  }

  @Override
  public void run(SwaggerSampleConfiguration configuration, Environment environment) {

    OpenAPI oas = new OpenAPI();
    Info info = new Info()
            .title("Swagger Sample App")
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
    OpenApiConfiguration oasConfig = new OpenApiConfiguration()
            .openApi(oas)
            .prettyPrint(true)
            .resourcePackages(Stream.of("io.swagger.sample.resource").collect(Collectors.toSet()));

    environment.jersey().register(new PetResource());
    environment.getObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);

    // eg.
    environment.jersey().register(new OpenApiResource().openApiConfiguration(oasConfig));

    // or
/*
    try {
      new GenericOpenApiContext().openApiConfiguration(oasConfig).init();
    } catch (OpenApiConfigurationException e) {
      e.printStackTrace();
    }
*/

    // or
    //environment.jersey().register(new OpenApiResource().configLocation("/integration/openapi-configuration.json"));

    // or provide a openapi-configuration.json or yaml in classpath

    // or
    //environment.jersey().register(new OpenApiResource().resourcePackage("io.swagger.sample.resource"));

    // or
    //environment.jersey().register(new OpenApiResource().resourceClasses("io.swagger.sample.resource.PetResource"));


  }
}
