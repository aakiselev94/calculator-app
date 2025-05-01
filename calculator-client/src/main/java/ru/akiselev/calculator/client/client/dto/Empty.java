package ru.akiselev.calculator.client.client.dto;

record Empty() implements Operand {

    @Override
    public double evaluate() {
        return 0;
    }
}
