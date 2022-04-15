package com.tosan.loan.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Loan {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotNull
    private LoanType type;

    @Min(0)
    private int rate=0;

    @NotNull
    private Date date=new Date();

    @NotNull
    private LoanState state;

    @Min(1)
    private int amount;
    
    @Min(1)
    private int installmentNumber;

    @Min(0)
    private int installmentPassed;

    @Min(0)
    private int amountOfEachInstallment;

    @NotBlank
    private String depositNumber;
}
