package org.merkulov.exception;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException {
    private final String resourceName;
    private final String fieldName;
    private final Long fieldValue;
    private final String fieldEmail;

    public ResourceNotFoundException(String resourceName, String fieldName, String fieldEmail) {
        super(String.format("%s not found with %s : %s", resourceName, fieldName, fieldEmail));

        this.fieldValue = null;
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldEmail = fieldEmail;
    }
//TODO костыль исправить

    public ResourceNotFoundException(String resourceName, String fieldName, Long fieldValue) {
        super(String.format("%s not found with %s : %s", resourceName, fieldName, fieldValue)); // Post not found with id: 1
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
        this.fieldEmail = null;
    }



}
