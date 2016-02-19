package io.swagger.sample.modules;

import com.google.inject.Inject;
import com.sun.jersey.api.core.HttpContext;
import com.sun.jersey.core.spi.component.ComponentContext;
import com.sun.jersey.core.spi.component.ComponentScope;
import com.sun.jersey.server.impl.inject.AbstractHttpContextInjectable;
import com.sun.jersey.spi.inject.Injectable;
import com.sun.jersey.spi.inject.InjectableProvider;

import javax.servlet.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Objects;
import java.util.Set;

/**
 * Created by rbolles on 2/17/16.
 *
 * A hack to allow Swagger, Jersey 1.X, and Guice to play nice together. (https://github.com/swagger-api/swagger-core/issues/1619)
 *
 * If you expose your JAX-RS resources via a Guice filter() call Jersey will blow up with a cryptic message saying something to the effect:
 *   SEVERE: Missing dependency for method public javax.ws.rs.core.Response io.swagger.jaxrs.listing.ApiListingResource.getListing(javax.ws.rs.core.Application,javax.servlet.ServletConfig,javax.ws.rs.core.HttpHeaders,javax.ws.rs.core.UriInfo,java.lang.String) at parameter at index 1
 *
 * This is error is due to the fact that when your expose your JAX-RS resources via filter, there is no ServletConfig object
 * to inject into Swagger's ApiListingResource. Jersey blows up and prevents your app from starting up.
 *
 * This class, when registered with Jersey (either directly or indirectly via Guice) provides a "dummy" ServletConfig
 * that Jersey can inject and go along on its merry way.
 *
 */
@Provider
public class ServletConfigProvider extends AbstractHttpContextInjectable<ServletConfig> implements InjectableProvider<Context, Type> {

    private final ServletContext servletContext;

    @Inject
    public ServletConfigProvider(ServletContext servletContext) {
        this.servletContext = Objects.requireNonNull(servletContext, "servletContext");;
    }

    @Override
    public ServletConfig getValue(HttpContext c) {
        return new ServletConfig() {

            @Override
            public String getServletName() {
                return null;
            }

            @Override
            public ServletContext getServletContext() {
                return servletContext;
            }

            @Override
            public String getInitParameter(String name) {
                return null;
            }

            @Override
            public Enumeration<String> getInitParameterNames() {
                return null;
            }

            @Override
            public String toString() {
                return "DUMMY SERVLET CONFIG TO GET SWAGGER WORKING WITH KARYON";
            }
        };
    }

    @Override
    public ComponentScope getScope() {
        return ComponentScope.Singleton;
    }

    @Override
    public Injectable getInjectable(ComponentContext ic, Context context, Type type) {
        if (type.equals(ServletConfig.class)) {
            return this;
        }

        return null;
    }
}
