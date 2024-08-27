package com.coworking.exception;

public class ForbidenException extends RuntimeException{

    public ForbidenException(String mensaje) {
        super(mensaje);
    }
}
