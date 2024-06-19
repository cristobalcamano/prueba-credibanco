package com.prueba.back.domain;

public class BankCardDomain {

	private Long id;
	private String cardId;
    private String number;
    private String holderName;
    private String expiration;
    private String status;
    private ProductTypeDomain typeProduct;
    private Double balance;
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
