package ru.akiselev.calculator.client.resource;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.AllArgsConstructor;
import ru.akiselev.calculator.client.dto.ExpressionRequest;
import ru.akiselev.calculator.client.service.ExpressionService;

@Path("/expressions")
@Produces(MediaType.APPLICATION_JSON)
@Singleton
@AllArgsConstructor(onConstructor = @__({@Inject}))
public class ExpressionResource {

    private final ExpressionService expressionService;

    @POST
    public double buildExpression(final ExpressionRequest request) {
        return expressionService.evaluateExpr(request);
    }
}
