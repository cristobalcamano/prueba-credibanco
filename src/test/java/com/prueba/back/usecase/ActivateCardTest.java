package com.prueba.back.usecase;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.prueba.back.application.port.out.CardBankOut;
import com.prueba.back.application.port.out.StatusCardOut;
import com.prueba.back.application.service.ActivateCardUseCase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ActivateCardTest {
	
	@InjectMocks
    private ActivateCardUseCase ActivateCardUseCase;
	
	@Mock
	private CardBankOut cardBankOut;
	@Mock
	private StatusCardOut statusCardOut;
	
	@BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
	
	@Test
    void activateCard() throws Exception{
		when(cardBankOut.findCardBank("1")).thenReturn(null);
		doNothing().when(statusCardOut).activateCard("1");
		
		ActivateCardUseCase.activateCard("1");
		
		verify(cardBankOut, times(1)).findCardBank("1");
		verify(statusCardOut, times(1)).activateCard("1");
    }

}
