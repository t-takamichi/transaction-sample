package com.transaction.transaction_sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class TransactionSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransactionSampleApplication.class, args);
	}

}
