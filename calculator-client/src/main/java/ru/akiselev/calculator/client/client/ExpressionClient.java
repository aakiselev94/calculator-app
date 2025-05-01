package ru.akiselev.calculator.client.client;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import ru.akiselev.calculator.client.client.dto.Operand;

@Path("/expressions")
@Produces(MediaType.APPLICATION_JSON)
public interface ExpressionClient {

    @GET
    @Path("/{expression}")
    Operand buildExpression(@PathParam("expression") String expression);
}
