/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lojajogos.model;

import java.time.LocalDateTime;
import br.com.lojajogos.model.StatusVenda;

/**
 *
 * @author fabio
 */
public class Venda {
    
    private Long idVenda;
    private LocalDateTime dataHora;
    private StatusVenda status;
    private Long idCliente;
    private Long idFuncionario;
    private Long idFormaPagamento;

    public Long getIdVenda(){
        return idVenda;
    }

    public void setIdVenda(Long idVenda){
        this.idVenda = idVenda;
    }

    public LocalDateTime getDataHora(){
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora){
        this.dataHora = dataHora;
    }

    public StatusVenda getStatus(){
        return status;
    }

    public void setStatus(StatusVenda status){
        this.status = status;
    }

    public Long getIdCliente(){
        return idCliente;
    }

    public void setIdCliente(Long idCliente){
        this.idCliente = idCliente;
    }

    public Long getIdFuncionario(){
        return idFuncionario;
    }

    public void setIdFuncionario(Long idFuncionario){
        this.idFuncionario = idFuncionario;
    }

    public Long getIdFormaPagamento(){
        return idFormaPagamento;
    }

    public void setIdFormaPagamento(Long idFormaPagamento){
        this.idFormaPagamento = idFormaPagamento;
    }
    
}
