package com.prueba.back.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.prueba.back.application.port.out.BuyTransactionOut;
import com.prueba.back.application.port.out.CardBankOut;
import com.prueba.back.application.port.out.StatusCardOut;
import com.prueba.back.application.service.BuyTransactionUseCase;
import com.prueba.back.domain.BankCardDomain;
import com.prueba.back.exeption.BusinessExeption;
import com.prueba.back.util.EnviromentGlobal;

public class BuyTransactionTest {

	@InjectMocks
	private BuyTransactionUseCase buyTransactionUseCase;

	@Mock
	private BuyTransactionOut buyTransactionOut;
	@Mock
	private CardBankOut cardBankOut;
	@Mock
	private StatusCardOut statusCardOut;
	
	@BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
	
	@Test
	public void buyTransaction() throws BusinessExeption {
		
		BankCardDomain bankCardDomain = new BankCardDomain();
		bankCardDomain.setCardId("cardDemo");
		bankCardDomain.setPrice(20.2);
		
		BankCardDomain bankCard = new BankCardDomain();
		bankCard.setBalance(30.0);
		when(cardBankOut.findCardBank(bankCardDomain.getCardId())).thenReturn(bankCard);
		
		doNothing().when(statusCardOut).validateStatusCard(bankCardDomain.getCardId(), EnviromentGlobal.ACTIVE_STATUS);
		
		doNothing().when(cardBankOut).changeBalance(bankCardDomain.getCardId(), bankCard.getBalance() - bankCardDomain.getPrice());
		
		when(buyTransactionOut.buyTransaction(bankCardDomain, bankCard.getId())).thenReturn("TransactionId");
		
		String response = buyTransactionUseCase.buyTransaction(bankCardDomain);

		verify(statusCardOut, times(1)).validateStatusCard(bankCardDomain.getCardId(), EnviromentGlobal.ACTIVE_STATUS);
		verify(cardBankOut, times(1)).changeBalance(bankCardDomain.getCardId(), bankCard.getBalance() - bankCardDomain.getPrice());
		
		assertEquals("TransactionId", response);
		
	}

}
