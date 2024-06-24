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
import com.prueba.back.util.EnviromentGlobal;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
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
    		@ApiResponse(responseCode = "302", description = "Operación exitosa",
            		content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = String.class),
                    examples = @ExampleObject(name = "Ejemplo de crespuesta de compra",
                        value = "1234567890"))),
            @ApiResponse(responseCode = "404", description = "Error controlado",
            		content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = String.class),
                    examples = @ExampleObject(name = "Ejemplo de generacion de tarjeta",
                        value = "El tipo de producto 1000023201567542 no encontrado"))),
            @ApiResponse(responseCode = "500", description = "Error del servidor",
            		content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = String.class),
                    examples = @ExampleObject(name = "Error del servidor",
                        value = "Error en el servidor, Por favor intentar mas tarde")))
    })
	@PostMapping(path = "/purchase")
	public ResponseEntity<?> buyTransaction(@RequestBody BankCardDomain bankCard){
		try {
			String transaction = BuyTransactionIn.buyTransaction(bankCard);
			return new ResponseEntity(transaction, HttpStatus.FOUND);
		} catch (BusinessExeption e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity(EnviromentGlobal.INTERNAL_SERVER_ERROR_MSS,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Operation(summary = "Consultar una transaccion", description = "Se consulta el balance apartir del numero de tarjeta")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "302", description = "Operación exitosa",
            		content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = String.class),
                    examples = @ExampleObject(name = "Ejemplo de consulta de transaccion",
                        value = "{\"id\":1,\"transactionId\":\"5478844461\",\"statusDomain\":{\r\n"
                        		+ "\"name\":\"activo\",\"description\":\"Estadoactivo\"},\r\n"
                        		+ "\"bankCardDomain\":{\"id\":2,\"cardId\":\"1000023251567542\",\r\n"
                        		+ "\"number\":\"1000023251567542\",\"expiration\":\"06/2027\",\r\n"
                        		+ "\"status\":\"activo\",\"typeProduct\":{\"id\":100002,\r\n"
                        		+ "\"name\":\"Debito\",\"description\":\"Tarjetadedebito\"\r\n"
                        		+ "},\"balance\":3240.0},\"salesValue\":1.0,\r\n"
                        		+ "\"createdDate\":\"2024-06-23T22:22:26.826021\"}"))),
            @ApiResponse(responseCode = "404", description = "Error controlado",
            		content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = String.class),
                    examples = @ExampleObject(name = "Ejemplo de generacion de tarjeta",
                        value = "El tipo de producto 5478844461 no encontrado"))),
            @ApiResponse(responseCode = "500", description = "Error del servidor",
            		content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = String.class),
                    examples = @ExampleObject(name = "Error del servidor",
                        value = "Error en el servidor, Por favor intentar mas tarde")))
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
			return new ResponseEntity(EnviromentGlobal.INTERNAL_SERVER_ERROR_MSS,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Operation(summary = "Anular una transaccion", description = "Se Anula una transaccion donde la creacion no supere 24 horas con"
			+ "el numero de transaccion y de tarjeta")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "302", description = "Operación exitosa",
            		content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = String.class),
                    examples = @ExampleObject(name = "Ejemplo de anular transaccion",
                        value = "true"))),
            @ApiResponse(responseCode = "404", description = "Error controlado",
            		content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = String.class),
                    examples = @ExampleObject(name = "Ejemplo de generacion de tarjeta",
                        value = "El tipo de producto 5478844461 no encontrado"))),
            @ApiResponse(responseCode = "500", description = "Error del servidor",
            		content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = String.class),
                    examples = @ExampleObject(name = "Error del servidor",
                        value = "Error en el servidor, Por favor intentar mas tarde")))
    })
	@PostMapping(path = "/anulation")
	public ResponseEntity<?> anulationTransaction(@RequestBody TransactionBuyDomain transaction){
		try {
			Boolean response = AnulationTransactionIn.anulationTransaction(transaction);
			return new ResponseEntity(response,HttpStatus.FOUND);
		} catch (BusinessExeption e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity(EnviromentGlobal.INTERNAL_SERVER_ERROR_MSS,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
