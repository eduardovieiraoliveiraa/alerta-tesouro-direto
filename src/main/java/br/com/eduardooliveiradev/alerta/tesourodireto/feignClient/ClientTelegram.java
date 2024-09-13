package br.com.eduardooliveiradev.alerta.tesourodireto.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "APITelegram", url = "https://api.telegram.org")
public interface ClientTelegram {

	@GetMapping("/bot{token}/sendMessage")
	void sendMessage(@RequestParam("token") String token,                        
							@RequestParam("chat_id") String chatId,
				            @RequestParam("text") String mensagem);
}
