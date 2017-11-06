package com.plenumsoft.vuzee.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableArgumentResolver;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.dialect.springdata.SpringDataDialect;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@Configuration
// @EnableWebMvc
// @ComponentScan(basePackages = "com.plenumsoft.vuzee.config")
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		 PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();
		 resolver.setFallbackPageable(new PageRequest(0, 5));
		 argumentResolvers.add(resolver);
		 super.addArgumentResolvers(argumentResolvers);
	}

	
   
}
