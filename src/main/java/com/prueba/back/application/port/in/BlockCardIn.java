package com.prueba.back.application.port.in;

import com.prueba.back.exeption.BusinessExeption;

public interface BlockCardIn {
	
	public Boolean BlockCard(String number) throws BusinessExeption;

}
