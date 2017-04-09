package org.xsierra.translator.client.yandex;

import java.util.Locale;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.xsierra.translator.client.TranslatorClient;

@Service
public class YandexClient implements TranslatorClient {

	private RestTemplate restTemplate;

	private YandexConfig config;
	
	public YandexClient(RestTemplate yandexTemplate, YandexConfig config) {
		super();
		this.restTemplate = yandexTemplate;
		this.config = config;
	}

	@Override
	public String translate(String phrase, Locale source, Locale target) {

		YandexRequest request = new YandexRequest(phrase, source, target);

		String url = config.getUrl();
		String key = config.getKey();
		YandexResponse response = restTemplate.postForObject(url, null, YandexResponse.class, key,
				request.getLang(), request.getText());

		return response.getText()[0];
	}

	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public YandexConfig getConfig() {
		return config;
	}

	public void setConfig(YandexConfig config) {
		this.config = config;
	}
	
	

}
