package com.example.banking.dto;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.banking.document.Account;
import com.example.banking.document.CustomerDocument;
import com.example.banking.repository.AccountMongoRepository;
import com.example.banking.repository.CustomerMongoRepository;
import com.example.banking.service.TransferService;


@Service
public class TransferRequest implements TransferService  {
	private String fromIdentity;
	private String toIdentity;
	private String fromIban;
	private String toIban;
	private double amount;
	
	@Autowired
	public TransferRequest() {
	}
	
	List<Account> account;
	
	CustomerMongoRepository customerRepository;
	AccountMongoRepository accountRepository;
	
	public TransferRequest(CustomerMongoRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	public String getFromIdentity() {
		return fromIdentity;
	}
	public void setFromIdentity(String fromIdentity) {
		this.fromIdentity = fromIdentity;
	}
	public String getToIdentity() {
		return toIdentity;
	}
	public void setToIdentity(String toIdentity) {
		this.toIdentity = toIdentity;
	}
	public String getFromIban() {
		return fromIban;
	}
	public void setFromIban(String fromIban) {
		this.fromIban = fromIban;
	}
	public String getToIban() {
		return toIban;
	}
	public void setToIban(String toIban) {
		this.toIban = toIban;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	@Override
	public void transfer(String fromIdentity, String fromIban, String toIdentity, String toIban, double amount) {

		Optional<CustomerDocument> sender = customerRepository.findByAccountsIban(fromIban);
		Optional<CustomerDocument> receiver = customerRepository.findByAccountsIban(toIban);
		var sAcc = sender.get().getAccounts();
		var rAcc = receiver.get().getAccounts();
		sAcc = accountRepository.findByIban(fromIban);
		rAcc = accountRepository.findByIban(toIban);
		
		
		
		if(amount<= 0 && amount> ((Account) sAcc).getBalance() )
			throw new IllegalArgumentException("Amount must be valid");
		
		((Account) sAcc).setBalance(((Account) sAcc).getBalance() - amount);
		((Account) rAcc).setBalance(((Account) rAcc).getBalance() + amount);
			
	
		
		
	}
	
	
		
	
	
	
}
