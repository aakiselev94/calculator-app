package ru.akiselev.calculator.client.service;

import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.akiselev.calculator.client.client.ExpressionClient;
import ru.akiselev.calculator.client.client.dto.BinaryExpr;
import ru.akiselev.calculator.client.client.dto.Expr;
import ru.akiselev.calculator.client.client.dto.Operand;
import ru.akiselev.calculator.client.client.dto.UnaryExpr;
import ru.akiselev.calculator.client.client.dto.Variable;
import ru.akiselev.calculator.client.dto.ExpressionRequest;

import java.util.Map;

@Slf4j
@Singleton
@AllArgsConstructor(onConstructor = @__({@Inject}))
public class ExpressionService {

    private final ExpressionClient expressionClient;

    public double evaluateExpr(ExpressionRequest request) {
        Preconditions.checkNotNull(request, "Expression request can't be null.");
        Preconditions.checkNotNull(request.getExpression(), "Expression can't be null.");
        Operand expr = expressionClient.buildExpression(request.getExpression());
        return substituteVariables(expr, request.getParams()).evaluate();
    }

    private Operand substituteVariables(Operand operand, Map<String, Double> params) {
        Preconditions.checkNotNull(params, "Parameters can't be null.");
        Preconditions.checkNotNull(operand, "Expression can't be null");
        if (operand instanceof Variable variable) {
            if (!params.containsKey(variable.var())) {
                return operand;
            }
            return Operand.number(params.get(variable.var()));
        } else if (operand instanceof Expr expr) {
            var args = expr.args()
                    .stream()
                    .map(arg -> substituteVariables(arg, params))
                    .toList();
            if (expr instanceof BinaryExpr binaryExpr) {
                return Operand.binary(binaryExpr.symbol(), binaryExpr.operator(), args);
            } else if (expr instanceof UnaryExpr unaryExpr) {
                return Operand.unary(unaryExpr.symbol(), unaryExpr.operator(), args);
            }
        }
        return operand;
    }
}
