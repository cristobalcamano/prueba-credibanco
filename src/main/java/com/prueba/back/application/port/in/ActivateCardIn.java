package com.prueba.back.application.port.in;

import com.prueba.back.exeption.BusinessExeption;

public interface ActivateCardIn {
	
	public Boolean activateCard(String number) throws BusinessExeption;

}
