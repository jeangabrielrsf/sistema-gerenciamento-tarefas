package com.br.supera.exceptions;

public class ListNotFoundException extends RuntimeException {
    private final static String MESSAGE = "Lista não encontrada!";
    public ListNotFoundException() {
        super(MESSAGE);
    }
}
