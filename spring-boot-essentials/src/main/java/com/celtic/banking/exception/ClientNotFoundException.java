package com.celtic.banking.exception;

public class ClientNotFoundException extends RuntimeException{
    public ClientNotFoundException(String message){
        super(message);
    }

    public ClientNotFoundException(String message, String code){
        super(message);
    }

}
