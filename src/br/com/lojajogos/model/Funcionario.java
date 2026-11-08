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
public class Funcionario {
    
    private Long idFuncionario;
    private Long idSupervisor;
    private Long idLoja;
    private String nome;
    private String cpf;
    private String telefone;
    private LocalDate dataContratacao;

    public Long getIdFuncionario(){
        return idFuncionario;
    }

    public void setIdFuncionario(Long idFuncionario){
        this.idFuncionario = idFuncionario;
    }

    public Long getIdSupervisor(){
        return idSupervisor;
    }

    public void setIdSupervisor(Long idSupervisor){
        this.idSupervisor = idSupervisor;
    }

    public Long getIdLoja(){
        return idLoja;
    }

    public void setIdLoja(Long idLoja){
        this.idLoja = idLoja;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getCpf(){
        return cpf;
    }

    public void setCpf(String cpf){
        this.cpf = cpf;
    }

    public String getTelefone(){
        return telefone;
    }

    public void setTelefone(String telefone){
        this.telefone = telefone;
    }

    public LocalDate getDataContratacao(){
        return dataContratacao;
    }

    public void setDataContratacao(LocalDate dataContratacao){
        this.dataContratacao = dataContratacao;
    }

    
}
