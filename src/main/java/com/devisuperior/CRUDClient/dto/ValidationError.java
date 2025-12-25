package com.devisuperior.CRUDClient.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends CustomError {

    private List<ErrorMessage> errors =  new ArrayList<>();

    public ValidationError(Instant timestamp, Integer status, String error, String path) {
        super(timestamp, status, error, path);
    }

    public List<ErrorMessage> getErrors() {
        return errors;
    }

    public void addError(String fieldName,  String message) {
        errors.add(new  ErrorMessage(fieldName, message));
    }

}
