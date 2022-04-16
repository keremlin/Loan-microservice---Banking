package com.tosan.loan.repository;

public interface DepositResources {
    public abstract boolean isDepositValid(String depositNumber);
    public abstract boolean withdrawInstallment(String depositNumber,int amount);
}
