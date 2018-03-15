package io.swagger.sample;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/test")
public class MyApplication extends Application {

    public MyApplication() {
        super();
    }
}