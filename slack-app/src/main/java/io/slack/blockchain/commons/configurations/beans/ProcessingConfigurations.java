package io.slack.blockchain.commons.configurations.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.slack.blockchain.domain.processing.ProcessingResult;
import io.slack.blockchain.domain.processing.ProcessingResult.ProcessingResultBuilder;

@Configuration
public class ProcessingConfigurations {
	@Bean
	public ProcessingResultBuilder createProcessingResult() {
		return ProcessingResult.builder();
	}
}
