/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lojajogos.dao;

import br.com.lojajogos.config.Conexao;
import br.com.lojajogos.model.Desenvolvedora;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Types;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fabio
 */
public class DesenvolvedoraDAO {
    
    public void inserir(Desenvolvedora desenvolvedora) throws SQLException{
        
        String sql = "INSERT INTO desenvolvedora "
                   + "(nome, pais, data_funfacao) "
                   + "VALUES (?, ?, ?)";
        
        try(Connection conexao = Conexao.conectar();
            PreparedStatement stmt = conexao.prepareStatement(
                    sql, Statement.RETURN_GENERATED_KEYS)){
            
            stmt.setString(1, desenvolvedora.getNome());
            stmt.setString(2, desenvolvedora.getPais());
            
            if(desenvolvedora.getDataFundacao() != null){
               stmt.setDate(3, Date.valueOf(
                    desenvolvedora.getDataFundacao()));
            } else{
                stmt.setNull(3, Types.DATE);
            }
            
            stmt.executeUpdate();
            
            try(ResultSet chaves = stmt.getGeneratedKeys()){
                if(chaves.next()){
                   desenvolvedora.setIdDesenvolvedora(
                            chaves.getLong(1));
                }
            }
        }
    }
    
    public List<Desenvolvedora> ListarTodos()
            throws SQLException{
        
        List<Desenvolvedora> desenvolvedoras =
                new ArrayList<>();
        
        String sql = "SELECT * FROM desenvolvedora ORDER BY nome";
        
        try(Connection conexao = Conexao.conectar();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery()){
            
            while(resultado.next()){
                desenvolvedoras.add(
                        mapearDesenvolvedora(resultado));
            }
        }
        
        return desenvolvedoras;
    }
    
    public Desenvolvedora buscarPorId(Long id)
            throws SQLException{
        
        String sql = "SELECT * FROM desenvolvedora "
                   + "WHERE id_desenvolvedora = ?";
        
        try(Connection conexao = Conexao.conectar();
            PreparedStatement stmt = conexao.prepareStatement(sql)){
            
            stmt.setLong(1, id);
            
            try(ResultSet resultado = stmt.executeQuery()){
                
                if(resultado.next()){
                    return mapearDesenvolvedora(resultado);
                }
            }
        }
        
        return null;
    }
    
    public boolean atualizar(Desenvolvedora desenvolvedora)
            throws SQLException{
        
        String sql = "UPDATE desenvolvedora SET"
                   + "nome = ?, pais = ?, data_fundacao = ? "
                   + "WHERE id_desenvolvedora = ?";
        
        try(Connection conexao = Conexao.conectar();
            PreparedStatement stmt = conexao.prepareStatement(sql)){
            
            stmt.setString(1, desenvolvedora.getNome());
            stmt.setString(2, desenvolvedora.getPais());
            
            if(desenvolvedora.getDataFundacao() != null){
               stmt.setDate(3, Date.valueOf(
                       desenvolvedora.getDataFundacao()));
            } else{
                stmt.setNull(3, Types.DATE);
            }
            
            stmt.setLong(4, desenvolvedora.getIdDesenvolvedora());
            
            return stmt.executeUpdate() > 0;
        }
    }
    
    public boolean excluir(Long id) throws SQLException{
        
        String sql = "DELETE FROM desenvolvedora "
                   + "WHERE id_desenvolvedora = ?";
        
        try(Connection conexao = Conexao.conectar();
            PreparedStatement stmt = conexao.prepareStatement(sql)){
            
            stmt.setLong(1, id);
            
            return stmt.executeUpdate() > 0;
        }
    }
    
    private Desenvolvedora mapearDesenvolvedora(
            ResultSet resultado) throws SQLException{
        
        Desenvolvedora desenvolvedora = new Desenvolvedora();
        
        desenvolvedora.setIdDesenvolvedora(
                resultado.getLong("id_desenvolvedora"));
        
        desenvolvedora.setNome(
                resultado.getString("nome"));
        
        desenvolvedora.setPais(
                resultado.getString("pais"));
        
        Date data = resultado.getDate("data_fundacao");
        
        if(data != null){
           desenvolvedora.setDataFundacao(
                    data.toLocalDate());
        }
        
        return desenvolvedora;
      
    }
}
