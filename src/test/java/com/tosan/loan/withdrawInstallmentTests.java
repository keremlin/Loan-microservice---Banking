package com.tosan.loan;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.tosan.loan.model.Loan;
import com.tosan.loan.model.LoanState;
import com.tosan.loan.model.LoanType;
import com.tosan.loan.repository.DepositResources;
import com.tosan.loan.repository.LoanRepository;
import com.tosan.loan.services.LoanServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class withdrawInstallmentTests {

    @InjectMocks
    private LoanServiceImpl service;
    @Mock
    private LoanRepository repo;
    @Mock
    private DepositResources depositRepo;

    private List<Loan> list;
    
    @BeforeEach
    void beforeEach() {

        list = new ArrayList<>();
        Loan loan = new Loan(1, LoanType.EZDEVAJ, 20, null, LoanState.open, 12000, 12, 0, 0, "321212221");
        Loan loan2 = new Loan(2, LoanType.BUY, 20, null, LoanState.open, 112000, 122, 0, 0, "321212222");
        list.add(loan);
        list.add(loan2);

        when(depositRepo.isDepositValid(ArgumentMatchers.<String>any()))
                .thenAnswer(Q -> true);

        when(repo.findByDepositNumber(ArgumentMatchers.<String>any()))
                .thenAnswer(Q -> list);

        when(repo.save(ArgumentMatchers.<Loan>any()))
                .thenAnswer(i -> i.getArguments()[0]);

        when(depositRepo.withdrawInstallment(ArgumentMatchers.<String>any(), ArgumentMatchers.<Integer>any()))
                .thenAnswer(R -> true);
    }

    @Test
    void checkWithdrawInstallment(){
        assertEquals(11,service.payInstallment("10001").getInstallmentNumber());
    }
    
}
