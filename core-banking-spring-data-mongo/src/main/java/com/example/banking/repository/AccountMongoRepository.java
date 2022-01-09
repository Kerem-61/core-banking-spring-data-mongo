package com.example.banking.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.banking.document.Account;

public interface AccountMongoRepository extends MongoRepository<Account, String> {
	
	List<Account> findByIban(String iban);

}
