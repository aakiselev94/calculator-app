package ru.akiselev.calculator.client.client.dto;

import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public sealed interface Operand permits Variable, Number, Expr, Empty {

    double evaluate();

    static Operand binary(String symbol, BinaryOperator<Double> operator, List<Operand> args) {
        return new BinaryExpr(symbol, operator, args);
    }

    static Operand unary(String symbol, UnaryOperator<Double> operator, List<Operand> args) {
        return new UnaryExpr(symbol, operator, args);
    }

    static Operand empty() {
        return new Empty();
    }

    static Operand number(double val) {
        return new Number(val);
    }

    static Operand variable(String val) {
        return new Variable(val);
    }
}
