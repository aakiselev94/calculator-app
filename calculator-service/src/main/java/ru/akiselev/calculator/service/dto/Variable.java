package ru.akiselev.calculator.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

@ResourceRepresentation
public record Variable(@JsonProperty("var") String var) implements Operand {}
