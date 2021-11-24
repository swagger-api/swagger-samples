/*
 *  Copyright 2016 SmartBear Software
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package io.swagger.samples.inflector.dropwizard;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.inject.Injector;
import com.google.inject.Stage;
import com.hubspot.dropwizard.guice.GuiceBundle;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.swagger.inflector.SwaggerInflector;
import io.swagger.inflector.config.Configuration;
import io.swagger.inflector.config.ControllerFactory;
import io.swagger.inflector.processors.JsonNodeExampleSerializer;
import io.swagger.inflector.processors.XMLExampleProvider;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import io.swagger.models.Operation;
import io.swagger.util.Json;
import io.swagger.util.Yaml;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.slf4j.LoggerFactory;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

public class InflectorServer extends Application<InflectorServerConfiguration> {
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(InflectorServer.class);
    private GuiceBundle<InflectorServerConfiguration> guiceBundle;

    public static void main(String[] args) throws Exception {
        new InflectorServer().run(args);
    }

    @Override
    public String getName() {
        return "Inflector Dropwizard Sample";
    }

    @Override
    public void initialize(Bootstrap<InflectorServerConfiguration> bootstrap) {
        guiceBundle = GuiceBundle.<InflectorServerConfiguration>newBuilder()
            .addModule(new InflectorServerModule())
            .setConfigClass(InflectorServerConfiguration.class)
            .build(Stage.DEVELOPMENT);

        bootstrap.addBundle(guiceBundle);
    }

    @Override
    public void run(InflectorServerConfiguration configuration, Environment environment) throws Exception {
        final FilterRegistration.Dynamic cors = environment.servlets().addFilter("crossOriginRequsts", CrossOriginFilter.class);
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

        Configuration config = Configuration.read(configuration.getConfig());
        config.setControllerFactory(new GuiceControllerFactory(guiceBundle.getInjector()));
        SwaggerInflector inflector = new SwaggerInflector(config);
        environment.jersey().getResourceConfig().registerResources(inflector.getResources());
        
        // add serializers for swagger
        environment.jersey().register(SwaggerSerializers.class);

        // example serializers
        environment.jersey().register(XMLExampleProvider.class);
        
        // mappers
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(new JsonNodeExampleSerializer());
        Json.mapper().registerModule(simpleModule);
        Yaml.mapper().registerModule(simpleModule);
    }

    private static class GuiceControllerFactory implements ControllerFactory {
        private final Injector injector;

        public GuiceControllerFactory(Injector injector) {
            this.injector = injector;
        }

        public Object instantiateController(Class<?> aClass, Operation operation) throws IllegalAccessException, InstantiationException {
            return this.injector.getInstance(aClass);
        }
    }
}
