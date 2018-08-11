package com.chang.exception;

public class DefaultGroupCantDeleteException extends RuntimeException{

    public DefaultGroupCantDeleteException(String message) {
        super(message);
    }
}
