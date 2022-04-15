package com.tosan.loan.services;

import java.util.List;

import com.tosan.loan.dto.LoanDto;
import com.tosan.loan.model.Loan;
import com.tosan.loan.model.LoanState;
import com.tosan.loan.repository.LoanRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanRepository repo;

    @Override
    public int calculateInstallments(int amount, int numberOfInstallment, int rate) {
        return (int) calculateEachInstallment(amount, numberOfInstallment, rate);
    }
    public static int calculateProfit(int amount, int numberOfInstallment,int rate){
        return (amount * rate * (numberOfInstallment + 1)) / 2400;
    }
    public static float calculateEachInstallment(int amount,int numberOfInstallment,int rate){
        return((amount+calculateProfit(amount, numberOfInstallment, rate))/numberOfInstallment);
    }

    @Override
    public Loan createLoan(LoanDto loan) {
        // if(loan.getDepositNumber()==is not real)
        //     exception TODO: create a new service in deposit for checking
        Loan newLoan = loan.convert();
        newLoan.setState(LoanState.open);
        newLoan.setAmountOfEachInstallment(
                calculateInstallments(loan.getAmount(), loan.getInstallmentNumber(), loan.getRate()));
        newLoan=repo.save(newLoan);
        return newLoan;
    }

    @Override
    public List<Loan> listOfLoanForDeposit(String depositNumber) {
        // TODO check deposit number
        return repo.findByDepositNumber(depositNumber);
    }

    @Override
    public Loan payInstallment(String depositNumber) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Loan> toList() {
        return repo.findAll();
    }

    
}
