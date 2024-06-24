package com.prueba.back.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.prueba.back.application.port.out.CheckTransactionOut;
import com.prueba.back.application.service.CheckTransactionUseCase;
import com.prueba.back.domain.TransactionBuyDomain;
import com.prueba.back.exeption.BusinessExeption;

public class CheckTransactionTest {
	
	@InjectMocks
	private CheckTransactionUseCase checkTransactionUseCase;
	@Mock
	private CheckTransactionOut checkTransactionOut;
	
	@BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
	
	@Test
	public void checkTransaction() throws BusinessExeption{
		
		TransactionBuyDomain response = new TransactionBuyDomain();
		response.setId(1L);
		
		when(checkTransactionOut.checkTransaction("transaction123")).thenReturn(response);
		
		response = checkTransactionUseCase.checkTransaction("transaction123");

		assertEquals(1L, response.getId());		
		
	}

}
