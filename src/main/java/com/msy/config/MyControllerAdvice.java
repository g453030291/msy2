package com.msy.config;

import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
public class MyControllerAdvice {

	@InitBinder
	protected void initBinder(WebDataBinder binder){
		GenericConversionService genericConversionService = (GenericConversionService) binder.getConversionService();
		if (genericConversionService != null) {
			genericConversionService.addConverter(new DateConverter());
		}
	}

}
