package io.swagger.sample;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/test")
public class MyApplication extends ResourceConfig {

    public MyApplication() {
        super();
    }
}