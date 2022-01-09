package com.example.banking.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.banking.document.CustomerDocument;
import com.example.banking.dto.TransferRequest;
import com.example.banking.dto.TransferResponse;
import com.example.banking.repository.CustomerMongoRepository;
import com.example.banking.service.TransferService;


@RestController
@RequestMapping("customers")
public class BankingController {
	CustomerMongoRepository customerRepository;
	TransferService service;
	@Autowired
	public BankingController(TransferService service) {
		this.service=service;
	}
	
	@GetMapping("customers/{identity}")
	public Optional<CustomerDocument> getCustomerByIdentity(@PathVariable String identity) {
	
			return customerRepository.findByIdentity(identity);
					
	}
	@PostMapping
	public TransferResponse transfer(@RequestBody TransferRequest request) {
		this.service.transfer(request.getFromIdentity(), request.getFromIban(), request.getToIdentity(), request.getToIban(), 3000);
		return new TransferResponse("success", "Transfer is successful");
	}
}
