package com.prueba.back.adapter.persistence.entity;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "bank_card")
public class BankCardEntity implements Serializable{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5544171736619828861L;
	
	@Id
    @GeneratedValue
	private Long id;
	@Column(name = "card_id")
	private String number;
	private Double balance;
	@Column(name = "cardholder_name")
	private String cardholderName;
	private String expiration;
	@ManyToOne
    @JoinColumn(name = "status_fk", nullable = false)
	private StatusEntity status;
	@ManyToOne
    @JoinColumn(name = "product_type_fk", nullable = false)
	private ProductoTypeEntity productType;
	
	public BankCardEntity() {
		super();
	}

	public BankCardEntity(Long id) {
		super();
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCardholderName() {
		return cardholderName;
	}
	public void setCardholderName(String cardholderName) {
		this.cardholderName = cardholderName;
	}
	public String getExpiration() {
		return expiration;
	}
	public void setExpiration(String expiration) {
		this.expiration = expiration;
	}
	public StatusEntity getStatus() {
		return status;
	}
	public void setStatus(StatusEntity status) {
		this.status = status;
	}
	public ProductoTypeEntity getProductType() {
		return productType;
	}
	public void setProductType(ProductoTypeEntity productType) {
		this.productType = productType;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
}
