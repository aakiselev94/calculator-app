package ru.akiselev.calculator.client.client.dto;

import java.util.List;
import java.util.function.BinaryOperator;

public record BinaryExpr(String symbol, BinaryOperator<Double> operator, List<Operand> args) implements Expr {

    @Override
    public double evaluate() {
        checkArgs();
        return operator.apply(args.get(0).evaluate(), args.get(1).evaluate());
    }

    @Override
    public int numberOfArgs() {
        return 2;
    }
}
