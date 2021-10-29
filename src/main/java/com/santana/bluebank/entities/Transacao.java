package com.santana.bluebank.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.santana.bluebank.enums.TipoOperacao;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_transacoes")
@NoArgsConstructor
@Data
public class Transacao implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String banco;
	private String agencia;
	private String numeroContaOrigem;
	private String numeroContaDestino;
	
	@Enumerated(EnumType.STRING)
	private TipoOperacao operacao;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date data;
	private BigDecimal valor;

	public Transacao(Integer id, String banco, String agencia, String numeroContaOrigem, String numeroContaDestino,
			TipoOperacao operacao, Date data, BigDecimal valor) {
		this.id = id;
		this.banco = banco;
		this.agencia = agencia;
		this.numeroContaOrigem = numeroContaOrigem;
		this.numeroContaDestino = numeroContaDestino;
		this.operacao = operacao;
		this.data = data.;
		this.valor = valor;
		
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getNumeroContaOrigem() {
		return numeroContaOrigem;
	}

	public void setNumeroContaOrigem(String numeroContaOrigem) {
		this.numeroContaOrigem = numeroContaOrigem;
	}

	public String getNumeroContaDestino() {
		return numeroContaDestino;
	}

	public void setNumeroContaDestino(String numeroContaDestino) {
		this.numeroContaDestino = numeroContaDestino;
	}

	public TipoOperacao getOperacao() {
		return operacao;
	}

	public void setOperacao(TipoOperacao operacao) {
		this.operacao = operacao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}


	
	

}
