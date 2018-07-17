package io.slack.blockchain.interactive.components.dialogs.elements;

import static io.slack.blockchain.interactive.components.dialogs.elements.constants.TransactionSelectElementConstants.CURRENCY_ELEMENT_LABEL;
import static io.slack.blockchain.interactive.components.dialogs.elements.constants.TransactionSelectElementConstants.CURRENCY_ELEMENT_PLACEHOLDER;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.github.seratch.jslack.api.model.dialog.DialogOption;
import com.github.seratch.jslack.api.model.dialog.DialogSelectElement;

import io.slack.blockchain.interactive.components.dialogs.elements.CurrencySelectElementBuilder;
import io.slack.blockchain.interactive.components.dialogs.elements.utils.CurrenciesProvider;

@RunWith(MockitoJUnitRunner.class)
public class CurrencySelectElementBuilderTest {
	private static final List<DialogOption> CURRENCIES = asList(
			DialogOption.builder().label("labelCurrency").value("valueCurrency").build());

	private static final DialogSelectElement CURRENCIES_SELECT_ELEMENT = DialogSelectElement.builder()
			.name(CURRENCY_ELEMENT_LABEL.toLowerCase()).label(CURRENCY_ELEMENT_LABEL)
			.placeholder(CURRENCY_ELEMENT_PLACEHOLDER).options(CURRENCIES).build();

	@InjectMocks
	private CurrencySelectElementBuilder currencySelectElementBuilder;

	@Mock
	private CurrenciesProvider currenciesProviderMock;

	@Before
	public void setup() {
		when(currenciesProviderMock.provideCurrencies()).thenReturn(CURRENCIES);
	}

	@Test
	public void testBuild() {
		assertThat(currencySelectElementBuilder.build(), equalTo(CURRENCIES_SELECT_ELEMENT));
		verify(currenciesProviderMock).provideCurrencies();
	}
}
