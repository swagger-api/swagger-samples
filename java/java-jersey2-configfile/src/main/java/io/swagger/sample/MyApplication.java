package io.swagger.sample;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/sample")
public class MyApplication extends ResourceConfig {
//public class MyApplication extends Application {

    public MyApplication() {
    //public MyApplication(@Context ServletConfig servletConfig) {
        super();
        /*
         * It seems that Jersey (at least 2.26) incorrectly registers also non root resources (available via getClasses())
         * even if stated otherwise in https://jersey.github.io/documentation/latest/deployment.html
         * 4.7.2.3.2. JAX-RS application with a custom Application subclass
         * this could result in non existing operations being added if served by subresource classes.
         *
         * To avoid this behaviour provide own packages/classes/resources/singleton here
         */
        super.packages("io.swagger.sample.resource", "io.swagger.v3.jaxrs2.integration.resources");
    }
}