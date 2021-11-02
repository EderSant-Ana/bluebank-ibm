package com.santana.bluebank.service;

import java.math.BigDecimal;

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
public class TransacoesService  {

    private ContaRepository contaRepository;
    private TransacoesRepository transacoesRepository;

    public Transacao trasferir(Transacao obj) throws TransacaoException, ContaNaoEncontradaException {

        Conta contaOrigem = verificaConta(obj.getNumeroContaOrigem());
        Conta contaDestino = verificaConta(obj.getNumeroContaDestino());

        if (contaDestino != null && contaOrigem != null ) {

            try {

                if (verificaSaldo(contaOrigem.getLimiteDisponivel(), obj.getValor())) {

                  	obj.setNomeDepositante(contaOrigem.getCliente().getNome());
                    contaOrigem.setLimiteDisponivel(contaOrigem.getLimiteDisponivel().subtract(obj.getValor()));
                    obj.setNomeDestinatario(contaDestino.getCliente().getNome());
                    contaDestino.setLimiteDisponivel(contaDestino.getLimiteDisponivel().add(obj.getValor()));

                    Transacao objSaved = transacoesRepository.save(obj);
                    return objSaved;

                }
            }catch (TransacaoException e){
                throw new TransacaoException(e.getMessage());

            }
        }

        throw new ContaNaoEncontradaException("Conta não encontrada!");
    }


    //Não permite fazer transferências com valor superior ao saldo em conta ou transferências de valores negativos ou zerados.
    public boolean verificaSaldo(BigDecimal saldo, BigDecimal valorTransferencia) throws TransacaoException{

        if (valorTransferencia.compareTo(saldo) > 0){
            throw new TransacaoException("Valor da transferência Maior que o Saldo disponível!");

        }else if(valorTransferencia.compareTo(new BigDecimal("0")) == 0){
            throw new TransacaoException("Valor da transferência não pode ser 0!");

        }else if (valorTransferencia.compareTo(new BigDecimal("0")) < 0){
            throw new TransacaoException("Valor da transferência não pode ser negativo!");

        }

        return true;
    }

    //Verifica se as conta está cadastradas no banco.
    public Conta verificaConta(String c) throws ContaNaoEncontradaException{

        c = validaConta(c);

        if (c != null) {
            try {
                Conta conta = contaRepository.findByNumeroConta(c);
                return conta;

            }catch (NullPointerException e){
                throw new ContaNaoEncontradaException("Conta não encontrada!");

            }
        }else {
            throw new ContaNaoEncontradaException("Confira o numero da conta!");
        }

    }

    //Valida numero da conta, caso esteja sem o `-` é adicionado
    public String validaConta(String c) {

        if (c.equals("") || c.length() < 6 || c.length() > 7){
            return null;

        }else if (c.length() == 6){
          StringBuilder str = new StringBuilder();
          str.append(c);
          str.insert(5,"-");
          return c = str.toString();
        }

        return c;
    }



}
