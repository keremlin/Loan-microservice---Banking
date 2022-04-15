package com.tosan.loan.services;

import java.util.List;

import com.tosan.loan.dto.LoanDto;
import com.tosan.loan.model.*;

public interface LoanService {

    public abstract int calculateInstallments(int amount, int numberOfInstallment, int rate);

    public abstract Loan createLoan(LoanDto loan);

    public abstract Loan payInstallment(String depositNumber);

    public abstract List<Loan> toList();

    public abstract List<Loan> listOfLoanForDeposit(String depositNumber);

}
