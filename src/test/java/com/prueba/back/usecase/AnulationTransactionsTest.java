package com.prueba.back.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.prueba.back.application.port.out.AnulationTransactionOut;
import com.prueba.back.application.port.out.CardBankOut;
import com.prueba.back.application.port.out.CheckTransactionOut;
import com.prueba.back.application.port.out.TransactionOut;
import com.prueba.back.application.service.AnulationTransactionsUseCase;
import com.prueba.back.domain.BankCardDomain;
import com.prueba.back.domain.TransactionBuyDomain;
import com.prueba.back.exeption.BusinessExeption;
import com.prueba.back.util.EnviromentGlobal;

public class AnulationTransactionsTest {
	
	@InjectMocks
	private AnulationTransactionsUseCase anulationTransactionsUseCase;
	
	@Mock
	private CheckTransactionOut checkTransactionOut;
	@Mock
	private AnulationTransactionOut anulationTransactionOut;
	@Mock
	private CardBankOut cardBankOut;
	@Mock
	private TransactionOut transactionOut;
	
	@BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
	
	@Test
	public void anulationTransaction() throws BusinessExeption {

		TransactionBuyDomain transactionBuyDomain = new TransactionBuyDomain();
		transactionBuyDomain.setCardId("card1");
		transactionBuyDomain.setTransactionId("Transaction1");
		
		BankCardDomain card = new BankCardDomain();
		card.setBalance(20.2);

		when(cardBankOut.findCardBank("card1")).thenReturn(card);
		
		TransactionBuyDomain transaction = new TransactionBuyDomain();
		transaction.setSalesValue(30.2);
		transaction.setCreatedDate(LocalDateTime.now());
		
		when(checkTransactionOut.checkTransaction("Transaction1")).thenReturn(transaction);

		transactionOut.validateStatusTransaction(transactionBuyDomain.getTransactionId(), EnviromentGlobal.ACTIVE_STATUS);

		when(transactionOut.validateStatusTransaction("Transaction1", EnviromentGlobal.ACTIVE_STATUS)).thenReturn(transaction);

		doNothing().when(cardBankOut).changeBalance("card1", transaction.getSalesValue() + card.getBalance());
		
		doNothing().when(anulationTransactionOut).anulationTransaction(transactionBuyDomain);
		
		Boolean response = anulationTransactionsUseCase.anulationTransaction(transactionBuyDomain);
		
		verify(cardBankOut, times(1)).changeBalance("card1", transaction.getSalesValue() + card.getBalance());
		verify(anulationTransactionOut, times(1)).anulationTransaction(transactionBuyDomain);
		
		assertEquals(true, response);

	}
	
}
