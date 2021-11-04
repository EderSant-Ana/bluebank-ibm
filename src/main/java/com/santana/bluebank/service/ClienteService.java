package com.santana.bluebank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santana.bluebank.entities.Cliente;
import com.santana.bluebank.entities.Conta;
import com.santana.bluebank.repository.ClienteRepository;
import com.santana.bluebank.repository.ContaRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ClienteService {

	private ContaRepository contaRepository;
	private ClienteRepository clienteRepository;

	public Cliente createCliente(Cliente cliente) {
		
		Cliente clienteSaved = clienteRepository.save(cliente);
		clienteSaved.setConta(contaRepository.save(new Conta(null, cliente)));
		
		return clienteSaved;
	}

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

}
