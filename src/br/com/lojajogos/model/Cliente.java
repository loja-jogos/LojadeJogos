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
public class Cliente {
    
    private long idCliente;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private LocalDateTime dataCadastro;
    
    public Cliente(){
    }
    
    public Cliente(String nome, String cpf,String telefone, String email){
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
    }
    
    public long getIdCliente(){
        return idCliente;
    }
    
    public void setIdCliente(long idCliente){
        this.idCliente = idCliente;
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
    
    public void setCpf (String cpf){
        this.cpf = cpf;
    }
    
    public String getTelefone(){
        return telefone;
    }
    
    public void setTelefone(String telefone){
        this.telefone = telefone;
    }
    
       public String getEmail(){
        return email;
    }
    
    public void setEmail (String email){
        this.email = email;
    }
    
     public LocalDateTime getDataCadastro(){
        return dataCadastro;
    }
    
    public void setDataCadastro (LocalDateTime dataCadastro){
        this.dataCadastro = dataCadastro;
    }
    
    @Override
    public String toString(){
        return nome;
    }
}
