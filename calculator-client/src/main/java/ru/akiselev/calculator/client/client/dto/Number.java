package ru.akiselev.calculator.client.client.dto;

public record Number(double val) implements Operand {

    @Override
    public double evaluate() {
        return val;
    }
}
