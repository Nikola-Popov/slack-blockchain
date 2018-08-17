package io.transaction.broker.coinbase;

import java.net.URISyntaxException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TransactionBrokerApplication {
	public static void main(String[] args) throws URISyntaxException {
		SpringApplication.run(TransactionBrokerApplication.class, args);
	}
}
