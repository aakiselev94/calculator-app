package ru.akiselev.calculator.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@ResourceRepresentation
public record UnaryExpression(@JsonProperty("symbol") String symbol,
                              @JsonProperty("args") List<Operand> operands) implements Operand {

    public UnaryExpression(String symbol, Operand arg) {
        this(symbol, List.of(arg));
    }
}
