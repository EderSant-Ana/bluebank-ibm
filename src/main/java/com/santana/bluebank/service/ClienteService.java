package com.santana.bluebank.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.santana.bluebank.exception.ClienteJaCadastradoException;
import com.santana.bluebank.exception.EmailInvalidoException;
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

	public Cliente createCliente(Cliente obj) throws ClienteJaCadastradoException, EmailInvalidoException {

		Cliente clienteSaved = null;

		if(verificaCadastro(obj.getCpf(), obj.getEmail()) && validaEmail(obj.getEmail()) ){
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

	// Verifica se o email tem formato valido 
	public boolean validaEmail(String email) throws EmailInvalidoException {
		final String regex = "^(.+)@(.+)$";
		Pattern valid_email_format = Pattern.compile(regex);
		Matcher email_to_validate = valid_email_format.matcher(email);

		if (!email_to_validate.matches()) {
			throw new EmailInvalidoException("Por favor, insira um e-mail válido");

		}
		return true;
	}



}
