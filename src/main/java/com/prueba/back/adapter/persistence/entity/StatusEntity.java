package com.prueba.back.adapter.persistence.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
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
@Table(name = "status_tbl")
public class StatusEntity implements Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3788025440781466769L;

	@Id
    @GeneratedValue
    private Long id;

	@Column(name = "name_status")
    private String name;
	@Column(name = "description_status")
    private String description;
    
    public StatusEntity() {
		super();
	}
    
	public StatusEntity(Long id) {
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
