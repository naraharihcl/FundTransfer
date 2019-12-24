package com.test.FundTransfer.model;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/*
	 * @OneToOne
	 * 
	 * @JoinColumn(name = "accountId") private Account account;
	 */
	private double amount;
	private String trnsType;
	
	private Date trnsDate;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getTrnsType() {
		return trnsType;
	}
	public void setTrnsType(String trnsType) {
		this.trnsType = trnsType;
	}
	public Date getTrnsDate() {
		return trnsDate;
	}
	public void setTrnsDate(Date trnsDate) {
		this.trnsDate = trnsDate;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
	
}
