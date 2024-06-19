package com.prueba.back.application.service;

import org.springframework.stereotype.Component;

import com.prueba.back.application.port.in.GenerateCardIn;
import com.prueba.back.application.port.out.CardBankOut;
import com.prueba.back.application.port.out.ProductTypeOut;
import com.prueba.back.exeption.BusinessExeption;
import com.prueba.back.util.EnviromentGlobal;

@Component
public class GenerateCardNumberCase implements GenerateCardIn{
	
	private final CardBankOut cardBankOut;
	
	private final ProductTypeOut productTypeOut;

	public GenerateCardNumberCase(CardBankOut cardBankOut, ProductTypeOut productTypeOut) {
		super();
		this.cardBankOut = cardBankOut;
		this.productTypeOut=productTypeOut;
	}

	@Override
	public String create(Long productType) throws BusinessExeption{
		
		//Validar que el tipo de producto exista(El metodo lanzara una exepcion de no existir)
		productTypeOut.validateProductType(productType);
		
		String tarjeta = cardBankOut.generatCardBank(productType);
		if(tarjeta != null && tarjeta.length() == EnviromentGlobal.NUMBER_CARD_CHARACTERS) {
			return tarjeta;
		} else {
			throw new BusinessExeption("No se ha generado la tarjeta por favor volver a intentar");
		}
	}

}
