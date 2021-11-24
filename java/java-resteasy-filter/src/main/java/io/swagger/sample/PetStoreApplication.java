package io.swagger.sample;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.sample.resource.PetResource;
import io.swagger.sample.resource.PetStoreResource;
import io.swagger.sample.resource.UserResource;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class PetStoreApplication extends Application {
    HashSet<Object> singletons = new HashSet<Object>();

    public PetStoreApplication() {
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.2");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("localhost:8002");
        beanConfig.setBasePath("/api");
        beanConfig.setFilterClass("io.swagger.sample.util.ApiAuthorizationFilterImpl");
        beanConfig.setResourcePackage("io.swagger.sample.resource");
        beanConfig.setScan(true);
    }

    @Override
    public Set<Class<?>> getClasses() {
        HashSet<Class<?>> set = new HashSet<Class<?>>();

        set.add(PetResource.class);
        set.add(UserResource.class);
        set.add(PetStoreResource.class);

        set.add(io.swagger.jaxrs.listing.ApiListingResource.class);
        set.add(io.swagger.jaxrs.listing.AcceptHeaderApiListingResource.class);
        set.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);


        return set;
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}
