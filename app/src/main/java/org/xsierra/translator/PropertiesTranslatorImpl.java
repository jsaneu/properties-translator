package org.xsierra.translator;

import java.util.Locale;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xsierra.translator.client.TranslatorClient;

@Service
public class PropertiesTranslatorImpl implements PropertiesTranslator {
	
	@Autowired
	private TranslatorClient client;
	
	@Override
	public Properties translate(Properties properties, Locale source, Locale target) {
		
		Properties translated = new Properties();
		
		properties.forEach((key, value) -> {
			String phrase = (String) value;
			String translatedPhrase = client.translate(phrase, source, target);
			translated.setProperty((String) key, translatedPhrase);
		});
		
		return translated;
	}

}
