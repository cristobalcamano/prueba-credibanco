package com.prueba.back.application.port.out;

import com.prueba.back.exeption.BusinessExeption;

public interface StatusCardOut {
	
	public void activateCard(String number);

	public void blockCard(String number);

	public void validateStatusCard(String number, Long status)  throws BusinessExeption; 

}
