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
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.santana.bluebank.enums.TipoOperacao;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_transacoes")
@NoArgsConstructor
@Data
public class Transacao implements Serializable, Comparable<Transacao>{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String banco = "Blue-Bank";
	private String agencia = "0001";

	private String numeroContaOrigem;
	private String nomeDepositante;

	private String numeroContaDestino;
	private String nomeDestinatario;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	private TipoOperacao operacao;
	
	@Column(name = "data_hora_operacao")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", locale = "pt-BR", timezone = "Brazil/East")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private final LocalDateTime data = LocalDateTime.ofInstant(Instant.now(), ZoneId.of("America/Sao_Paulo"));

	@NotNull
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

	@Override
	public int compareTo(Transacao transacao) {

		if (this.data.isAfter(transacao.getData())) {
			return -1;
		} if (this.data.isBefore(transacao.getData())) {
			return 1;
		}
		return 0;
	}

	//Usar somente para testes
	public Transacao(Integer id, String banco, String agencia, String numeroContaOrigem, String nomeDepositante,
			String numeroContaDestino, String nomeDestinatario, @NotNull TipoOperacao operacao,
			@NotNull BigDecimal valor) {
		this.id = id;
		this.banco = banco;
		this.agencia = agencia;
		this.numeroContaOrigem = numeroContaOrigem;
		this.nomeDepositante = nomeDepositante;
		this.numeroContaDestino = numeroContaDestino;
		this.nomeDestinatario = nomeDestinatario;
		this.operacao = operacao;
		this.valor = valor;
	}
}
