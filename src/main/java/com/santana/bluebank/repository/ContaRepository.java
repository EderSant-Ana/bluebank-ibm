package com.santana.bluebank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.santana.bluebank.entities.Conta;

public interface ContaRepository extends JpaRepository<Conta, Integer>{

	Optional<Conta> findByNumeroConta(String numeroConta);





}
