package br.com.eduardooliveiradev.alerta.tesourodireto.bean;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseTesouroDiretoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	@JsonProperty("TrsrBdTradgList")
	private List<TitulosTesouroDiretoBean> titulos;

	public List<TitulosTesouroDiretoBean> getTitulos() {
		return titulos;
	}

	public void setTitulos(List<TitulosTesouroDiretoBean> titulos) {
		this.titulos = titulos;
	}

}
