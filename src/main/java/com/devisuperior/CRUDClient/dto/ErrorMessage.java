package com.devisuperior.CRUDClient.dto;

public class ErrorMessage {

    private String name;
    private String message;

    public ErrorMessage(String name, String message) {
        this.name = name;
        this.message = message;
    }
    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }
}
