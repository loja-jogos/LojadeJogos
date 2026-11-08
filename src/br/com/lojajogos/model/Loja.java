/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lojajogos.model;

/**
 *
 * @author fabio
 */
public class Loja {
    
    private Long idLoja;
    private String nomeLoja;
    private String telefone;
    private String endereco;

    public Long getIdLoja(){
        return idLoja;
    }

    public void setIdLoja(Long idLoja){
        this.idLoja = idLoja;
    }

    public String getNomeLoja(){
        return nomeLoja;
    }

    public void setNomeLoja(String nomeLoja){
        this.nomeLoja = nomeLoja;
    }

    public String getTelefone(){
        return telefone;
    }

    public void setTelefone(String telefone){
        this.telefone = telefone;
    }

    public String getEndereco(){
        return endereco;
    }

    public void setEndereco(String endereco){
        this.endereco = endereco;
    }

    
}
