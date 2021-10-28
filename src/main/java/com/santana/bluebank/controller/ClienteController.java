package com.santana.bluebank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.santana.bluebank.entities.Cliente;
import com.santana.bluebank.entities.Conta;
import com.santana.bluebank.repository.ClienteRepository;
import com.santana.bluebank.repository.ContaRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/v1")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ClienteController {

	private ClienteRepository clienteRepository;
	private ContaRepository contaRepository;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/clientes")
	public Cliente createEmployee(@RequestBody Cliente cliente) {
		
		Cliente clienteSaved = clienteRepository.save(cliente);
		clienteSaved.setConta(contaRepository.save(new Conta(null, cliente)));
		
		return clienteSaved;
	}
	
	@GetMapping(value = "/clientes")
	public List<Cliente> getAllClientes(){
		List<Cliente> list =  clienteRepository.findAll();
		return list;
	}
	
}
