/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lojajogos.model;

/**
 *
 * @author fabio
 */
public class JogoGenero {
    
    private Long idJogo;
    private Long idGenero;
    private Boolean generoPrincipal;

    public Long getIdJogo(){
        return idJogo;
    }

    public void setIdJogo(Long idJogo){
        this.idJogo = idJogo;
    }

    public Long getIdGenero(){
        return idGenero;
    }

    public void setIdGenero(Long idGenero){
        this.idGenero = idGenero;
    }

    public Boolean getGeneroPrincipal(){
        return generoPrincipal;
    }

    public void setGeneroPrincipal(Boolean generoPrincipal){
        this.generoPrincipal = generoPrincipal;
    }

    
}
