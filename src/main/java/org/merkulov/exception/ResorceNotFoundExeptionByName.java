package org.merkulov.exception;

import lombok.Getter;

@Getter
public class ResorceNotFoundExeptionByName extends RuntimeException {
    private final String resourceName;
    private final String fieldName;
    private final String fieldValue;

    public ResorceNotFoundExeptionByName(String resourceName, String fieldName, String fieldValue) {
        super(String.format("%s not found with %s : %s", resourceName, fieldName, fieldValue)); // Post not found with id: 1

        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}