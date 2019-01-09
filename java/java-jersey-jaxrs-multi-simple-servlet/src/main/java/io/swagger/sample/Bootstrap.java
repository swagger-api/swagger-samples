package io.swagger.sample;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.config.SwaggerContextService;
import io.swagger.models.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

public class Bootstrap extends HttpServlet {
  @Override
  public void init(ServletConfig config) throws ServletException {
    Info info = new Info()
      .title("Swagger Petstore")
      .version("1.0.0");
    Swagger swagger = new Swagger().info(info);
    swagger.tag(new Tag()
      .name("store")
      .description("Access to Petstore orders"));

    // specify packages to be included by providing a Scanner implementation (BeanConfig) using reflection to scan classes
    // included in packages provided in "ResourcePackage". This can be useful e.g. to specify a subset (or different set)
    // of packages/classes defined in jersey servlet/jaxrs application.
    BeanConfig beanConfig = new BeanConfig();
    beanConfig.setResourcePackage("io.swagger.sample.storeresource");
    beanConfig.setInfo(info); // needed if using BeanConfig
    beanConfig.setBasePath("/api1");

    new SwaggerContextService()
            .withContextId("test.1")
            .withBasePath("/api1")
            .withScanner(beanConfig) // if we need to specify packages
            .updateSwagger(swagger)
            .initScanner();


    Info info2 = new Info()
            .title("Swagger Petstore 2")
            .version("1.0.0");
    Swagger swagger2 = new Swagger().info(info2);
    swagger2.tag(new Tag()
            .name("pet")
            .description("Everything about your Pets"));

    // specify packages to be included by providing a Scanner implementation (BeanConfig) using reflection to scan classes
    // included in packages provided in "ResourcePackage". This can be useful e.g. to specify a subset (or different set)
    // of packages/classes defined in jersey servlet/jaxrs application.
    BeanConfig beanConfig2 = new BeanConfig();
    beanConfig2.setResourcePackage("io.swagger.sample.userresource");
    beanConfig2.setInfo(info2); // needed if using BeanConfig
    beanConfig2.setBasePath("/api2");

    new SwaggerContextService()
            .withContextId("test.2")
            .withBasePath("/api2")
            .withScanner(beanConfig2) // if we need to specify packages
            .updateSwagger(swagger2)
            .initScanner();
  }
}
