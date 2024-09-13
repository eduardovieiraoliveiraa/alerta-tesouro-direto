package br.com.eduardooliveiradev.alerta.tesourodireto.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TitulosTesouroDiretoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("TrsrBd")
	private DadosTituloTesouroDiretoBean trsrBd;
	@JsonProperty("SelicCode")
	private int selicCode;

	public DadosTituloTesouroDiretoBean getTrsrBd() {
		return trsrBd;
	}

	public void setTrsrBd(DadosTituloTesouroDiretoBean trsrBd) {
		this.trsrBd = trsrBd;
	}

	public int getSelicCode() {
		return selicCode;
	}

	public void setSelicCode(int selicCode) {
		this.selicCode = selicCode;
	}

}
