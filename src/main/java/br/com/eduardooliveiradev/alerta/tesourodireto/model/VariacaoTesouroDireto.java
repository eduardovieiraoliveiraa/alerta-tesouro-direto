package br.com.eduardooliveiradev.alerta.tesourodireto.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class VariacaoTesouroDireto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private BigDecimal valorUltimaVariacao;

	private String titulo;

	public BigDecimal getValorUltimaVariacao() {
		return valorUltimaVariacao;
	}

	public void setValorUltimaVariacao(BigDecimal valorUltimaVariacao) {
		this.valorUltimaVariacao = valorUltimaVariacao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

}
