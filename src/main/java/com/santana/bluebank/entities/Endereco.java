package com.santana.bluebank.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_enderecos")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Endereco implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "endereco_id")
	private Integer id;
	
	@NotEmpty(message="Logradouro é um atributo obrigatório") 
	@NotNull(message="Logradouro é um atributo obrigatório")
	@Column(name = "logradouro", nullable = false)
	private String logradouro;
	
	@NotEmpty(message="Número é um atributo obrigatório") 
	@NotNull(message="Número é um atributo obrigatório")
	@Column(name = "numero", nullable = false)
	private String numero;
	
	@Column(name = "complemento", nullable = true)
	private String complemento;
	
	@NotEmpty(message="Bairro é um campo obrigatório") 
	@NotNull(message="Bairro é um campo obrigatório")
	@Column(name = "bairro", nullable = false)
	private String bairro;
	
	@NotEmpty(message="Cep é um campo obrigatório") 
	@NotNull(message="Cep é um campo obrigatório")
	@Column(name = "Cep", nullable = false)
	private String cep;
	
	@NotEmpty(message="Estado é um campo obrigatório") 
	@NotNull(message="Estado é um campo obrigatório")	
	@Pattern(regexp="^(AC|AL|AP|AM|BA|CE|DF|ES|GO|MA|MT|MS|MG|PA|PB|PR|PE|PI|RJ|RN|RS|RO|RR|SC|SP|SE|TO)$",message="Estado inválido")
	@Column(name = "estado", nullable = false)
	private String estado;
	
	@NotEmpty(message="Cidade é um campo obrigatório") 
	@NotNull(message="Cidade é um campo obrigatório")
	@Column(name = "cidade", nullable = false)
	@Pattern(regexp="^[a-zA-Z\\u0080-\\u024F\\s\\/\\-\\)\\(\\`\\.\\\"\\']{2,60}+$",message = "Corrija os dados informados no campo cidade")
	private String cidade;
	
	@JsonIgnore
	@Transient
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	public Endereco(Integer id, String logradouro, String numero, String complemento, String bairro, 
			String cep, String estado, String cidade) {
		this.id = id;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cep = cep;
		this.estado = estado;
		this.cidade = cidade;
	}
}
