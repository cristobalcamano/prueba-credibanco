package com.prueba.back.domain;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Representa un usuario en el sistema")
public class TransactionBuyDomain {
	
	@Schema(description = "Identificador de la transaccion en BBDD", example = "1")
	private Long id;
	@Schema(description = "Detalle de la compra", example = "Prueba")
    private String detail;
	@Schema(description = "Numero de la tarjeta", example = "1234567890123456")
    private String cardId;
	@Schema(description = "Descripcion de la compra", example = "prueba")
    private String description;
	@Schema(description = "Numero de la transaccion", example = "1234567890")
    private String transactionId;
	@Schema(description = "Estado de la compra", example = "Active")
    private StatusDomain statusDomain;
	@Schema(description = "Tarjeta", example = "1234567890")
    private BankCardDomain bankCardDomain;
	@Schema(description = "Valor de la compra", example = "20.3")
    private Double salesValue;
	@Schema(description = "Creación de la transaccion", example = "23/06/2024")
    private LocalDateTime createdDate;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public StatusDomain getStatusDomain() {
		return statusDomain;
	}
	public void setStatusDomain(StatusDomain statusDomain) {
		this.statusDomain = statusDomain;
	}
	public BankCardDomain getBankCardDomain() {
		return bankCardDomain;
	}
	public void setBankCardDomain(BankCardDomain bankCardDomain) {
		this.bankCardDomain = bankCardDomain;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public Double getSalesValue() {
		return salesValue;
	}
	public void setSalesValue(Double salesValue) {
		this.salesValue = salesValue;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
		
}
