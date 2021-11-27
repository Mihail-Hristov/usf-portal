package com.example.backend.exceprtion;


public class ObjectNotFoundException extends RuntimeException{

    private final String objectId;

    public ObjectNotFoundException(String objectId) {
        super("Object with id " + objectId + " not found" );
        this.objectId = objectId;
    }

    public String getObjectId() {
        return objectId;
    }
}
