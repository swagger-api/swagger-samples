package io.swagger.sample;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/sample")
public class MyApplication extends ResourceConfig {

    public MyApplication() {
    //public MyApplication(@Context ServletConfig servletConfig) {
        super();
    }
}