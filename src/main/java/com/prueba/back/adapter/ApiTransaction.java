package com.prueba.back.adapter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.back.application.port.in.AnulationTransactionIn;
import com.prueba.back.application.port.in.BuyTransactionIn;
import com.prueba.back.application.port.in.CheckTransactionIn;
import com.prueba.back.domain.BankCardDomain;
import com.prueba.back.domain.TransactionBuyDomain;
import com.prueba.back.exeption.BusinessExeption;

@RestController
@RequestMapping("${path-transaction}")
public class ApiTransaction {
	
	private final AnulationTransactionIn AnulationTransactionIn;
	private final CheckTransactionIn CheckTransactionIn;
	private final BuyTransactionIn BuyTransactionIn;
	
	
	public ApiTransaction(AnulationTransactionIn AnulationTransactionIn, CheckTransactionIn CheckTransactionIn,
			BuyTransactionIn BuyTransactionIn) {
		this.BuyTransactionIn=BuyTransactionIn;
		this.CheckTransactionIn=CheckTransactionIn;
		this.AnulationTransactionIn=AnulationTransactionIn;
	}
	
	
	@PostMapping(path = "/purchase")
	public ResponseEntity<?> buyTransaction(@RequestBody BankCardDomain bankCard){
		try {
			String transaction = BuyTransactionIn.buyTransaction(bankCard);
			return new ResponseEntity(transaction, HttpStatus.FOUND);
		} catch (BusinessExeption e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping(path = "/{transactionId}")
	public ResponseEntity<?> checkTransaction(@PathVariable("transactionId") String transactionId){
		try {
			TransactionBuyDomain transaction = CheckTransactionIn.checkTransaction(transactionId);
			return new ResponseEntity(transaction,HttpStatus.FOUND);
		} catch (BusinessExeption e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(path = "/anulation")
	public ResponseEntity<?> anulationTransaction(@RequestBody TransactionBuyDomain transaction){
		try {
			Boolean response = AnulationTransactionIn.anulationTransaction(transaction);
			return new ResponseEntity(response,HttpStatus.FOUND);
		} catch (BusinessExeption e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
