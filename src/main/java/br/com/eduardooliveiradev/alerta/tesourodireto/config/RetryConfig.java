package br.com.eduardooliveiradev.alerta.tesourodireto.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Retryer;

@Configuration
public class RetryConfig {

	@Bean
	Retryer retryer() {
		return new Retryer.Default(100, 1000, 3); // Intervalo inicial de 100ms, máximo de 1 segundo, 3 tentativas
	}
}
