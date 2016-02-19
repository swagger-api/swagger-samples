/*
 * Copyright 2016 Jithu Menon
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.swagger.sample.modules;

import com.google.inject.AbstractModule;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

import javax.annotation.PostConstruct;

/**
 * A google guice module for configuring swagger
 *
 * For example: hostname and schemes
 */
public class SwaggerModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ApiListingResource.class).asEagerSingleton();
        bind(SwaggerSerializers.class).asEagerSingleton();
    }

    @PostConstruct
    public void init() {
        BeanConfig swaggerBeanConfig = new BeanConfig();
        swaggerBeanConfig.setHost("localhost:8002"); //this would be a dynamic value in a production app
        swaggerBeanConfig.setSchemes(new String[]{"http"}); //this would be a dynamic value in a production app
        swaggerBeanConfig.setScan();
    }

}
