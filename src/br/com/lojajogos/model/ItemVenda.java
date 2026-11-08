/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lojajogos.model;

import java.math.BigDecimal;

/**
 *
 * @author fabio
 */
public class ItemVenda {
    
    private Long idVenda;
    private Long idJogo;
    private Long quantidade;
    private BigDecimal precoUnitario;

    public Long getIdVenda(){
        return idVenda;
    }

    public void setIdVenda(Long idVenda){
        this.idVenda = idVenda;
    }

    public Long getIdJogo(){
        return idJogo;
    }

    public void setIdJogo(Long idJogo){
        this.idJogo = idJogo;
    }

    public Long getQuantidade(){
        return quantidade;
    }

    public void setQuantidade(Long quantidade){
        this.quantidade = quantidade;
    }

    public BigDecimal getPrecoUnitario(){
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario){
        this.precoUnitario = precoUnitario;
    }

    
}
