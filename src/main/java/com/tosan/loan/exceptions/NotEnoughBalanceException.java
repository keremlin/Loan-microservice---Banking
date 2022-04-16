package com.tosan.loan.exceptions;

public class NotEnoughBalanceException extends RuntimeException {
    public NotEnoughBalanceException(String message) {
        super("Not enough balance in deposit : " + message);
    }

    public NotEnoughBalanceException() {
        super("Not enough balance in deposit");
    }

}
