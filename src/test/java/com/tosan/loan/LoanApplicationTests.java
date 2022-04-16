package com.tosan.loan;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.tosan.loan.model.*;
import com.tosan.loan.repository.*;
import com.tosan.loan.services.LoanServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LoanApplicationTests {

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
		when(repo.findAll()).thenReturn(list);
		when(repo.findByDepositNumber(ArgumentMatchers.<String>any()))
				.thenAnswer(i -> findLoanByDepositNumber(i.getArguments()[0] + ""));

		when(depositRepo.isDepositValid(ArgumentMatchers.<String>any()))
				.thenAnswer(Q -> true);
	}

	private List<Loan> findLoanByDepositNumber(String depositNumber) {
		return list.stream().filter(x -> x.getDepositNumber().startsWith(depositNumber)).collect(Collectors.toList());
	}

	@Test
	void calculateInstallment() {
		assertEquals(1108, service.calculateInstallments(12000, 12, 20));
	}

	@Test
	void calculateProfit() {
		assertEquals(1300, LoanServiceImpl.calculateProfit(12000, 12, 20));
	}

	@Test
	void calculateEachInstallment() {
		assertEquals(1108, (int) LoanServiceImpl.calculateEachInstallment(12000, 12, 20));
	}

	@Test
	void payInstallment() {
		assertTrue(false);
	}

	@Test
	void toList() {
		assertEquals(2, list.size());
	}

	@Test
	void listOfLoanForDeposit() {
		assertEquals(1,service.listOfLoanForDeposit("321212222").size());
		assertEquals(2,service.listOfLoanForDeposit("32121222").size());
	}

}
