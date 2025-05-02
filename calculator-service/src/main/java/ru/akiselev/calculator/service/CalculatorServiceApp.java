package ru.akiselev.calculator.service;

import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import ru.akiselev.calculator.service.healthcheck.AppHealthCheck;
import ru.vyarus.dropwizard.guice.GuiceBundle;

public class CalculatorServiceApp extends Application<CalculatorServiceConfiguration> {

    public static void main(String[] args) throws Exception {
        new CalculatorServiceApp().run(args);
    }

    @Override
    public void run(CalculatorServiceConfiguration configuration, Environment environment) {
        environment.healthChecks().register("AppHealthCheck", new AppHealthCheck());
    }

    @Override
    public String getName() {
        return "Calculator service";
    }

    @Override
    public void initialize(Bootstrap<CalculatorServiceConfiguration> bootstrap) {
        var bundle = GuiceBundle.builder()
                .enableAutoConfig(getClass().getPackage().getName())
                .modules(new CalculatorServiceModule())
                .build();
        bootstrap.addBundle(bundle);
        bootstrap.setConfigurationSourceProvider(new ResourceConfigurationSourceProvider());
    }
}
