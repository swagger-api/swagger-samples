package io.swagger.sample;

import io.swagger.models.*;
import io.swagger.models.auth.ApiKeyAuthDefinition;
import io.swagger.models.auth.In;
import io.swagger.models.auth.OAuth2Definition;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Bootstrap extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        Map<String, Swagger> swaggers = (Map<String, Swagger>) config.getServletContext().getAttribute("swaggers");
        if (swaggers == null) {
            swaggers = new ConcurrentHashMap<String, Swagger>();
            config.getServletContext().setAttribute("swaggers", swaggers);
        }

        final String paths = config.getInitParameter("swagger.paths");
        for (String path: paths.split(";")) {
            swaggers.put(path, createSwagger());
        }
    }

    private Swagger createSwagger() {
        Info info = new Info()
                .title("Swagger Petstore")
                .description("This is a sample server Petstore server.  You can find out more about Swagger " +
                        "at [http://swagger.io](http://swagger.io) or on [irc.freenode.net, #swagger](http://swagger.io/irc/).  For this sample, " +
                        "you can use the api key `special-key` to test the authorization filters.")
                .termsOfService("http://swagger.io/terms/")
                .contact(new Contact()
                        .email("apiteam@swagger.io"))
                .license(new License()
                        .name("Apache 2.0")
                        .url("http://www.apache.org/licenses/LICENSE-2.0.html"));

        Swagger swagger = new Swagger().info(info);
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

        return swagger;

    }
}
