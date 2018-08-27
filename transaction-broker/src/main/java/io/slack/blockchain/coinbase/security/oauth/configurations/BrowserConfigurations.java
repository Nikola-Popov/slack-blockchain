package io.slack.blockchain.coinbase.security.oauth.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import edu.stanford.ejalbert.BrowserLauncher;
import edu.stanford.ejalbert.exception.BrowserLaunchingInitializingException;
import edu.stanford.ejalbert.exception.UnsupportedOperatingSystemException;

@Configuration
public class BrowserConfigurations {
	@Bean
	public BrowserLauncher createBrowserLauncher()
			throws BrowserLaunchingInitializingException, UnsupportedOperatingSystemException {
		return new BrowserLauncher();
	}
}
