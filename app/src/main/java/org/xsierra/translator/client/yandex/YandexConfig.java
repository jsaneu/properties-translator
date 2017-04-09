package org.xsierra.translator.client.yandex;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("yandex")
public class YandexConfig {

	private String key = "mock_key";

	private String url = "https://server.mock?key={key}&lang={lang}&text={text}";

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
