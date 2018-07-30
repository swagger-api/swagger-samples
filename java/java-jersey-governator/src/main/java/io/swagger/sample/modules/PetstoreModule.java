package io.swagger.sample.modules;

import com.google.common.collect.Maps;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import com.sun.jersey.spi.container.servlet.ServletContainer;
import io.swagger.sample.resources.RootResource;
import io.swagger.sample.util.ApiOriginCorsFilter;

import java.util.Map;

/**
 * Created by rbolles on 2/18/16.
 */
public class PetstoreModule extends JerseyServletModule {

    protected void configureServlets() {
        bind(RootResource.class).asEagerSingleton();
//        bind(SampleExceptionMapper.class).asEagerSingleton();

        //make the ServletContext available for the SwaggerModule
        bind(ServletConfigProvider.class).toInstance(new ServletConfigProvider(getServletContext()));

        bind(GuiceContainer.class).asEagerSingleton();
        filter("/*").through(ApiOriginCorsFilter.class);
        filter("/*").through(GuiceContainer.class, createServletParams());
    }

    private Map<String, String> createServletParams() {
        Map<String, String> servletParams = Maps.newHashMapWithExpectedSize(2);
        // For static resources like HTML, JS, or CSS that should get served by the servlet container
        servletParams.put(ServletContainer.PROPERTY_WEB_PAGE_CONTENT_REGEX, "/(docs|js)/.*");
        return servletParams;

    }
}
