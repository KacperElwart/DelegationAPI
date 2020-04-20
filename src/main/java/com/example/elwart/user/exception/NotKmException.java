package com.example.elwart.user.exception;

public class NotKmException extends Exception {
    public NotKmException(){
        super("Km for Auto is obligatory");
    }
}
