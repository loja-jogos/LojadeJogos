/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lojajogos.model;

/**
 *
 * @author fabio
 */
public class Genero {
    
    private Long idGenero;
    private String nome;

    public Long getIdGenero(){
        return idGenero;
    }

    public void setIdGenero(Long idGenero){
        this.idGenero = idGenero;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }
    
}
