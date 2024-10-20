package com.scs.kata.spring_boot_rest.exception;

public class InvalidInputException extends RuntimeException
{
    public InvalidInputException(String message)
    {
            super(message);
    }
}
