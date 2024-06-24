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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

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
	
	@Operation(summary = "Realizar una compra", description = "Se consulta el balance apartir del numero de tarjeta")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "302", description = "Operación exitosa"),
        @ApiResponse(responseCode = "404", description = "Error controlado"),
        @ApiResponse(responseCode = "500", description = "Error del servidor")
    })
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
	
	@Operation(summary = "Consultar una transaccion", description = "Se consulta el balance apartir del numero de tarjeta")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "302", description = "Operación exitosa"),
        @ApiResponse(responseCode = "404", description = "Error controlado"),
        @ApiResponse(responseCode = "500", description = "Error del servidor")
    })
	@GetMapping(path = "/{transactionId}")
	public ResponseEntity<?> checkTransaction(@Parameter(description = "Numero de transaccion", required = true)
	@PathVariable("transactionId") String transactionId){
		try {
			TransactionBuyDomain transaction = CheckTransactionIn.checkTransaction(transactionId);
			return new ResponseEntity(transaction,HttpStatus.FOUND);
		} catch (BusinessExeption e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Operation(summary = "Anular una transaccion", description = "Se Anula una transaccion donde la creacion no supere 24 horas con"
			+ "el numero de transaccion y de tarjeta")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "302", description = "Operación exitosa"),
        @ApiResponse(responseCode = "404", description = "Error controlado"),
        @ApiResponse(responseCode = "500", description = "Error del servidor")
    })
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
