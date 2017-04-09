package org.xsierra.tranlator;

import org.junit.Test;

import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.context.junit4.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.Locale;
import java.util.Properties;

import org.xsierra.translator.PropertiesTranslatorImpl;
import org.xsierra.translator.client.TranslatorClient;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PropertiesTranslatorImplTest {

	@MockBean
	private TranslatorClient client;

	@Autowired
	private PropertiesTranslatorImpl translator;

	@Test
	public void testTranslate() {

		given(client.translate(eq("Hello"), eq(Locale.ENGLISH), eq(new Locale("es")))).willReturn("Hola");

		Properties p = new Properties();
		p.setProperty("some.key", "Hello");

		Properties result = translator.translate(p, Locale.ENGLISH, new Locale("es"));

		assertThat(result.getProperty("some.key")).isEqualTo("Hola");

	}

}
