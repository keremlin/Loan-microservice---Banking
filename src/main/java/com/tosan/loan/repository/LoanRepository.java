package com.tosan.loan.repository;

import java.util.List;

import com.tosan.loan.model.Loan;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends CrudRepository<Loan,Integer> {
    public List<Loan> findAll();
    public List<Loan> findByDepositNumber(String depositNumber);
}
