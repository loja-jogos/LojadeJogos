/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lojajogos.dao;

import br.com.lojajogos.config.Conexao;
import br.com.lojajogos.model.Loja;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fabio
 */
public class LojaDAO {
    
    public void inserir(Loja loja) throws SQLException{

        String sql = "INSERT INTO loja "
                   + "(nome_loja, telefone, endereco) "
                   + "VALUES (?, ?, ?)";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)){

            stmt.setString(1, loja.getNomeLoja());
            stmt.setString(2, loja.getTelefone());
            stmt.setString(3, loja.getEndereco());

            stmt.executeUpdate();

            try (ResultSet chaves = stmt.getGeneratedKeys()){

                if (chaves.next()){
                    loja.setIdLoja(chaves.getLong(1));
                }
            }
        }
    }
    
    public List<Loja> listarTodos() throws SQLException{

        List<Loja> lojas = new ArrayList<>();

        String sql = "SELECT * FROM loja ORDER BY nome_loja";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet resultado = stmt.executeQuery()){

            while (resultado.next()){

                Loja loja = mapearLoja(resultado);

                lojas.add(loja);
            }
        }

        return lojas;
    }
     
    public Loja buscarPorId(Long idLoja) throws SQLException{

        String sql = "SELECT * FROM loja WHERE id_loja = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)){

            stmt.setLong(1, idLoja);

            try (ResultSet resultado = stmt.executeQuery()){

                if (resultado.next()){
                    return mapearLoja(resultado);
                }
            }
        }

        return null;
    }
    
    public boolean atualizar(Loja loja) throws SQLException{

        String sql = "UPDATE loja SET "
                   + "nome_loja = ?, "
                   + "telefone = ?, "
                   + "endereco = ? "
                   + "WHERE id_loja = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)){

            stmt.setString(1, loja.getNomeLoja());
            stmt.setString(2, loja.getTelefone());
            stmt.setString(3, loja.getEndereco());
            stmt.setLong(4, loja.getIdLoja());

            return stmt.executeUpdate() > 0;
        }
    }
    
    public boolean excluir(Long idLoja) throws SQLException{

        String sql = "DELETE FROM loja WHERE id_loja = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)){

            stmt.setLong(1, idLoja);

            return stmt.executeUpdate() > 0;
        }
    }
    
    private Loja mapearLoja(ResultSet resultado)
            throws SQLException{

        Loja loja = new Loja();

        loja.setIdLoja(resultado.getLong("id_loja"));
        loja.setNomeLoja(resultado.getString("nome_loja"));
        loja.setTelefone(resultado.getString("telefone"));
        loja.setEndereco(resultado.getString("endereco"));

        return loja;
    }
  
}
