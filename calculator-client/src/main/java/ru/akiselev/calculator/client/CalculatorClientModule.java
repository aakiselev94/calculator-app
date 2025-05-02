package ru.akiselev.calculator.client;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import ru.akiselev.calculator.client.client.CalculatorServiceApiBuilder;
import ru.akiselev.calculator.client.client.ExpressionClient;

public class CalculatorClientModule extends AbstractModule {

    @Override
    protected void configure() {
    }

    @Provides
    @Singleton
    public ExpressionClient expressionClient(CalculatorClientConfiguration configuration) {
        return CalculatorServiceApiBuilder.build(ExpressionClient.class, configuration.getCalculatorServerHost());
    }

}
