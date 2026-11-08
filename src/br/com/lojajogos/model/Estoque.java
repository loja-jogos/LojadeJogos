/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lojajogos.model;

import java.time.LocalDateTime;

/**
 *
 * @author fabio
 */
public class Estoque {
    
    private Long idEstoque;
    private Long idJogo;
    private Long quantidadeDisponivel;
    private LocalDateTime ultimaAtualizacao;

    public Long getIdEstoque(){
        return idEstoque;
    }

    public void setIdEstoque(Long idEstoque){
        this.idEstoque = idEstoque;
    }

    public Long getIdJogo(){
        return idJogo;
    }

    public void setIdJogo(Long idJogo){
        this.idJogo = idJogo;
    }

    public Long getQuantidadeDisponivel(){
        return quantidadeDisponivel;
    }

    public void setQuantidadeDisponivel(Long quantidadeDisponivel){
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public LocalDateTime getUltimaAtualizacao(){
        return ultimaAtualizacao;
    }

    public void setUltimaAtualizacao(LocalDateTime ultimaAtualizacao){
        this.ultimaAtualizacao = ultimaAtualizacao;
    }
    
}
