/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lojajogos.model;

import java.time.LocalDateTime;
import br.com.lojajogos.model.StatusAcessoBiblioteca;

/**
 *
 * @author fabio
 */
public class Biblioteca {
    
    private Long idCliente;
    private Long idJogo;
    private LocalDateTime dataAquisicao;
    private StatusAcessoBiblioteca statusAcesso;

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

    public LocalDateTime getDataAquisicao(){
        return dataAquisicao;
    }

    public void setDataAquisicao(LocalDateTime dataAquisicao){
        this.dataAquisicao = dataAquisicao;
    }

    public StatusAcessoBiblioteca getStatusAcesso(){
        return statusAcesso;
    }

    public void setStatusAcesso(StatusAcessoBiblioteca statusAcesso){
        this.statusAcesso = statusAcesso;
    }

    
}
