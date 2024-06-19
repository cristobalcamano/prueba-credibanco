package com.prueba.back.adapter.persistence.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
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
@Table(name = "transaction")
public class TransactionEntity implements Serializable{

	/**
	 * serialVersionUID
	 * 
	 */
	private static final long serialVersionUID = 3142300556277246709L;

	@Id
    @GeneratedValue
	private Long id;
	
	@Column(name = "transaction_id")
	private String transactionId;
	@ManyToOne
    @JoinColumn(name = "statud_id", nullable = false)
	private StatusEntity statudId;
	@ManyToOne
    @JoinColumn(name = "card_id", nullable = false)
	private BankCardEntity card;
	@Column(name = "sale_value")
    private Double saleValue;
	@Column(name = "creation_date")
    private LocalDateTime creationDate;
	
	public TransactionEntity() {
		super();
	}
	
	public TransactionEntity(Long id) {
		super();
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public StatusEntity getStatudId() {
		return statudId;
	}
	public void setStatudId(StatusEntity statudId) {
		this.statudId = statudId;
	}
	public BankCardEntity getCard() {
		return card;
	}
	public void setCard(BankCardEntity card) {
		this.card = card;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public LocalDateTime getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}
	public Double getSaleValue() {
		return saleValue;
	}
	public void setSaleValue(Double saleValue) {
		this.saleValue = saleValue;
	}
	
}
