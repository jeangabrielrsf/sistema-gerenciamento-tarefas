package com.br.supera.exceptions;

public class ItemNotFoundException extends RuntimeException {
    private final static String MESSAGE = "Item não encontrado!";
    public ItemNotFoundException() {
        super(MESSAGE);
    }
}
