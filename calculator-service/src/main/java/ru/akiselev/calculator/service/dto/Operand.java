package ru.akiselev.calculator.service.dto;

public sealed interface Operand permits BinaryExpression, UnaryExpression, Number, Variable, Empty {

    static Operand binary(String symbol, Operand left, Operand right) {
        return new BinaryExpression(symbol, left, right);
    }

    static Operand unary(String symbol, Operand operand) {
        return new UnaryExpression(symbol, operand);
    }

    static Operand number(double val) {
        return new Number(val);
    }

    static Operand variable(String var) {
        return new Variable(var);
    }

    static Operand empty() {
        return new Empty();
    }
}

