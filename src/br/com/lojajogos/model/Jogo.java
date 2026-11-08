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
public class Jogo {
    
    private Long idJogo;
    private String nome;
    private BigDecimal preco;
    private Long idDesenvolvedora;
    private LocalDate dataLancamento;

    public Long getIdJogo(){
        return idJogo;
    }

    public void setIdJogo(Long idJogo){
        this.idJogo = idJogo;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public BigDecimal getPreco(){
        return preco;
    }

    public void setPreco(BigDecimal preco){
        this.preco = preco;
    }

    public Long getIdDesenvolvedora(){
        return idDesenvolvedora;
    }

    public void setIdDesenvolvedora(Long idDesenvolvedora){
        this.idDesenvolvedora = idDesenvolvedora;
    }

    public LocalDate getDataLancamento(){
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento){
        this.dataLancamento = dataLancamento;
    }

    
}
