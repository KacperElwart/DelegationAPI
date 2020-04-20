package com.example.elwart.user.exception;

public class NotTicketPriceException extends Exception{
    public NotTicketPriceException(){
        super("Ticket price is obligatory for this transport type");
    }
}
