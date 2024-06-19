package com.prueba.back.adapter.persistence.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "producto_type")
public class ProductoTypeEntity implements Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7010386861878963086L;

	@Id
    @GeneratedValue
	private Long id;
	
	private String name;
    private String description;
    
    public ProductoTypeEntity() {
		super();
	}
    
	public ProductoTypeEntity(Long id) {
		super();
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
    
}
