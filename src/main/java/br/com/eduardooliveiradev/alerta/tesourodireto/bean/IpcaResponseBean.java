package br.com.eduardooliveiradev.alerta.tesourodireto.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class IpcaResponseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String data;
	private BigDecimal valor;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
