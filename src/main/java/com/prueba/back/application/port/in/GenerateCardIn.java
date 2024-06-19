package com.prueba.back.application.port.in;

import com.prueba.back.exeption.BusinessExeption;

public interface GenerateCardIn {
	
	public String create(Long productType) throws BusinessExeption;

}
