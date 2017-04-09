package org.xsierra.translator;

import java.util.Locale;
import java.util.Properties;

public interface PropertiesTranslator {

	Properties translate(Properties properties, Locale source, Locale target);
}
