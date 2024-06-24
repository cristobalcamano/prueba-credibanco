package com.prueba.back.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.prueba.back.application.port.out.CardBankOut;
import com.prueba.back.application.port.out.ProductTypeOut;
import com.prueba.back.application.service.GenerateCardNumberUseCase;
import com.prueba.back.exeption.BusinessExeption;

public class GenerateCardNumberTest {
	
	@InjectMocks
	private GenerateCardNumberUseCase cardNumberUseCase;
	
	@Mock
	private CardBankOut cardBankOut;
	@Mock
	private ProductTypeOut productTypeOut;

	@BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
	
	@Test
	public void createTest() throws BusinessExeption {

		when(productTypeOut.validateProductType(1L)).thenReturn(null);
		
		when(cardBankOut.generatCardBank(1L)).thenReturn("Tarjeta123456789");
		
		String response = cardNumberUseCase.create(1L);
		
		assertEquals("Tarjeta123456789", response);
	}
	
}
