package com.santana.bluebank.service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santana.bluebank.entities.Conta;
import com.santana.bluebank.entities.Transacao;
import com.santana.bluebank.exception.ContaNaoEncontradaException;
import com.santana.bluebank.exception.TransacaoException;
import com.santana.bluebank.repository.ContaRepository;
import com.santana.bluebank.repository.TransacoesRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class TransacoesService {

	private ContaRepository contaRepository;
	private TransacoesRepository transacoesRepository;

	public Transacao transferir(Transacao obj) throws TransacaoException, ContaNaoEncontradaException {

		Conta contaOrigem = verificaConta(obj.getNumeroContaOrigem());
		Conta contaDestino = verificaConta(obj.getNumeroContaDestino());
		Transacao transacaoSalva = null;

		if (verificaSaldo(contaOrigem.getLimiteDisponivel(), obj.getValor())) {

			obj.setNomeDepositante(contaOrigem.getCliente().getNome());
			contaOrigem.setLimiteDisponivel(contaOrigem.getLimiteDisponivel().subtract(obj.getValor()));
			obj.setNomeDestinatario(contaDestino.getCliente().getNome());
			contaDestino.setLimiteDisponivel(contaDestino.getLimiteDisponivel().add(obj.getValor()));

			transacaoSalva = transacoesRepository.save(obj);

		}

		return transacaoSalva;
	}

	// Não permite fazer transferências com valor superior ao saldo em conta ou
	// transferências de valores negativos ou zerados.
	public boolean verificaSaldo(BigDecimal saldo, BigDecimal valorTransferencia) throws TransacaoException {

		if (valorTransferencia.compareTo(saldo) > 0) {
			throw new TransacaoException("Valor da transferência Maior que o Saldo disponível!");

		} else if (valorTransferencia.compareTo(new BigDecimal("0")) == 0) {
			throw new TransacaoException("Valor da transferência não pode ser 0!");

		} else if (valorTransferencia.compareTo(new BigDecimal("0")) < 0) {
			throw new TransacaoException("Valor da transferência não pode ser negativo!");

		}

		return true;
	}

	// Verifica se as conta está cadastradas no banco.
	public Conta verificaConta(String c) throws ContaNaoEncontradaException {

		c = validaConta(c);

		Conta conta = contaRepository.findByNumeroConta(c)
				.orElseThrow(() -> new ContaNaoEncontradaException("Conta não encontrada!"));

		return conta;

	}

	// Valida numero da conta, caso esteja sem o `-` é adicionado
	public String validaConta(String c) {

		if (c.equals("") || c.length() < 6 || c.length() > 7) {
			return null;

		} else if (c.length() == 6) {
			StringBuilder str = new StringBuilder();
			str.append(c);
			str.insert(5, "-");
			return c = str.toString();
		}

		return c;
	}

	public List<Transacao> listarTransações() throws TransacaoException{

		List<Transacao> transacoes = transacoesRepository.findAll();

		if(transacoes.isEmpty()) {
			throw new TransacaoException("Nenhuma Transferência Realizada!");
		}

		Collections.sort(transacoes);
		return transacoes;
	}

}
