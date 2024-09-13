package br.com.eduardooliveiradev.alerta.tesourodireto.feignClient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.eduardooliveiradev.alerta.tesourodireto.bean.IpcaResponseBean;
import br.com.eduardooliveiradev.alerta.tesourodireto.config.RetryConfig;

@FeignClient(name = "IConsultaInflacao",url = "https://api.bcb.gov.br/dados/serie/bcdata.sgs.433",  configuration = RetryConfig.class)
public interface ClientIPCA {

	@GetMapping("/dados")
	List<IpcaResponseBean> consultarUltimosDozeMeses(@RequestParam("formato") String formato,
	        @RequestParam("dataInicial") String dataInicial);
}
