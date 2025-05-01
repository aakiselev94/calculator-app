package ru.akiselev.calculator.client.client;

import com.fasterxml.jackson.databind.JsonNode;
import feign.FeignException;
import feign.Response;
import feign.codec.Decoder;
import ru.akiselev.calculator.client.client.dto.Operand;
import ru.akiselev.calculator.client.utils.JsonUtils;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;
import java.util.stream.StreamSupport;

public class OperandDecoder implements Decoder {

    private static final String VAL = "val";
    private static final String VAR = "var";
    private static final String SYMBOL = "symbol";
    private static final String ARGS = "args";

    private static final Map<String, BinaryOperator<Double>> BINARY_OPERATORS = Map.of(
            "+", Double::sum,
            "-", (a, b) -> a - b,
            "*", (a, b) -> a * b,
            "/", (a, b) -> a / b
    );

    private static final Map<String, UnaryOperator<Double>> UNARY_OPERATORS = Map.of(
            "()", a -> a,
            "--", a -> --a
    );

    @Override
    public Operand decode(Response response, Type type) throws FeignException {
        final JsonNode jsonNode = JsonUtils.toJsonNode(response);
        return parse(jsonNode);
    }

    private Operand parse(final JsonNode node) {
        if (node.has(SYMBOL) && node.has(ARGS)) {
            var operands = StreamSupport.stream(node.get(ARGS).spliterator(), false)
                    .map(this::parse)
                    .toList();
            var operator = node.get(SYMBOL).asText();
            if (BINARY_OPERATORS.containsKey(operator)) {
                return Operand.binary(operator, BINARY_OPERATORS.get(operator), operands);
            } else if (UNARY_OPERATORS.containsKey(operator)) {
                return Operand.unary(operator, UNARY_OPERATORS.get(operator), operands);
            } else {
                return Operand.empty();
            }
        } else if (node.has(VAL)) {
            return Operand.number(node.get(VAL).asDouble());
        } else if (node.has(VAR)) {
            return Operand.variable(node.get(VAR).asText());
        }
        return Operand.empty();
    }
}
