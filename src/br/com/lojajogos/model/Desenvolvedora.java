/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lojajogos.model;

import java.time.LocalDate;

/**
 *
 * @author fabio
 */
public class Desenvolvedora {

    private Long idDesenvolvedora;
    private String nome;
    private String pais;
    private LocalDate dataFundacao;

    public Long getIdDesenvolvedora(){
        return idDesenvolvedora;
    }

    public void setIdDesenvolvedora(Long idDesenvolvedora){
        this.idDesenvolvedora = idDesenvolvedora;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getPais(){
        return pais;
    }

    public void setPais(String pais){
        this.pais = pais;
    }

    public LocalDate getDataFundacao(){
        return dataFundacao;
    }

    public void setDataFundacao(LocalDate dataFundacao){
        this.dataFundacao = dataFundacao;
    }

}