package com.tosan.loan.controllers;

import java.util.List;

import javax.validation.Valid;

import com.tosan.loan.dto.LoanDto;
import com.tosan.loan.model.Loan;
import com.tosan.loan.services.LoanService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/loan")
public class LoanController {

    @Autowired
    private LoanService service;

    @PostMapping("/create")
    public ResponseEntity<Loan> createLoan(@Valid @RequestBody LoanDto loan){
        return ResponseEntity.ok().body(
            service.createLoan(loan)        
        );
    }
    @GetMapping("/getAllLoans")
    public ResponseEntity<List<Loan>> getAllLoans(){
        return ResponseEntity.ok().body(
            service.toList()
        );
    }
    @GetMapping("/{depositNumber}/getLoans")
    public ResponseEntity<List<Loan>> getLoans(@PathVariable String depositNumber){
        return ResponseEntity.ok().body(
            service.listOfLoanForDeposit(depositNumber)
        );
    }
    @GetMapping("/{depositNumber}/payInstallment")
    public ResponseEntity<Loan> payInstallment(@PathVariable String depositNumber){
        return ResponseEntity.ok().body(
            service.payInstallment(depositNumber)
        );
    }
}
