package ru.akiselev.calculator.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@ResourceRepresentation
public record BinaryExpression(@JsonProperty("symbol") String symbol,
                               @JsonProperty("args") List<Operand> operands) implements Operand {

    public BinaryExpression(String symbol, Operand arg1, Operand arg2) {
        this(symbol, List.of(arg1, arg2));
    }
}
