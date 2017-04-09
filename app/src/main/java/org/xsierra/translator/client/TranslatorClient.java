package org.xsierra.translator.client;

import java.util.Locale;

public interface TranslatorClient {
	
	String translate(String phrase, Locale source, Locale target);

}
