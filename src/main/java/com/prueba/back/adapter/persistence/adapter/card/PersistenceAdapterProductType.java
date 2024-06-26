package com.prueba.back.adapter.persistence.adapter.card;

import org.springframework.stereotype.Component;

import com.prueba.back.adapter.persistence.entity.ProductoTypeMapper;
import com.prueba.back.adapter.persistence.repository.ProductTypeRepository;
import com.prueba.back.application.port.out.ProductTypeOut;
import com.prueba.back.domain.ProductTypeDomain;
import com.prueba.back.exeption.BusinessExeption;

@Component
public class PersistenceAdapterProductType implements ProductTypeOut{
	
	private final ProductTypeRepository productTypeRepository;

	public PersistenceAdapterProductType(ProductTypeRepository productTypeRepository) {
		super();
		this.productTypeRepository = productTypeRepository;
	}

	/**
	 * Metodo para consultar el tipo de producto por du identificador en BBDD
	 * 
	 * @param number identificador del tipo de producto en BBDD
	 * @return ProductTypeDomain objeto representativo del tipo de producto 
	 */
	@Override
	public ProductTypeDomain validateProductType(Long number) throws BusinessExeption{
		ProductTypeDomain producto = productTypeRepository.findById(number)
				.map(ProductoTypeMapper::entityToDomain)
				.orElseThrow(() -> new BusinessExeption("El tipo de producto " + number + " no encontrado"));
		return producto;
	}

}
