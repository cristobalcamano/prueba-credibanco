package com.prueba.back.usecase;

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
import com.prueba.back.application.port.out.StatusCardOut;
import com.prueba.back.application.service.BlockCardUseCase;
import com.prueba.back.domain.BankCardDomain;
import com.prueba.back.exeption.BusinessExeption;

public class BlockCardTest {
	
	@InjectMocks
	private BlockCardUseCase blockCardUseCase;
	
	@Mock
	private CardBankOut cardBankOut;
	@Mock
	private StatusCardOut statusCardOut;
	
	@BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
	
	@Test
	public void BlockCard() throws BusinessExeption{
		
		BankCardDomain transaction = new BankCardDomain();

		when(cardBankOut.findCardBank("card1")).thenReturn(transaction);
		doNothing().when(statusCardOut).blockCard("card1");
		
		blockCardUseCase.BlockCard("card1");
		
		verify(statusCardOut, times(1)).blockCard("card1");
		
	}

}
