package io.swagger.sample;

import io.swagger.v3.jaxrs2.integration.JaxrsOpenApiContextBuilder;
import io.swagger.v3.oas.integration.SwaggerConfiguration;
import io.swagger.v3.oas.integration.OpenApiConfigurationException;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Bootstrap extends HttpServlet {
  @Override
  public void init(ServletConfig config) throws ServletException {

    OpenAPI oas = new OpenAPI();
    Info info = new Info()
      .title("Swagger Sample App - independent config exposed by dedicated servlet")
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
    List<Server> servers = new ArrayList<>();
    servers.add(new Server().url("/api"));
    oas.servers(servers);
    SwaggerConfiguration oasConfig = new SwaggerConfiguration()
            .openAPI(oas)
            .resourcePackages(Stream.of("io.swagger.sample.resource").collect(Collectors.toSet()));


    // or
/*
    try {
      new GenericOpenApiContext().openApiConfiguration(oasConfig).init();
    } catch (OpenApiConfigurationException e) {
      e.printStackTrace();
    }
*/


    try {
      new JaxrsOpenApiContextBuilder()
              .servletConfig(config)
              //.ctxId(config.getServletName())
              .openApiConfiguration(oasConfig)
              .buildContext(true);
    } catch (OpenApiConfigurationException e) {
      throw new ServletException(e.getMessage(), e);
    }

  }
}
