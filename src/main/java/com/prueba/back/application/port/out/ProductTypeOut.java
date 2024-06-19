package com.prueba.back.application.port.out;

import com.prueba.back.domain.ProductTypeDomain;
import com.prueba.back.exeption.BusinessExeption;

public interface ProductTypeOut {
	
	public ProductTypeDomain validateProductType(Long number) throws BusinessExeption;

}
