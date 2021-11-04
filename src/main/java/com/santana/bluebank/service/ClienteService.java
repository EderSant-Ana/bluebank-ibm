package com.santana.bluebank.service;

import java.util.List;

import com.santana.bluebank.exception.ClienteJaCadastradoException;
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

	public Cliente createCliente(Cliente obj) throws ClienteJaCadastradoException {

		Cliente clienteSaved = null;

		if(verificaCadastro(obj.getCpf(), obj.getEmail())){
			clienteSaved = clienteRepository.save(obj);
			clienteSaved.setConta(contaRepository.save(new Conta(null, clienteSaved)));
		}
		return clienteSaved;
	}

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}


	// Não permite o cadastro de CPFs e e-mails já existentes no banco
	public boolean verificaCadastro(String cpf, String email) throws ClienteJaCadastradoException {

		if (clienteRepository.existsByCpf(cpf)) {
			throw new ClienteJaCadastradoException("Já existe um cliente cadastrado com este CPF!");

		} else if (clienteRepository.existsByEmail(email)) {
			throw new ClienteJaCadastradoException("Já existe um cliente cadastrado com este Email!");

		}
		return true;
	}



}
