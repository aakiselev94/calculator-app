package ru.akiselev.calculator.client.client.dto;

import com.google.common.base.Preconditions;

import java.util.List;

public sealed interface Expr extends Operand permits BinaryExpr, UnaryExpr {

    String symbol();

    List<Operand> args();

    int numberOfArgs();

    default void checkArgs() {
        List<Operand> args = args();
        Preconditions.checkNotNull(args, "Args cannot be null!");
        Preconditions.checkState(numberOfArgs() == args.size(), "Number of arguments should be equal to " + numberOfArgs() + ".");
    }
}
