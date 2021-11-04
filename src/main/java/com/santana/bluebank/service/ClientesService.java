package com.santana.bluebank.service;

import com.santana.bluebank.entities.Cliente;
import com.santana.bluebank.entities.Conta;
import com.santana.bluebank.exception.ClienteJaCadastradoException;
import com.santana.bluebank.repository.ClienteRepository;
import com.santana.bluebank.repository.ContaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ClientesService {

    private ClienteRepository clienteRepository;
    private ContaRepository contaRepository;

    public Cliente save(Cliente obj) throws ClienteJaCadastradoException {

        Cliente clienteSaved = null;

        if(verificaCadastro(obj.getCpf(), obj.getEmail())){
            clienteSaved = clienteRepository.save(obj);
            clienteSaved.setConta(contaRepository.save(new Conta(null, clienteSaved)));
        }
        return clienteSaved;
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
