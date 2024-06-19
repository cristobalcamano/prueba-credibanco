package com.prueba.back.adapter.persistence.entity;

import com.prueba.back.domain.StatusDomain;

public class StatusMapper {
	
	public static StatusDomain entityToDomain(StatusEntity entity) {
		StatusDomain d = new StatusDomain();
		d.setName(entity.getName());
		d.setDescription(entity.getDescription());
        return d;
    }

}
