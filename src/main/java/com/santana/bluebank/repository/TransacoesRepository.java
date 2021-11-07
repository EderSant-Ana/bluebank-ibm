package com.santana.bluebank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.santana.bluebank.entities.Transacao;

public interface TransacoesRepository extends JpaRepository<Transacao, Integer>{
}
