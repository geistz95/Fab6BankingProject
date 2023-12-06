package com.fab5.bankingapp;

import com.fab5.bankingapp.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class BankingApplication {

	@Autowired
	private TransactionService transactionService;

	@Scheduled(fixedRate = 300000)
	public void setTransactionService(){
		transactionService.processTransactions();
	}
	public static void main(String[] args) {
		SpringApplication.run(BankingApplication.class, args);
	}

	@Bean
	public CommandLineRunner run() throws Exception{
		return args -> setTransactionService();
	}
}
