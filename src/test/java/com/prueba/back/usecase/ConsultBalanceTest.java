package com.prueba.back.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.prueba.back.application.port.out.CardBankOut;
import com.prueba.back.application.service.ConsultBalanceUseCase;
import com.prueba.back.domain.BankCardDomain;
import com.prueba.back.exeption.BusinessExeption;

public class ConsultBalanceTest {
	
	@InjectMocks
	private ConsultBalanceUseCase balanceUseCase;
	
	@Mock
	private CardBankOut cardBankOut;
	
	@BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
	

	@Test
	public void consultBalance() throws BusinessExeption {
		
		BankCardDomain bankCardDomain = new BankCardDomain();
		bankCardDomain.setBalance(20.52);
		
		when(cardBankOut.findCardBank("Card123")).thenReturn(bankCardDomain);
		
		double response = balanceUseCase.consultBalance("Card123");
		
		assertEquals(20.52, response);
		
	}

}
