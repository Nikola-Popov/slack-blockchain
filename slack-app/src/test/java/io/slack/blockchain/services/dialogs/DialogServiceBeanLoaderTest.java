package io.slack.blockchain.services.dialogs;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

@RunWith(MockitoJUnitRunner.class)
public class DialogServiceBeanLoaderTest {
	@InjectMocks
	@Autowired
	private DialogServiceBeanLoader dialogServiceBeanLoader;

	@Mock
	private ApplicationContext applicationContextMock;

	@Test
	public void testGetEmailConfigurationDialogService() {
		dialogServiceBeanLoader.getDialogService(EmailConfigurationDialogService.class);

		verify(applicationContextMock).getBean(EmailConfigurationDialogService.class);
	}

	@Test
	public void testGetTransactionDialogService() {
		dialogServiceBeanLoader.getDialogService(TransactionDialogService.class);

		verify(applicationContextMock).getBean(TransactionDialogService.class);
	}
}
