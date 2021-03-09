package com.yuriranieri.cursomc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yuriranieri.cursomc.domain.PagamentoComBoleto;
import com.yuriranieri.cursomc.domain.PagamentoComCartao;

@Configuration
public class JacksonConfig { // classe de configuração que registra as subclasses
// https://stackoverflow.com/questions/41452598/overcome-can-not-construct-instance-ofinterfaceclass-without-hinting-the-pare
	@Bean
	public Jackson2ObjectMapperBuilder objectMapperBuilder() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
			public void configure(ObjectMapper objectMapper) {
				objectMapper.registerSubtypes(PagamentoComCartao.class); // registra a subclasse PagamentoComCartao
				objectMapper.registerSubtypes(PagamentoComBoleto.class); // registra a subclasse PagamentoComBoleto
				super.configure(objectMapper);
			}
		};
		return builder;
	}
}
