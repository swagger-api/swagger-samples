package io.swagger.sample;

import com.google.inject.Singleton;

import io.swagger.jaxrs.config.SwaggerContextService;
import io.swagger.models.Contact;
import io.swagger.models.ExternalDocs;
import io.swagger.models.Info;
import io.swagger.models.License;
import io.swagger.models.Swagger;
import io.swagger.models.Tag;
import io.swagger.models.auth.ApiKeyAuthDefinition;
import io.swagger.models.auth.In;
import io.swagger.models.auth.OAuth2Definition;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

@Singleton
public class Bootstrap extends HttpServlet {

  @Override
  public void init(ServletConfig config) throws ServletException {

    ServletContext context = config.getServletContext();
    Swagger swagger = new Swagger();
    swagger.securityDefinition("api_key", new ApiKeyAuthDefinition("api_key", In.HEADER));
    swagger.securityDefinition("petstore_auth",
      new OAuth2Definition()
        .implicit("http://localhost:8002/oauth/dialog")
        .scope("email", "Access to your email address")
        .scope("pets", "Access to your pets"));
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
