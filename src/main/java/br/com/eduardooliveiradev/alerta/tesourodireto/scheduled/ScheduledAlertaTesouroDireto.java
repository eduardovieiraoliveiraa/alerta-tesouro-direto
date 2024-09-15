package br.com.eduardooliveiradev.alerta.tesourodireto.scheduled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.eduardooliveiradev.alerta.tesourodireto.service.AlertaTesouroDiretoService;

@Component
public class ScheduledAlertaTesouroDireto {
	
	@Autowired
	private AlertaTesouroDiretoService alertaTesouroDiretoService;

	@Scheduled(cron = "0  9-17 * * MON-FRI")
	public void enviarAlerta() {
		alertaTesouroDiretoService.processarAlertaTesouroDireto();
	}
}
