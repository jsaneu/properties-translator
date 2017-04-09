package org.xsierra.translator;

import java.io.IOException;
import java.util.Locale;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class TranslatorControler {

	@Autowired
	private PropertiesTranslator translator;

	@RequestMapping(value = "translate/{source}/{target}", method = RequestMethod.POST, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public void translateFile(@PathVariable String source, @PathVariable String target,
			@RequestParam("file") MultipartFile file, HttpServletResponse response) throws IOException {

		Properties incomingProperties = new Properties();
		incomingProperties.load(file.getInputStream());

		Locale sourceLocale = new Locale(source);
		Locale targetLocale = new Locale(target);

		Properties translatedProperties = translator.translate(incomingProperties, sourceLocale, targetLocale);

		response.setContentType("text/x-java-properties");
		response.setHeader("Content-Disposition", "attachment; filename=\"translated_" + target + ".properties\"");
		translatedProperties.store(response.getOutputStream(), "Powered by Yandex");
		response.flushBuffer();

	}

	@ResponseStatus(value = HttpStatus.UNSUPPORTED_MEDIA_TYPE, reason = "Mailformed properties file")
	@ExceptionHandler(IOException.class)
	public void mailformedFile() {

	}

}
