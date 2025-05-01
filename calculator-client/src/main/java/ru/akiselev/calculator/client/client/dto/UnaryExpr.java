package ru.akiselev.calculator.client.client.dto;

import java.util.List;
import java.util.function.UnaryOperator;

public record UnaryExpr(String symbol, UnaryOperator<Double> operator, List<Operand> args) implements Expr {

    @Override
    public double evaluate() {
        checkArgs();
        return operator.apply(args.get(0).evaluate());
    }

    @Override
    public int numberOfArgs() {
        return 1;
    }
}
