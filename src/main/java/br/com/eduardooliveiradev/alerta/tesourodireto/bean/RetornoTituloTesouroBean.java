package br.com.eduardooliveiradev.alerta.tesourodireto.bean;

import java.math.BigDecimal;

public class RetornoTituloTesouroBean {

	private String titulo;

	private BigDecimal valor;

	private boolean principal;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public boolean isPrincipal() {
		return principal;
	}

	public void setPrincipal(boolean principal) {
		this.principal = principal;
	}

}
