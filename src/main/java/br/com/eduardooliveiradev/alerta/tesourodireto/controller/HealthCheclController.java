package br.com.eduardooliveiradev.alerta.tesourodireto.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthCheclController {
	
	   @GetMapping
	    public String healthCheck() {
	        return "Aplicação está funcionando corretamente!";
	    }

}
