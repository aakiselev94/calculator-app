package ru.akiselev.calculator.service.service;

import com.google.common.base.Preconditions;
import com.google.inject.Singleton;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import ru.akiselev.calculator.service.CalculatorLexer;
import ru.akiselev.calculator.service.CalculatorParser;
import ru.akiselev.calculator.service.CalculatorParser.BracketsContext;
import ru.akiselev.calculator.service.CalculatorParser.DivisionContext;
import ru.akiselev.calculator.service.CalculatorParser.MinusContext;
import ru.akiselev.calculator.service.CalculatorParser.MultiplyContext;
import ru.akiselev.calculator.service.CalculatorParser.OperandContext;
import ru.akiselev.calculator.service.CalculatorParser.PlusContext;
import ru.akiselev.calculator.service.CalculatorParser.UnaryMinusContext;
import ru.akiselev.calculator.service.dto.Expr;
import ru.akiselev.calculator.service.dto.Operand;

import static org.apache.commons.lang3.StringUtils.isNumeric;

@Singleton
public class ExpressionService {

    public Operand buildExpression(String expression) {
        CalculatorLexer lexer = new CalculatorLexer(CharStreams.fromString(expression));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CalculatorParser parser = new CalculatorParser(tokens);
        ParseTree tree = parser.expression();
        return parse(tree);
    }

    private Operand parse(ParseTree node) {
        Preconditions.checkNotNull(node);
        switch (node) {
            case MinusContext minusContext -> {
                return parseBinary(minusContext, "-");
            }
            case PlusContext plusContext -> {
                return parseBinary(plusContext, "+");
            }
            case MultiplyContext multiplyContext -> {
                return parseBinary(multiplyContext, "*");
            }
            case DivisionContext divisionContext -> {
                return parseBinary(divisionContext, "/");
            }
            case BracketsContext bracketsContext -> {
                return Expr.unary("()", parse(bracketsContext.getChild(1)));
            }
            case UnaryMinusContext unaryMinusContext -> {
                return Expr.unary("--", parse(unaryMinusContext.getChild(0)));
            }
            case OperandContext operandContext -> {
                 String val = operandContext.getText();
                if (isNumeric(val)) {
                    return Expr.number(Double.parseDouble(val));
                } else {
                    return Expr.variable(val);
                }
            }
            default -> {
                return Expr.empty();
            }
        }
    }

    private Operand parseBinary(ParseTree node, String operator) {
        Operand left = parse(node.getChild(0));
        Operand right = parse(node.getChild(2));
        return Expr.binary(operator, left, right);
    }
}
