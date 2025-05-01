package ru.akiselev.calculator.client;

import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import ru.akiselev.calculator.client.healthcheck.AppHealthCheck;
import ru.vyarus.dropwizard.guice.GuiceBundle;

public class CalculatorClientApp extends Application<CalculatorClientConfiguration> {

    public static void main(String[] args) throws Exception {
        new CalculatorClientApp().run(args);
    }

    @Override
    public void run(CalculatorClientConfiguration configuration, Environment environment) {
        environment.healthChecks().register("AppHealthCheck", new AppHealthCheck());
    }

    @Override
    public String getName() {
        return "Calculator client";
    }

    @Override
    public void initialize(final Bootstrap<CalculatorClientConfiguration> bootstrap) {
        bootstrap.addBundle(GuiceBundle.builder()
                        .enableAutoConfig(getClass().getPackage().getName())
                        .modules(new CalculatorClientModule())
                .build());
        bootstrap.setConfigurationSourceProvider(new ResourceConfigurationSourceProvider());
        bootstrap.setConfigurationSourceProvider(new SubstitutingSourceProvider(
                bootstrap.getConfigurationSourceProvider(),
                new EnvironmentVariableSubstitutor(true))
        );
    }
}
