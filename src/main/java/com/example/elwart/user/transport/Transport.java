package com.example.elwart.user.transport;

import java.util.Set;

public enum Transport {
    AUTO("Auto"),
    POCIAG("Pociag"),
    AUTOBUS("Autobus");

    private String description;
    Transport(String description){
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
    private Set<Transport> publicTransports(){
        return Set.of(Transport.POCIAG,Transport.AUTOBUS);
    }
    public boolean isPublicTransport(){
        return publicTransports().contains(this);
    }
}
