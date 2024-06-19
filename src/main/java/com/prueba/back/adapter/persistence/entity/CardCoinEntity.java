package com.prueba.back.adapter.persistence.entity;

import java.io.Serializable;

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
@Table(name = "card_coin")
public class CardCoinEntity implements Serializable{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4760233486827174894L;

	@Id
    @GeneratedValue
	private Long id;
	
	@ManyToOne
    @JoinColumn(name = "bank_card_fk", nullable = false)
	private BankCardEntity card;
	@ManyToOne
    @JoinColumn(name = "coin_fk", nullable = false)
	private CoinEntity coin;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public BankCardEntity getCard() {
		return card;
	}
	public void setCard(BankCardEntity card) {
		this.card = card;
	}
	public CoinEntity getCoin() {
		return coin;
	}
	public void setCoin(CoinEntity coin) {
		this.coin = coin;
	}
	
}
