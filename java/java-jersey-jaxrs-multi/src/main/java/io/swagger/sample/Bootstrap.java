package io.swagger.sample;

import io.swagger.jaxrs.config.SwaggerContextService;
import io.swagger.models.*;

import io.swagger.models.auth.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

public class Bootstrap extends HttpServlet {
  @Override
  public void init(ServletConfig config) throws ServletException {
    Info info = new Info()
      .title("Swagger Petstore")
      .description("This is a sample server Petstore server.  You can find out more about Swagger " + 
        "at [http://swagger.io](http://swagger.io) or on [irc.freenode.net, #swagger](http://swagger.io/irc/).  For this sample, " +
        "you can use the api key `special-key` to test the authorization filters.")
      .termsOfService("http://swagger.io1/terms/")
      .contact(new Contact()
        .email("apiteam@swagger.io"))
      .license(new License()
        .name("Apache 2.0")
        .url("http://www.apache.org/licenses/LICENSE-2.0.html"));

    Swagger swagger = new Swagger().info(info);
    swagger.externalDocs(new ExternalDocs("Find out more about Swagger", "http://swagger.io"));
    swagger.securityDefinition("api_key", new ApiKeyAuthDefinition("api_key", In.HEADER));
    swagger.securityDefinition("petstore_auth", 
      new OAuth2Definition()
        .implicit("http://petstore.swagger.io/api/oauth/dialog")
        .scope("read:pets", "read your pets")
        .scope("write:pets", "modify pets in your account"));
    swagger.tag(new Tag()
      .name("pet")
      .description("Everything about your Pets")
      .externalDocs(new ExternalDocs("Find out more", "http://swagger.io")));
    swagger.tag(new Tag()
      .name("store")
      .description("Access to Petstore orders"));
    swagger.tag(new Tag()
      .name("user")
      .description("Operations about user")
      .externalDocs(new ExternalDocs("Find out more about our store", "http://swagger.io")));

    new SwaggerContextService().withServletConfig(config).updateSwagger(swagger);
  }
}
