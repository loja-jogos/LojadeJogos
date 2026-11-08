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
public class AquisicaoDlc {
    
    private Long idCliente;
    private Long idJogo;
    private Long idDlc;
    private LocalDateTime dataAquisicao;

    public Long getIdCliente(){
        return idCliente;
    }

    public void setIdCliente(Long idCliente){
        this.idCliente = idCliente;
    }

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

    public LocalDateTime getDataAquisicao(){
        return dataAquisicao;
    }

    public void setDataAquisicao(LocalDateTime dataAquisicao){
        this.dataAquisicao = dataAquisicao;
    }

    
}
