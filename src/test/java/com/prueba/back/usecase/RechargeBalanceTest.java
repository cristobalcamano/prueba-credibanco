package com.prueba.back.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.prueba.back.application.port.out.CardBankOut;
import com.prueba.back.application.port.out.RechargeBalanceOut;
import com.prueba.back.application.service.RechargeBalanceUseCase;
import com.prueba.back.domain.BankCardDomain;
import com.prueba.back.exeption.BusinessExeption;

public class RechargeBalanceTest {
	
	@InjectMocks
	private RechargeBalanceUseCase balanceUseCase;	

	@Mock
	private RechargeBalanceOut rechargeBalanceOut;
	@Mock
	private CardBankOut cardBankOut;

	@BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
	
	@Test
	public void rechargeBalance() throws BusinessExeption{
		
		BankCardDomain bankCardDomain = new BankCardDomain();
		
		when(cardBankOut.findCardBank(bankCardDomain.getCardId())).thenReturn(bankCardDomain);
		
		doNothing().when(rechargeBalanceOut).rechargeBalance(bankCardDomain);
		
		Boolean response = balanceUseCase.rechargeBalance(bankCardDomain);
		verify(rechargeBalanceOut, times(1)).rechargeBalance(bankCardDomain);
		
		assertEquals(true, response);

	}

}
