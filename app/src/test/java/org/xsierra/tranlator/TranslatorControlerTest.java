package org.xsierra.tranlator;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.io.ByteArrayInputStream;
import java.util.Locale;
import java.util.Properties;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.xsierra.PropertiesTranslatorApplication;
import org.xsierra.translator.PropertiesTranslator;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PropertiesTranslatorApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TranslatorControlerTest {

	@MockBean
	private PropertiesTranslator translator;

	@Autowired
    private MockMvc mvc;
	
	@Test
	public void testTranslate() throws Exception {

		Properties expectedTranslatedProperties = new Properties();
		expectedTranslatedProperties.setProperty("com.mock.property", "hola");
		
		given(translator.translate(any(Properties.class), eq(new Locale("es")), eq(Locale.ENGLISH)))
				.willReturn(expectedTranslatedProperties);
		
		Properties toTranslate = new Properties();
		toTranslate.setProperty("com.mock.property", "hello");
		
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		toTranslate.store(os, null);
		
		MockMultipartFile multipartFile =
                new MockMultipartFile("file", "test.properties", "text/plain", os.toByteArray());
		
		MockHttpServletResponse response = this.mvc.perform(fileUpload("/translate/es/en").file(multipartFile)).andExpect(status().isOk()).andReturn().getResponse();
		
		

		Properties actualTranslatedProperties = new Properties();
		actualTranslatedProperties.load(new ByteArrayInputStream(response.getContentAsByteArray()));
		
		assertThat(expectedTranslatedProperties, equalTo(actualTranslatedProperties));

	}

}
