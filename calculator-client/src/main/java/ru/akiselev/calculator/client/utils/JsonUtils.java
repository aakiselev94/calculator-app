package ru.akiselev.calculator.client.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class JsonUtils {
    private JsonUtils () {}

    public static JsonNode toJsonNode(Response response) {
        try {
            String json = IOUtils.toString(response.body().asInputStream(), StandardCharsets.UTF_8);
            return toJsonNode(json);
        } catch (IOException e) {
            throw new RuntimeException("Can't parse json from feign response.", e);
        }
    }

    private static JsonNode toJsonNode(String str) {
        try {
            return new ObjectMapper().readTree(str);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Can't parse json node.", e);
        }
    }
}
