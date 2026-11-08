/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lojajogos.model;

import java.time.LocalDate;
import java.math.BigDecimal;

/**
 *
 * @author fabio
 */
public class Dlc {
    
    private Long idJogo;
    private Long idDlc;
    private String nome;
    private String conteudo;
    private LocalDate dataDeAquisicao;
    private BigDecimal valor;

    public Long getIdJogo(){
        return idJogo;
    }

    public void setIdJogo(Long idJogo){
        this.idJogo = idJogo;
    }

    public Long getIdDlc(){
        return idDlc;
    }

    public void setIdDlc(Long idDlc){
        this.idDlc = idDlc;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getConteudo(){
        return conteudo;
    }

    public void setConteudo(String conteudo){
        this.conteudo = conteudo;
    }

    public LocalDate getDataDeAquisicao(){
        return dataDeAquisicao;
    }

    public void setDataDeAquisicao(LocalDate dataDeAquisicao){
        this.dataDeAquisicao = dataDeAquisicao;
    }

    public BigDecimal getValor(){
        return valor;
    }

    public void setValor(BigDecimal valor){
        this.valor = valor;
    }
    
}
