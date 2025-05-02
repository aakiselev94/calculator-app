package ru.akiselev.calculator.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

@ResourceRepresentation
public record Number(@JsonProperty("val") double val) implements Operand {}
