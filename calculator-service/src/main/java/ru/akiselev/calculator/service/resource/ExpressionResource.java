package ru.akiselev.calculator.service.resource;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.AllArgsConstructor;
import ru.akiselev.calculator.service.dto.Operand;
import ru.akiselev.calculator.service.service.ExpressionService;

@Path("/expressions")
@Produces(MediaType.APPLICATION_JSON)
@Singleton
@AllArgsConstructor(onConstructor = @__({@Inject}))
public class ExpressionResource {

    private final ExpressionService expressionService;

    @GET
    @Path("/{expression}")
    public Operand buildExpression(@PathParam("expression") String expression) {
        return expressionService.buildExpression(expression);
    }
}
