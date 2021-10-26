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
import com.santana.bluebank.repository.ClienteRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/v1")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ClienteController {

	private ClienteRepository clienteRepository;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/clientes")
	public Cliente createEmployee(@RequestBody Cliente cliente) {

		return clienteRepository.save(cliente);
	}
	
	@GetMapping(value = "/clientes")
	public List<Cliente> getAllClientes(){
		List<Cliente> list =  clienteRepository.findAll();
		return list;
	}
	
}
