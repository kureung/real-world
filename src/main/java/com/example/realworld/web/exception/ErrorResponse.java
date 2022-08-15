package com.example.realworld.web.exception;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.WRAPPER_OBJECT;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.List;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@JsonTypeName("errors")
@JsonTypeInfo(use = NAME, include = WRAPPER_OBJECT)
public class ErrorResponse {

    @JsonProperty("body")
    private final List<String> messages;

    public ErrorResponse(Exception e) {
        this(List.of(e.getMessage()));
    }

    public ErrorResponse(BindException e) {
        this(rejectedValuesOfBindingResult(e));
    }

    public ErrorResponse(BusinessException businessException) {
        this.messages = List.of(businessException.getMessage());
    }

    public ErrorResponse(List<String> messages) {
        this.messages = messages;
    }

    private static List<String> rejectedValuesOfBindingResult(BindingResult bindingResult) {
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        return fieldErrors.stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
    }

}
