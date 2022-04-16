package com.tosan.loan.exceptions;

public class DepositNotFoundException extends RuntimeException {
    public DepositNotFoundException(String message){
        super("Deposit not found with number : " + message);
    }
    public DepositNotFoundException(){
        super("Deposit not found with number : ");
    }
}
