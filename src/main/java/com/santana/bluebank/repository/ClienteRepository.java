package com.santana.bluebank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.santana.bluebank.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);
}
