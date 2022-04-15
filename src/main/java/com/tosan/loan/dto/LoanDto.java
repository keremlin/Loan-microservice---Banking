package com.tosan.loan.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.tosan.loan.model.*;

import org.springframework.beans.BeanUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanDto {
    
    @NotNull
    private LoanType type;

    @Min(0)
    private int rate = 0;

    @Min(1)
    private int amount;

    @Min(1)
    private int installmentNumber;

    @NotBlank
    private String depositNumber;

    public Loan convert() {
        Loan temp = new Loan();
        BeanUtils.copyProperties(this, temp);
        return temp;
    }
}
