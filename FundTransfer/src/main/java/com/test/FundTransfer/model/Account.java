package com.test.FundTransfer.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Formula;

@Entity
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private Long accountNo;
	@NotNull
	private String bankName;
	
	
	//@Transient
	//@PostLoad
	 @Formula(value = " (select (select COALESCE(sum(t.amount),0) from transaction t where t.account_id = id and t.trns_type = 'CR') - " +
	            "(select COALESCE(sum(d.amount),0) from transaction d where d.account_id = id and d.trns_type = 'DR'))")
	private double balance;
	
	@OneToOne
	 @JoinColumn(name = "custId")
	private Customer customer;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "accountId")
	private List<Transaction> transaction = new ArrayList<Transaction>();
	
	@Transient
	private Long otherAccountId;
	
	@Transient
	private double transferAmount;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(Long accountNo) {
		this.accountNo = accountNo;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public List<Transaction> getTransaction() {
		return transaction;
	}
	public void setTransaction(List<Transaction> transaction) {
		this.transaction = transaction;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public void addTransaction(Transaction t)
	{
		this.transaction.add(t);
	}
	public Long getOtherAccountId() {
		return otherAccountId;
	}
	public void setOtherAccountId(Long otherAccountId) {
		this.otherAccountId = otherAccountId;
	}
	public double getTransferAmount() {
		return transferAmount;
	}
	public void setTransferAmount(double transferAmount) {
		this.transferAmount = transferAmount;
	}
	
	

}
