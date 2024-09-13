package br.com.eduardooliveiradev.alerta.tesourodireto.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.eduardooliveiradev.alerta.tesourodireto.bean.IpcaResponseBean;
import br.com.eduardooliveiradev.alerta.tesourodireto.bean.RetornoTituloTesouroBean;
import br.com.eduardooliveiradev.alerta.tesourodireto.component.ManagerExtracaoDadoTesouroDireto;
import br.com.eduardooliveiradev.alerta.tesourodireto.feignClient.ClientIPCA;
import br.com.eduardooliveiradev.alerta.tesourodireto.model.VariacaoTesouroDireto;
import br.com.eduardooliveiradev.alerta.tesourodireto.repository.VariacaoTesouroDiretoRepository;

@Service
public class AlertaTesouroDiretoService {
	
	private static final String ALERTAR_VALOR_IDEAL_TITULO = "12";

	private ClientIPCA clientIPCA;
	
	private MensagemTelegramService mensagemTelegramService;
	
	private ManagerExtracaoDadoTesouroDireto managerExtracaoDadoTesouroDireto;
	
	private VariacaoTesouroDiretoRepository variacaoTesouroDiretoRepository;
	
	public AlertaTesouroDiretoService(ClientIPCA clientIPCA, 
			ManagerExtracaoDadoTesouroDireto managerExtracaoDadoTesouroDireto,
			MensagemTelegramService mensagemTelegramService,
			VariacaoTesouroDiretoRepository variacaoTesouroDiretoRepository) {
							this.clientIPCA = clientIPCA;
							this.managerExtracaoDadoTesouroDireto = managerExtracaoDadoTesouroDireto;
							this.mensagemTelegramService = mensagemTelegramService;
							this.variacaoTesouroDiretoRepository = variacaoTesouroDiretoRepository;
	}

	public void processarAlertaTesouroDireto() {
		List<RetornoTituloTesouroBean> retornoTituloTesouros = managerExtracaoDadoTesouroDireto.getValorTaxaAnualTesouro();
		
		List<BigDecimal> variacoes = consultarIPCAUltimosDozeMeses();
		BigDecimal valorIPCA = acumularIPCA(variacoes);
		Optional<RetornoTituloTesouroBean> retornoTituloTesouroOptPrincipal = retornoTituloTesouros.stream().filter(x -> x.isPrincipal()).findFirst();
		boolean ultimoTituloIgualAtual =false;
		
		
		if(retornoTituloTesouroOptPrincipal.isPresent()) {
			RetornoTituloTesouroBean retornoTituloTesouro = retornoTituloTesouroOptPrincipal.get();
			BigDecimal valorPercentPrincipal = retornoTituloTesouro.getValor();
			
			VariacaoTesouroDireto variacaoTesouroDireto = variacaoTesouroDiretoRepository.findByTituloIgnoreCase(retornoTituloTesouro.getTitulo());
			
			if(Objects.isNull(variacaoTesouroDireto)) {
				VariacaoTesouroDireto newVariacaoTesouroDireto = new VariacaoTesouroDireto();
				
				newVariacaoTesouroDireto.setTitulo(retornoTituloTesouro.getTitulo());
				newVariacaoTesouroDireto.setValorUltimaVariacao(valorPercentPrincipal);
				
				variacaoTesouroDiretoRepository.save(newVariacaoTesouroDireto);
			}else {
				
				BigDecimal valorUltimaVariacao = variacaoTesouroDireto.getValorUltimaVariacao().setScale(2);
				BigDecimal valorPercentPrincipalScale = valorPercentPrincipal.setScale(2);
				
				if(!valorUltimaVariacao.equals(valorPercentPrincipalScale)) {
					String messageAlerta = MessageFormat.format("ATENÇÃO: O Titulo {0} sofreu alteração no percentual. "
							+ "Ultimo registro: {1} , registro atual: {2}", 
							retornoTituloTesouro.getTitulo(), valorUltimaVariacao, valorPercentPrincipal);
					
					variacaoTesouroDireto.setValorUltimaVariacao(valorPercentPrincipal);
					variacaoTesouroDiretoRepository.save(variacaoTesouroDireto);
					
					enviarMensagem(messageAlerta);
					
				}else {
					ultimoTituloIgualAtual = true;
				}
			}
			
			if(valorPercentPrincipal.compareTo(new BigDecimal(ALERTAR_VALOR_IDEAL_TITULO)) > 0 && !ultimoTituloIgualAtual) {
				String messageAlerta = MessageFormat.format("ATENÇÃO: O Titulo {0} atingiu o percentual {1}", 
						retornoTituloTesouro.getTitulo(), valorPercentPrincipal);
				
				enviarMensagem(messageAlerta);
			}
			
			List<RetornoTituloTesouroBean> tituloTesourosNaoPrincipaisList = retornoTituloTesouros.stream().filter(x -> !x.isPrincipal()).collect(Collectors.toList());
			for (RetornoTituloTesouroBean retornoTituloTesouroNaoPrincipal : tituloTesourosNaoPrincipaisList) {
				
				BigDecimal valorTituloNaoPrincipal = retornoTituloTesouroNaoPrincipal.getValor();
				BigDecimal ganhoRealBrutoTituloPrincipal = valorPercentPrincipal.subtract(valorIPCA);
				
				if(ganhoRealBrutoTituloPrincipal.compareTo(valorTituloNaoPrincipal) < 0) {
					String messageAlerta = MessageFormat.format("ATENÇÃO: O ganho bruto do Titulo {0} está inferior ao titulo {1}. "
							+ "Ganho real bruto {2} X Ganho real bruto {3}", 
											retornoTituloTesouro.getTitulo(), 
											retornoTituloTesouroNaoPrincipal.getTitulo(), 
											valorPercentPrincipal, 
											valorTituloNaoPrincipal);
					
					enviarMensagem(messageAlerta);
				}
				
			}
		}
	}

	private void enviarMensagem(String messageAlerta) {
		mensagemTelegramService.enviar(messageAlerta);
	}
	
	private List<BigDecimal> consultarIPCAUltimosDozeMeses() {
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -12);
		String dataInicio = new SimpleDateFormat("dd/MM/yyyy").format(calendar.getTime());
		
		List<IpcaResponseBean> consultarUltimosDozeMeses = clientIPCA.consultarUltimosDozeMeses("json", dataInicio);
		return consultarUltimosDozeMeses.stream().map(r -> r.getValor()).collect(Collectors.toList());
	}

	private BigDecimal acumularIPCA(List<BigDecimal> variacoes) {
		BigDecimal acumulado = BigDecimal.ONE;

		for (BigDecimal variacao : variacoes) {
			BigDecimal newVariacaoDecimal = variacao.divide(new BigDecimal("100"));
			acumulado = acumulado.multiply(BigDecimal.ONE.add(newVariacaoDecimal));
		}

		acumulado = acumulado.subtract(BigDecimal.ONE).multiply(new BigDecimal("100"));
		acumulado = acumulado.setScale(2, RoundingMode.HALF_UP);
		return acumulado;
	}

}
