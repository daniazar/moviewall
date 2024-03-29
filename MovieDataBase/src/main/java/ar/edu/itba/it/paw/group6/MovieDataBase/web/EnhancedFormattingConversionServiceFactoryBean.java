package ar.edu.itba.it.paw.group6.MovieDataBase.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;

public class EnhancedFormattingConversionServiceFactoryBean extends FormattingConversionServiceFactoryBean {
	
	private final Converter<?, ?>[] converters;
	
	@Autowired
	public EnhancedFormattingConversionServiceFactoryBean(Converter<?, ?>[] converters) {
		this.converters = converters;
	}
	
	@Override
	protected void installFormatters(FormatterRegistry registry) {
		super.installFormatters(registry);
		for (Converter<?,?> c: converters) {
			registry.addConverter(c);
		}
	}
}
