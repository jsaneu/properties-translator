package org.xsierra.tranlator.client;

import static org.junit.Assert.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

import java.util.Locale;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import org.xsierra.translator.client.yandex.YandexClient;
import org.xsierra.translator.client.yandex.YandexConfig;
import org.xsierra.translator.client.yandex.YandexResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { YandexClient.class, RestTemplate.class, YandexConfig.class })
public class YandexClientTest {

	@Autowired
	private YandexClient client;

	@Test
	public void test() throws JsonProcessingException {

		MockRestServiceServer server = MockRestServiceServer.bindTo(client.getRestTemplate()).build();
		String apiKey = client.getConfig().getKey();

		String url = "https://server.mock?key=" + apiKey + "&lang=en-es&text=Hello";

		YandexResponse response = new YandexResponse();
		response.setCode(200);
		response.setLang("en-es");
		String[] text = { "Hola" };
		response.setText(text);

		ObjectMapper mapper = new ObjectMapper();

		server.expect(requestTo(url)).andExpect(method(HttpMethod.POST))
				.andRespond(withSuccess(mapper.writeValueAsString(response), MediaType.APPLICATION_JSON));

		String translated = client.translate("Hello", Locale.ENGLISH, new Locale("es"));

		assertEquals("Hola", translated);

	}

}
