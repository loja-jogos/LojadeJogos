/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lojajogos.dao;
import br.com.lojajogos.config.Conexao;
import br.com.lojajogos.model.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fabio
 */
public class ClienteDAO {
    public void inserir(Cliente cliente) throws SQLException{
        
        String sql = "INSERT INTO cliente "
                + "(nome, cpf, telefone, email) "
                + "VALUES (?, ?, ?, ?)";
        
        try(Connection conexao = Conexao.conectar();
                PreparedStatement stmt = conexao.prepareStatement(
                        sql, Statement.RETURN_GENERATED_KEYS)){
            
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getEmail());
            
            stmt.executeUpdate();
            
            try(ResultSet chaves = stmt.getGeneratedKeys()){
                if(chaves.next()){
                    cliente.setIdCliente(chaves.getLong(1));
                }
            }
        }
    }
    
    public List<Cliente> ListarTodos() throws SQLException{
        
        List<Cliente> clientes = new ArrayList<>();
        
        String sql = "SELECT * FROM cliente ORDER BY nome";
        
        try(Connection conexao = Conexao.conectar();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery()){
            
            while(resultado.next()){
                clientes.add(mapearCliente(resultado));
            }
        }
        
        return clientes;
    }
    
    public Cliente buscarPorId(Long idCliente) throws SQLException{
        
        String sql = "SELECT * FROM cliente where id_cliente = ?";
        
        try(Connection conexao = Conexao.conectar();
            PreparedStatement stmt = conexao.prepareStatement(sql)){
                
            stmt.setLong(1, idCliente);
            
            try(ResultSet resultado = stmt.executeQuery()){
                
                if(resultado.next()){
                    return mapearCliente(resultado);
                }
            }
        }
        
        return null;
    }
    
    public boolean atualizar(Cliente cliente) throws SQLException{
        
        String sql = "UPDATE cliente SET "
                + "nome = ?, cpf = ?, telefone = ?, email = ? "
                + "WHERE id_cliente = ?";
        
        try(Connection conexao = Conexao.conectar();
            PreparedStatement stmt = conexao.prepareStatement(sql)){
            
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getEmail());
            stmt.setLong(5, cliente.getIdCliente());
            
            return stmt.executeUpdate() > 0;
        }
    }
    
    public boolean excluir(Long idCliente) throws SQLException{
        
        String sql = "DELETE FROM cliente WHERE id_cliente = ?";
        
        try(Connection conexao = Conexao.conectar();
            PreparedStatement stmt = conexao.prepareStatement(sql)){
            
            stmt.setLong(1, idCliente);
            
            return stmt.executeUpdate() > 0;
        }
    }
    
    private Cliente mapearCliente(ResultSet resultado)
            throws SQLException{
        
        Cliente cliente = new Cliente();
        
        cliente.setIdCliente(resultado.getLong("id_cliente"));
        cliente.setNome(resultado.getString("nome"));
        cliente.setCpf(resultado.getString("cpf"));
        cliente.setTelefone(resultado.getString("telefone"));
        cliente.setEmail(resultado.getString("email"));
        
        Timestamp data = resultado.getTimestamp("data_cadastro");
        
        if(data != null){
           cliente.setDataCadastro(data.toLocalDateTime());
        }
        
        return cliente;
        
    }    
}
