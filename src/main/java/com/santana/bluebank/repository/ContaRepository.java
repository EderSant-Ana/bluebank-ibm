package com.santana.bluebank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.santana.bluebank.entities.Conta;

public interface ContaRepository extends JpaRepository<Conta, Integer>{

	Conta findByNumeroConta(String numeroConta);





}
