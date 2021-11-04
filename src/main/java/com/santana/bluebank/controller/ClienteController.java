package com.santana.bluebank.controller;

import java.util.List;

import com.santana.bluebank.exception.ClienteJaCadastradoException;
import com.santana.bluebank.exception.EmailInvalidoException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.santana.bluebank.entities.Cliente;
import com.santana.bluebank.service.ClienteService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/v1")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ClienteController {

	private ClienteService clienteService;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/clientes")
	public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) throws ClienteJaCadastradoException, EmailInvalidoException {
		Cliente objSaved = clienteService.createCliente(cliente);
		return new ResponseEntity<>(objSaved, HttpStatus.OK);
	}
	
	@GetMapping(value = "/clientes")
	public List<Cliente> getAllClientes(){
		List<Cliente> list =  clienteService.findAll();
		return list;
	}
}
