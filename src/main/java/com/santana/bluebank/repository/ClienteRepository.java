package com.santana.bluebank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.santana.bluebank.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

	Optional<Cliente> findByCpf(String cpf);

}
