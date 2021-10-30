package com.santana.bluebank.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.persistence.Column;
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
	
	@Column(name = "data_hora_operacao")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", locale = "pt-BR", timezone = "Brazil/East")
	private final LocalDateTime data = LocalDateTime.ofInstant(Instant.now(), ZoneId.of("America/Sao_Paulo"));
	
	private BigDecimal valor;

	public Transacao(Integer id, String banco, String agencia, String numeroContaOrigem, String numeroContaDestino,
			TipoOperacao operacao, BigDecimal valor) {
		this.id = id;
		this.banco = banco;
		this.agencia = agencia;
		this.numeroContaOrigem = numeroContaOrigem;
		this.numeroContaDestino = numeroContaDestino;
		this.operacao = operacao;
		this.valor = valor;
	}
	
	


}
