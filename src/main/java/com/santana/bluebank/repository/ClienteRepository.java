package com.santana.bluebank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.santana.bluebank.entities.Cliente;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
    Optional<Cliente> findByCpf(String cpf);
    boolean existsByCpf(String cpf);

    Optional<Cliente> findByEmail(String email);
    boolean existsByEmail(String email);
}
