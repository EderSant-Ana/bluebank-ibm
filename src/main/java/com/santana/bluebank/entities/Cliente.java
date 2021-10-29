package com.santana.bluebank.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

import com.santana.bluebank.enums.TipoRegimeEmpregaticio;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "tb_clientes")
@NoArgsConstructor
@Data
public class Cliente implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cliente_id")
	private Integer id;
	
	@Column(name = "nome", unique = true)	
	private String nome;

	@NotEmpty
	@CPF
	private String cpf;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "cliente_id")
	private List<Endereco> enderecos = new ArrayList<>();
	
	@ElementCollection
	@CollectionTable(name = "tb_telefones")
	private Set<String> telefones = new HashSet<>();
	
	private Integer idade;
	
	@Email
	private String email;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "regime_contratacao")
	private TipoRegimeEmpregaticio tipoRegimeEmpregaticio;
	
	@Column(name = "renda_mensal")
	private BigDecimal rendaMensalIndividual;

	@ToString.Exclude
	@OneToOne(mappedBy = "cliente")
	private Conta conta;

	public Cliente(Integer id, String nome, @NotEmpty @CPF String cpf, Integer idade, String email,
			TipoRegimeEmpregaticio tipoRegimeEmpregaticio, BigDecimal rendaMensalIndividual) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.idade = idade;
		this.email = email;
		this.tipoRegimeEmpregaticio = tipoRegimeEmpregaticio;
		this.rendaMensalIndividual = rendaMensalIndividual;
	}

}
