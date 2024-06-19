package com.prueba.back.domain;

import java.time.LocalDateTime;

public class TransactionBuyDomain {
	
	private Long id;
    private String detail;
    private String cardId;
    private String description;
    private String transactionId;
    private StatusDomain statusDomain;
    private BankCardDomain bankCardDomain;
    private Double salesValue;
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
