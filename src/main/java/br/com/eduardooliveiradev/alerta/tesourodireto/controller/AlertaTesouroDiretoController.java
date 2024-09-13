package br.com.eduardooliveiradev.alerta.tesourodireto.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.eduardooliveiradev.alerta.tesourodireto.service.AlertaTesouroDiretoService;

@RestController
@RequestMapping("alerta-tesouro-direto")
public class AlertaTesouroDiretoController {
	
	private AlertaTesouroDiretoService AlertaTesouroDiretoService;

	public AlertaTesouroDiretoController(AlertaTesouroDiretoService AlertaTesouroDiretoService) {
		this.AlertaTesouroDiretoService = AlertaTesouroDiretoService;
	}

	@GetMapping("/consultar-titulos")
	public String consultarTitulos() {
		AlertaTesouroDiretoService.processarAlertaTesouroDireto();

		return "Processamento iniciado com sucesso. Verifique as notificações.";
	}

}
