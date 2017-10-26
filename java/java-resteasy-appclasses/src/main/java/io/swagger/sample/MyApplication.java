package io.swagger.sample;

import io.swagger.sample.resource.PetResource;
import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ApplicationPath("/sample")
public class MyApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        return Stream.of(PetResource.class, OpenApiResource.class).collect(Collectors.toSet());
    }
}
