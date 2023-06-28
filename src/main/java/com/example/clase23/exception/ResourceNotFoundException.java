package com.example.clase23.exception;

public class ResourceNotFoundException extends Exception {
    public ResourceNotFoundException(String mensaje){
        super(mensaje);
    }
}
