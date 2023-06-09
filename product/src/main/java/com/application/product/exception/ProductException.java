package com.application.product.exception;

import org.springframework.http.HttpStatus;

public class ProductException extends Exception {

    private HttpStatus errorStatus;
    private String errorMessage;

    public ProductException() {
        super();
    }

    public ProductException(String errorMessage, HttpStatus errorStatus) {
        super(errorMessage);
        this.errorStatus = errorStatus;
        this.errorMessage = errorMessage;
    }

    public HttpStatus getErrorStatus() {
        return errorStatus;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}