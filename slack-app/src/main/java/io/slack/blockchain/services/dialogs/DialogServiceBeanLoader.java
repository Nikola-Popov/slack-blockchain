package io.slack.blockchain.services.dialogs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class DialogServiceBeanLoader {
	@Autowired
	private ApplicationContext applicationContext;

	public <T extends DialogService> DialogService getDialogService(final Class<T> classOfT) {
		return applicationContext.getBean(classOfT);
	}
}
