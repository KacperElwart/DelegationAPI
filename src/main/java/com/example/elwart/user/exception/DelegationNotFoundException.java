package com.example.elwart.user.exception;

import com.example.elwart.user.model.Delegation;

public class DelegationNotFoundException extends RuntimeException{
    public DelegationNotFoundException(Long id){
        super("Delegation with id: " + id + " not found");
    }
}
