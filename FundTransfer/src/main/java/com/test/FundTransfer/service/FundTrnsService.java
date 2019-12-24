package com.test.FundTransfer.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.FundTransfer.model.Account;
import com.test.FundTransfer.model.Customer;
import com.test.FundTransfer.model.Transaction;
import com.test.FundTransfer.repository.AccountRepository;
import com.test.FundTransfer.repository.CustomerRepository;

@Service
@Transactional
public class FundTrnsService {

	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private AccountRepository accountRepository;
	
	public List<Account> getAllCustomers()
	{
		return accountRepository.findAll();
	}
	
	public void saveCustomer(Account account)
	{
		if(account.getId() != null)
		{
			Account account1 = this.getAccountDetailsById(account.getId());
			account1.getCustomer().setCustomerName(account.getCustomer().getCustomerName()); 
			account1.getCustomer().setPhoneNo(account.getCustomer().getPhoneNo());
			accountRepository.save(account1);
		}
		else
		{
			Customer customer = account.getCustomer();
			customer = customerRepo.save(customer);
			/*
			 * Random value = new Random(); int r1 = value.nextInt(10);
			 */
			
			long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
			
			account.setAccountNo(number); 
			account.setCustomer(customer); 

			//account = accountRepository.save(account);
			
			Transaction transaction = new Transaction();
			transaction.setTrnsDate(new Date());
			transaction.setAmount(account.getBalance());
			transaction.setTrnsType("CR");
			account.addTransaction(transaction); 
			accountRepository.save(account);
		}
	}
	
	public Account getAccountDetailsById(Long id)
	{
		return accountRepository.findById(id).get();
	}
	
	public List<Account> getOtherAccoutDetails(Long id)
	{
		return accountRepository.findByIdNot(id);
	}
	
	public void sendFundTransfer(Account account)
	{
		Account debitAccount = accountRepository.findById(account.getId()).get();
		Transaction transaction = new Transaction();
		transaction.setTrnsDate(new Date());
		transaction.setAmount(account.getTransferAmount());
		transaction.setTrnsType("DR");
		debitAccount.addTransaction(transaction);
		accountRepository.save(debitAccount);
		
		Account creditAccount = accountRepository.findById(account.getOtherAccountId()).get();
		Transaction transaction1 = new Transaction();
		transaction1.setTrnsDate(new Date());
		transaction1.setAmount(account.getTransferAmount());
		transaction1.setTrnsType("CR");
		creditAccount.addTransaction(transaction1);
		accountRepository.save(creditAccount);
	}
	
}
