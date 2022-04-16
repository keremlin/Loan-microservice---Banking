package com.tosan.loan.services;

import java.util.List;

import com.tosan.loan.dto.LoanDto;
import com.tosan.loan.exceptions.DepositNotFoundException;
import com.tosan.loan.exceptions.NotEnoughBalanceException;
import com.tosan.loan.model.Loan;
import com.tosan.loan.model.LoanState;
import com.tosan.loan.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanRepository repo;

    @Autowired
    private DepositResources depositRepo;

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

         if(!depositRepo.isDepositValid(loan.getDepositNumber()))
            throw new DepositNotFoundException();

        Loan newLoan = loan.convert();
        newLoan.setState(LoanState.open);
        newLoan.setAmountOfEachInstallment(
                calculateInstallments(loan.getAmount(), loan.getInstallmentNumber(), loan.getRate()));
        newLoan=repo.save(newLoan);
        return newLoan;
    }

    @Override
    public List<Loan> listOfLoanForDeposit(String depositNumber) {
        if (!depositRepo.isDepositValid(depositNumber))
            throw new DepositNotFoundException();
        return repo.findByDepositNumber(depositNumber);
    }

    @Override
    public Loan payInstallment(String depositNumber) {
        if (!depositRepo.isDepositValid(depositNumber))
            throw new DepositNotFoundException();
        List<Loan> list = repo.findByDepositNumber(depositNumber);
        Loan item = list.size() > 0 ? list.get(0) : null;

        if (item != null) {
            if (item.getState() != LoanState.closed) {
                if (depositRepo.withdrawInstallment(depositNumber, item.getAmountOfEachInstallment())) {
                    item.setInstallmentNumber(item.getInstallmentNumber() - 1);
                    if (item.getInstallmentNumber() == 0) {
                        item.setState(LoanState.closed);
                    } else {
                        item.setState(LoanState.Paying);
                    }
                    item = repo.save(item);
                } else {
                    throw new NotEnoughBalanceException(depositNumber);
                }
            }
        }
        return item;
    }

    @Override
    public List<Loan> toList() {
        return repo.findAll();
    }

    
}
