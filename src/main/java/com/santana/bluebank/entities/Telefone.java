package com.santana.bluebank.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Entity
@Table(name = "tb_telefones")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Telefone implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;

    @NotEmpty(message="Telefone é um campo obrigatório")
    @NotNull(message="Telefone é um campo obrigatório")
    @Column(name = "telefones", nullable = false)
    @Pattern(regexp="^((\\(\\d{2}\\))|\\d{3})[- .]?\\d{4,5}[- .]?\\d{4}$",message = "Formatação inválida do número de telefone")
    private String tel;

    @JsonIgnore
    @Transient
    @JoinColumn(name = "cliente_cliente_id")
    private Cliente cliente;

    public Telefone(Integer id, String tel) {
        this.id = id;
        this.tel = tel;
    }
}

