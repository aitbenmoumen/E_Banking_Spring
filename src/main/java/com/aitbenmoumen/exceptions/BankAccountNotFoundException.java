package com.aitbenmoumen.exceptions;

public class BankAccountNotFoundException extends Exception {
    public BankAccountNotFoundException(String msg){
        super(msg);
    }
}
