package com.prueba.back.adapter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.back.application.port.in.ActivateCardIn;
import com.prueba.back.application.port.in.BlockCardIn;
import com.prueba.back.application.port.in.ConsultBalanceIn;
import com.prueba.back.application.port.in.GenerateCardIn;
import com.prueba.back.application.port.in.RechargeBalanceIn;
import com.prueba.back.domain.BankCardDomain;
import com.prueba.back.exeption.BusinessExeption;

@RestController
@RequestMapping("${path-card}")
public class ApiCard {
	
	private final GenerateCardIn generateCardIn;
	
	private final ActivateCardIn activateCardIn;
	
	private final BlockCardIn blockCardIn;
	
	private final RechargeBalanceIn rechargeBalanceIn;
	
	private final ConsultBalanceIn consultBalanceIn;
	
	public ApiCard(GenerateCardIn generateCardIn, BlockCardIn blockCardIn, ActivateCardIn activateCardIn,
			RechargeBalanceIn rechargeBalanceIn, ConsultBalanceIn consultBalanceIn) {
		super();
		this.generateCardIn = generateCardIn;
		this.blockCardIn=blockCardIn;
		this.activateCardIn=activateCardIn;
		this.rechargeBalanceIn=rechargeBalanceIn;
		this.consultBalanceIn=consultBalanceIn;
	}

	@GetMapping(path = "/{productId}/number")
	public ResponseEntity<?> generateCard(@PathVariable("productId") Long productId){
		try {
			String tarjeta = generateCardIn.create(productId);
			return new ResponseEntity(tarjeta,HttpStatus.FOUND);
		} catch (BusinessExeption e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(path = "/enroll")
	public ResponseEntity<?> activeCard(@RequestBody BankCardDomain cardBank){
		try {
			Boolean tarjeta = activateCardIn.activateCard(cardBank.getCardId());
			return new ResponseEntity(tarjeta,HttpStatus.FOUND);
		} catch (BusinessExeption e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(path = "/{cardId}")
	public ResponseEntity<?> BlockCard(@PathVariable("cardId") String cardId){
		try {
			Boolean tarjeta = blockCardIn.BlockCard(cardId);
			return new ResponseEntity(tarjeta,HttpStatus.FOUND);
		} catch (BusinessExeption e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(path = "/balance")
	public ResponseEntity<?> rechargeCard(@RequestBody BankCardDomain cardBank) throws BusinessExeption{
		try {
			Boolean response = rechargeBalanceIn.rechargeBalance(cardBank);
			return new ResponseEntity(response,HttpStatus.FOUND);
		} catch (BusinessExeption e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(path = "/balance/{cardId}")
	public ResponseEntity<?> checkBalance(@PathVariable("cardId") String cardId){
		try {
			Double response = consultBalanceIn.consultBalance(cardId);
			return new ResponseEntity(response,HttpStatus.FOUND);
		} catch (BusinessExeption e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
