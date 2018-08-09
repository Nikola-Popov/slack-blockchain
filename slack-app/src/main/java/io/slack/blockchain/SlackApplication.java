package io.slack.blockchain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses = io.slack.blockchain.commons.services.GsonJsonService.class)
public class SlackApplication {

	public static void main(String[] args) {
		SpringApplication.run(SlackApplication.class, args);
	}
}
