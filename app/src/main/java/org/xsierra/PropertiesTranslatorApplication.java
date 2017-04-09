package org.xsierra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@SpringBootApplication
public class PropertiesTranslatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(PropertiesTranslatorApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTeamplate(){
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate;
	}
	
	
	@Bean
	public CommonsMultipartResolver multipartResolver() {
	    final CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
	    return commonsMultipartResolver;
	}
}
