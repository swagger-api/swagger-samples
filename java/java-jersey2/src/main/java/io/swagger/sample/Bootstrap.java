package io.swagger.sample;

import io.swagger.jaxrs2.Reader;
import io.swagger.jaxrs2.config.SwaggerContextService;
import io.swagger.jaxrs2.integration.ContextUtils;
import io.swagger.jaxrs2.integration.XmlWebOpenApiContext;
import io.swagger.oas.integration.OpenApiConfiguration;
import io.swagger.oas.integration.OpenApiContextLocator;
import io.swagger.oas.models.OpenAPI;
import io.swagger.oas.models.info.Contact;
import io.swagger.oas.models.info.Info;
import io.swagger.oas.models.info.License;
import io.swagger.sample.resource.Metadata;
import io.swagger.sample.resource.PetResource;
import io.swagger.sample.resource.UserResource;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class Bootstrap extends HttpServlet {
  @Override
  public void init(ServletConfig config) throws ServletException {
    //OpenAPI oas = new OpenAPI();

    //Reader reader = new Reader(oas);
/*

    reader.read(Metadata.class);
    reader.read(UserResource.class);
    reader.read(PetResource.class);

    config.getServletContext().setAttribute("oas", reader.getOpenAPI());
*/



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
            .withResourcePackage("io.swagger.sample.resource");
    /*
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
    ContextUtils.getOrBuildContext(oasConfig);
  }
}
