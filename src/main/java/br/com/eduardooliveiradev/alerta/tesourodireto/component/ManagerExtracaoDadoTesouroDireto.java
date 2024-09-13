package br.com.eduardooliveiradev.alerta.tesourodireto.component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jsoup.Jsoup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.eduardooliveiradev.alerta.tesourodireto.bean.ConsultaTesouroBean;
import br.com.eduardooliveiradev.alerta.tesourodireto.bean.RetornoTituloTesouroBean;
import br.com.eduardooliveiradev.alerta.tesourodireto.bean.TitulosTesouroDiretoBean;

@Component
public class ManagerExtracaoDadoTesouroDireto {

	private static final String TESOURO_IPCA_2029 = "Tesouro IPCA+ 2029";
	private static final String TESOURO_PREFIXADO_2031 = "Tesouro Prefixado 2031";
	private static final String URL_TESOURODIRETO = "https://www.tesourodireto.com.br/json/br/com/b3/tesourodireto/service/api/treasurybondsinfo.json";

	public List<RetornoTituloTesouroBean> getValorTaxaAnualTesouro() {
		List<RetornoTituloTesouroBean> retornoTituloTesouros = new ArrayList<>();
		
		WebDriver driver = consultarDadosTesouroDireto();
		String pageSource = driver.getPageSource();
		
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			String textContent = Jsoup.parse(pageSource).text();
			ConsultaTesouroBean consultaTesouroBean = objectMapper.readValue(textContent, ConsultaTesouroBean.class);

			List<TitulosTesouroDiretoBean> titulos = consultaTesouroBean.getResponse().getTitulos();
			
			Optional<TitulosTesouroDiretoBean> optionalPreFixado2031 = filterTituloTesouro(titulos, TESOURO_PREFIXADO_2031);
			Optional<TitulosTesouroDiretoBean> optionalIPCA2029 = filterTituloTesouro(titulos, TESOURO_IPCA_2029);

			if (optionalPreFixado2031.isPresent()) {
				RetornoTituloTesouroBean retornoTituloTesouro = new RetornoTituloTesouroBean();
				TitulosTesouroDiretoBean titulosTesouroDireto = optionalPreFixado2031.get();
				
				BigDecimal taxaAnualNoDia = titulosTesouroDireto.getTrsrBd().getAnulInvstmtRate();
				
				retornoTituloTesouro.setValor(taxaAnualNoDia);
				retornoTituloTesouro.setTitulo(titulosTesouroDireto.getTrsrBd().getNm());
				retornoTituloTesouro.setPrincipal(true);
				
				retornoTituloTesouros.add(retornoTituloTesouro);
			}
			
			if (optionalIPCA2029.isPresent()) {
				RetornoTituloTesouroBean retornoTituloTesouro = new RetornoTituloTesouroBean();
				TitulosTesouroDiretoBean titulosTesouroDireto = optionalIPCA2029.get();
				BigDecimal taxaAnualNoDia = titulosTesouroDireto.getTrsrBd().getAnulInvstmtRate();
				
				retornoTituloTesouro.setValor(taxaAnualNoDia);
				retornoTituloTesouro.setTitulo(titulosTesouroDireto.getTrsrBd().getNm());
				retornoTituloTesouro.setPrincipal(false);
				
				retornoTituloTesouros.add(retornoTituloTesouro);
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally {
			driver.quit();
		}

		return retornoTituloTesouros;
	}

	private Optional<TitulosTesouroDiretoBean> filterTituloTesouro(List<TitulosTesouroDiretoBean> titulos, String constant) {
		return titulos.stream()
				.filter(t -> t.getTrsrBd().getNm().equalsIgnoreCase(constant))
				.findFirst();
	}

	private WebDriver consultarDadosTesouroDireto() {
		WebDriver driver = new ChromeDriver();
		driver.get("http://www.google.com");
		driver.get(URL_TESOURODIRETO);
		
		return driver;
	}
	
}
