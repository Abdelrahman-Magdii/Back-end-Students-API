package com.spring.student.ErrorHandling;

public class IdNotFound extends RuntimeException {

    public IdNotFound() {
        super();
    }

    public IdNotFound(String message) {
        super(message);
    }

}
