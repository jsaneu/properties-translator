package org.xsierra.translator.client.yandex;

import java.util.Locale;

public class YandexRequest {

	private static final String LANG_FORMAT = "%s-%s";

	private String text;

	private String lang;

	public YandexRequest(String phrase, Locale source, Locale target) {
		if (phrase.length() > 10000) {
			throw new IllegalArgumentException("Phrase argument is too long, cannot exceed 10000 characters. "
					+ "Current phrase length is " + phrase.length());
		}

		if (target == null) {
			throw new IllegalArgumentException("Target locale is mandatory");
		}

		text = phrase;
		
		String sourceLanguage;
		
		if (source == null){
			sourceLanguage = Locale.ENGLISH.getLanguage();
		} else {
			sourceLanguage = source.getLanguage();
		}

		String targetLanguage = target.getLanguage();

		lang = String.format(LANG_FORMAT, sourceLanguage, targetLanguage);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

}
