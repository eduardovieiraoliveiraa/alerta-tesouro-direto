package br.com.eduardooliveiradev.alerta.tesourodireto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableRetry
@EnableFeignClients
@EnableScheduling
public class AlertaTesouroDiretoApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(AlertaTesouroDiretoApplication.class, args);
	}

}
