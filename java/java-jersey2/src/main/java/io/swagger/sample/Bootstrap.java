package io.swagger.sample;

import io.swagger.jaxrs2.Reader;
import io.swagger.oas.models.OpenAPI;
import io.swagger.sample.resource.PetResource;
import io.swagger.sample.resource.UserResource;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class Bootstrap extends HttpServlet {
  @Override
  public void init(ServletConfig config) throws ServletException {
    OpenAPI oas = new OpenAPI();

    Reader reader = new Reader(oas, null);

    oas = reader.read(UserResource.class);
    oas = reader.read(PetResource.class);
    config.getServletContext().setAttribute("oas", oas);




    /*
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

    ServletContext context = config.getServletContext();
    Swagger swagger = new Swagger().info(info);
    swagger.securityDefinition("api_key", new ApiKeyAuthDefinition("api_key", In.HEADER));
    swagger.securityDefinition("petstore_auth", 
      new OAuth2Definition()
        .implicit("http://petstore.swagger.io/api/oauth/dialog")
        .scope("read:pets", "read your pets")
        .scope("write:pets", "modify pets in your account"));
    new SwaggerContextService().withServletConfig(config).updateSwagger(swagger);
    */
  }
}
