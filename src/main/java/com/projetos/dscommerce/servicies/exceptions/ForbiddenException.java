package com.projetos.dscommerce.servicies.exceptions;

public class ForbiddenException extends RuntimeException{

    public ForbiddenException( String message) {
        super(message);
    }
}
