package com.prueba.back.domain;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "El ID único del usuario", example = "1")
public class BankCardDomain {

	@Schema(description = "Identificador de la tarjeta en BBDD", example = "1")
	private Long id;
	@Schema(description = "Numero de la tarjeta", example = "1234567890123456")
	private String cardId;
	@Schema(description = "Numero de la tarjeta", example = "1234567890123456")
    private String number;
	@Schema(description = "usuario", example = "Carlos Andres")
    private String holderName;
	@Schema(description = "Fecha de vencimiento de la tarjeta", example = "16/27")
    private String expiration;
	@Schema(description = "Estado de la tarjeta", example = "Activo")
    private String status;
	@Schema(description = "Tipo de producto", example = "Credito")
    private ProductTypeDomain typeProduct;
	@Schema(description = "Balance de la tarjeta", example = "12.2")
    private Double balance;
	@Schema(description = "Precio de la compra", example = "13.0")
    private Double price;

	public String getHolderName() {
		return holderName;
	}
	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}
	public String getExpiration() {
		return expiration;
	}
	public void setExpiration(String expiration) {
		this.expiration = expiration;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public ProductTypeDomain getTypeProduct() {
		return typeProduct;
	}
	public void setTypeProduct(ProductTypeDomain typeProduct) {
		this.typeProduct = typeProduct;
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
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
		
}
