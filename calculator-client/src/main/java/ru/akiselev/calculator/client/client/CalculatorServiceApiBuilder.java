package ru.akiselev.calculator.client.client;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.jaxrs.JAXRSContract;

public class CalculatorServiceApiBuilder {

    public static <T> T build(Class<T> clazz, String url) {
        return builder().target(clazz, url);
    }

    private static Feign.Builder builder() {
        return Feign.builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .decoder(new OperandDecoder())
                .contract(new JAXRSContract());
    }
}
