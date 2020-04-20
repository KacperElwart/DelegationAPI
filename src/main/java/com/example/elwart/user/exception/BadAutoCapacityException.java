package com.example.elwart.user.exception;

public class BadAutoCapacityException  extends Exception{
    public BadAutoCapacityException(Integer capacity) {
        super("Capacity: " + capacity +" is bad. It must be higher or equal than 900 and less than 1900 ");
    }
}
