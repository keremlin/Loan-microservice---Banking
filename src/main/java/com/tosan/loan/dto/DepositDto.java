package com.tosan.loan.dto;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepositDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @NotBlank(message = "nin is mandatory")
    @Pattern(regexp="^([0-9]){10}$",message = "Nin must have 10 digits only")
    @Size(min = 10,max = 10)
    String nin;

    @NotBlank
    int depositNumber;

    int Balance=0;
    
    Date startDate;
    Date endDate;
}
