package br.com.eduardooliveiradev.alerta.tesourodireto.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.eduardooliveiradev.alerta.tesourodireto.feignClient.ClientTelegram;

@Service
public class MensagemTelegramService {
	
	private ClientTelegram clientTelegram;

	@Value("${telegram.bot.token}")
	private String botToken;

	@Value("${telegram.bot.id}")
	private String chatId;

	public MensagemTelegramService(ClientTelegram clientTelegram) {
		this.clientTelegram = clientTelegram;
	}

	public void enviar(String message) {
		clientTelegram.sendMessage(botToken, chatId, message);
	}

}
