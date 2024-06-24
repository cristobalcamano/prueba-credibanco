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
import com.prueba.back.util.EnviromentGlobal;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;

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

	@Operation(summary = "Generar una tarjeta", description = "Generar una tarjeta a partir del codigo ")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "302", description = "Operación exitosa",
        		content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = String.class),
                examples = @ExampleObject(name = "Ejemplo de generacion de tarjeta",
                    value = "1234567890123456"))),
        @ApiResponse(responseCode = "404", description = "Error controlado",
        		content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = String.class),
                examples = @ExampleObject(name = "Error controlado",
                    value = "No se ha generado la tarjeta por favor volver a intentar"))),
        @ApiResponse(responseCode = "500", description = "Error del servidor",
        		content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = String.class),
                examples = @ExampleObject(name = "Error del servidor",
                    value = "Error en el servidor, Por favor intentar mas tarde")))
    })
	@GetMapping(path = "/{productId}/number")
	public ResponseEntity<?> generateCard(@Parameter(description = "Numero del tipo de producto", required = true)
	@PathVariable("productId") Long productId){
		try {
			String tarjeta = generateCardIn.create(productId);
			return new ResponseEntity(tarjeta,HttpStatus.FOUND);
		} catch (BusinessExeption e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity(EnviromentGlobal.INTERNAL_SERVER_ERROR_MSS,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Operation(summary = "Activar tarjeta tarjeta", description = "Activar una tarjeta apartir de su numero de tarjeta")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "302", description = "Operación exitosa",
        		content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = String.class),
                examples = @ExampleObject(name = "Ejemplo de activacion de tarjeta",
                    value = "true"))),
        @ApiResponse(responseCode = "404", description = "Error controlado",
        		content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = String.class),
                examples = @ExampleObject(name = "Ejemplo de generacion de tarjeta",
                    value = "El tipo de producto 1000023251567542 no encontrado"))),
        @ApiResponse(responseCode = "500", description = "Error del servidor",
        		content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = String.class),
                examples = @ExampleObject(name = "Error del servidor",
                    value = "Error en el servidor, Por favor intentar mas tarde")))
    })
	@PostMapping(path = "/enroll")
	public ResponseEntity<?> activeCard(@RequestBody BankCardDomain cardBank){
		try {
			Boolean tarjeta = activateCardIn.activateCard(cardBank.getCardId());
			return new ResponseEntity(tarjeta,HttpStatus.FOUND);
		} catch (BusinessExeption e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity(EnviromentGlobal.INTERNAL_SERVER_ERROR_MSS,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Operation(summary = "Bloquear tarjeta", description = "Bloquear una tarjeta apartir de su numero de tarjeta")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "302", description = "Operación exitosa",
        		content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = String.class),
                examples = @ExampleObject(name = "Ejemplo de bloqueo de tarjeta",
                    value = "true"))),
        @ApiResponse(responseCode = "404", description = "Error controlado",
        		content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = String.class),
                examples = @ExampleObject(name = "Ejemplo de generacion de tarjeta",
                    value = "El tipo de producto 1000025256724469 no encontrado"))),
        @ApiResponse(responseCode = "500", description = "Error del servidor",
        		content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = String.class),
                examples = @ExampleObject(name = "Error del servidor",
                    value = "Error en el servidor, Por favor intentar mas tarde")))
    })
	@DeleteMapping(path = "/{cardId}")
	public ResponseEntity<?> BlockCard(@Parameter(description = "Numero de la tarjeta", required = true)
			@PathVariable("cardId") String cardId){
		try {
			Boolean tarjeta = blockCardIn.BlockCard(cardId);
			return new ResponseEntity(tarjeta,HttpStatus.FOUND);
		} catch (BusinessExeption e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity(EnviromentGlobal.INTERNAL_SERVER_ERROR_MSS,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Operation(summary = "Recargar tarjeta", description = "Recarrgar el balance de una tarjeta apartir de su numero de tarjeta")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "302", description = "Operación exitosa",
        		content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = String.class),
                examples = @ExampleObject(name = "Ejemplo de recarga de tarjeta",
                    value = "true"))),
        @ApiResponse(responseCode = "404", description = "Error controlado",
        		content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = String.class),
                examples = @ExampleObject(name = "Ejemplo de generacion de tarjeta",
                    value = "El tipo de producto 1000023251567542 no encontrado"))),
        @ApiResponse(responseCode = "500", description = "Error del servidor",
        		content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = String.class),
                examples = @ExampleObject(name = "Error del servidor",
                    value = "Error en el servidor, Por favor intentar mas tarde")))
    })
	@PostMapping(path = "/balance")
	public ResponseEntity<?> rechargeCard(@RequestBody BankCardDomain cardBank) throws BusinessExeption{
		try {
			Boolean response = rechargeBalanceIn.rechargeBalance(cardBank);
			return new ResponseEntity(response,HttpStatus.FOUND);
		} catch (BusinessExeption e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity(EnviromentGlobal.INTERNAL_SERVER_ERROR_MSS,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Operation(summary = "Consultar el balance de la tarjeta", description = "Se consulta el balance apartir del numero de tarjeta")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "302", description = "Operación exitosa",
        		content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = String.class),
                examples = @ExampleObject(name = "Ejemplo de consulta de balance de tarjeta",
                    value = "12.2"))),
        @ApiResponse(responseCode = "404", description = "Error controlado",
        		content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = String.class),
                examples = @ExampleObject(name = "Ejemplo de generacion de tarjeta",
                    value = "El tipo de producto 1000026346736256 no encontrado"))),
        @ApiResponse(responseCode = "500", description = "Error del servidor",
        		content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = String.class),
                examples = @ExampleObject(name = "Ejemplo de generacion de tarjeta",
                    value = "1234567890123456")))
    })
	@GetMapping(path = "/balance/{cardId}")
	public ResponseEntity<?> checkBalance(@Parameter(description = "Numero de la tarjeta", required = true)
	@PathVariable("cardId") String cardId){
		try {
			Double response = consultBalanceIn.consultBalance(cardId);
			return new ResponseEntity(response,HttpStatus.FOUND);
		} catch (BusinessExeption e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity(EnviromentGlobal.INTERNAL_SERVER_ERROR_MSS,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
