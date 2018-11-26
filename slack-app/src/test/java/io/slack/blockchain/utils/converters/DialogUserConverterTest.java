package io.slack.blockchain.utils.converters;

import static java.lang.String.format;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.seratch.jslack.api.model.User;
import com.github.seratch.jslack.api.model.dialog.DialogOption;
import com.github.seratch.jslack.api.model.dialog.DialogOption.DialogOptionBuilder;

@RunWith(MockitoJUnitRunner.class)
public class DialogUserConverterTest {
	private static final String ID = "id";
	private static final String REAL_NAME = "realName";
	private static final String NAME = "name";
	private static final String LABEL = format("%s (%s)", REAL_NAME, NAME);

	@Autowired
	@InjectMocks
	private DialogUserConverter userConverter;

	@Mock
	private DialogOptionBuilder dialogOptionBuilderMock;

	@Mock
	private User userMock;

	@Mock
	private DialogOption dialogOptionMock;

	@Spy
	private List<User> users = new ArrayList<>();

	@Before
	public void setUp() {
		when(userMock.getRealName()).thenReturn(REAL_NAME);
		when(userMock.getName()).thenReturn(NAME);
		when(userMock.getId()).thenReturn(ID);
		when(dialogOptionBuilderMock.label(LABEL)).thenReturn(dialogOptionBuilderMock);
		when(dialogOptionBuilderMock.value(ID)).thenReturn(dialogOptionBuilderMock);
		when(dialogOptionBuilderMock.build()).thenReturn(dialogOptionMock);

		when(dialogOptionMock.getLabel()).thenReturn(LABEL);
		when(dialogOptionMock.getValue()).thenReturn(ID);
		users.add(userMock);
	}

	@Test
	public void testConvert() {
		final List<DialogOption> dialogOptions = userConverter.convert(users);

		assertThat(dialogOptions.get(0).getLabel(), equalTo(LABEL));
		assertThat(dialogOptions.get(0).getValue(), equalTo(ID));
	}
}
