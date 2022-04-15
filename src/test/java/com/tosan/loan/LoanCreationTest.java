package com.tosan.loan;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.tosan.loan.dto.LoanDto;
import com.tosan.loan.model.Loan;
import com.tosan.loan.model.LoanState;
import com.tosan.loan.model.LoanType;
import com.tosan.loan.repository.LoanRepository;
import com.tosan.loan.services.LoanServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LoanCreationTest {

    @InjectMocks
    private LoanServiceImpl service;

    @Mock
    private LoanRepository repo;
    
    private Loan loan;
    private LoanDto loanDto;

    @BeforeEach
    void beforeEach(){
        loan=new Loan(1,LoanType.EZDEVAJ, 20, null, LoanState.open, 12000, 12, 0, 0, "321212221");
        loanDto=new LoanDto(LoanType.EZDEVAJ, 20, 12000, 12, "321212221");
        when(repo.save(ArgumentMatchers.<Loan>any()))
				.thenAnswer(i -> i.getArguments()[0]);
    }

    @Test
    void createLoan() {
        Loan temp = service.createLoan(loanDto);
        assertEquals(loan.getAmount(), temp.getAmount());
        assertEquals(temp.getAmountOfEachInstallment(), LoanServiceImpl.calculateEachInstallment(loan.getAmount(),
                loan.getInstallmentNumber(), loan.getRate()));
    }
    @Test
    void checkCreateLoanException(){
        
    }
    @Test
    void checkCreateLoanValidation(){

    }
}
