package com.prueba.back.application.port.in;

import com.prueba.back.exeption.BusinessExeption;

public interface ConsultBalanceIn {
	
	public Double consultBalance(String cardId) throws BusinessExeption;

}
