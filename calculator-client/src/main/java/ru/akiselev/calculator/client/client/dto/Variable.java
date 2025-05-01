package ru.akiselev.calculator.client.client.dto;

public record Variable(String var) implements Operand {

    @Override
    public double evaluate() {
        throw new RuntimeException("Variable can't be evaluated.");
    }
}
