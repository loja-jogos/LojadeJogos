/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lojajogos.model;

/**
 *
 * @author fabio
 */
public class FormaPagamento {
    
    private Long idFormaPagamento;
    private String descricao;

    public Long getIdFormaPagamento(){
        return idFormaPagamento;
    }

    public void setIdFormaPagamento(Long idFormaPagamento){
        this.idFormaPagamento = idFormaPagamento;
    }

    public String getDescricao(){
        return descricao;
    }

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }
    
}
