package io.swagger.sample;

import com.google.inject.Injector;
import com.netflix.governator.InjectorBuilder;
import com.netflix.governator.guice.servlet.GovernatorServletContextListener;
import io.swagger.sample.modules.PetstoreModule;
import io.swagger.sample.modules.SwaggerModule;
import netflix.adminresources.resources.KaryonWebAdminModule;

/**
 * Created by rbolles on 2/18/16.
 */
public class StartServer extends GovernatorServletContextListener {

    @Override
    protected Injector createInjector() throws Exception {
        return InjectorBuilder.fromModules(
                new PetstoreModule(),
                new SwaggerModule(),
                new KaryonWebAdminModule()
        ).createInjector();
    }
}