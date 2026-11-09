/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lojajogos.dao;

import br.com.lojajogos.config.Conexao;
import br.com.lojajogos.model.Estoque;

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
public class EstoqueDAO {
    
    public void inserir(Estoque estoque) throws SQLException{

        String sql = "INSERT INTO estoque "
                   + "(id_jogo, quantidade_disponivel) "
                   + "VALUES (?, ?)";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)){

            stmt.setLong(1, estoque.getIdJogo());
            stmt.setLong(2, estoque.getQuantidadeDisponivel());

            stmt.executeUpdate();

            try (ResultSet chaves = stmt.getGeneratedKeys()){

                if (chaves.next()){
                    estoque.setIdEstoque(chaves.getLong(1));
                }
            }
        }
    }
    
    public List<Estoque> listarTodos() throws SQLException{

        List<Estoque> estoques = new ArrayList<>();

        String sql = "SELECT * FROM estoque ORDER BY id_jogo";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet resultado = stmt.executeQuery()){

            while (resultado.next()){

                Estoque estoque = mapearEstoque(resultado);

                estoques.add(estoque);
            }
        }

        return estoques;
    }
    
    public Estoque buscarPorId(Long idEstoque)
            throws SQLException{

        String sql = "SELECT * FROM estoque WHERE id_estoque = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)){

            stmt.setLong(1, idEstoque);

            try (ResultSet resultado = stmt.executeQuery()){

                if (resultado.next()){
                    return mapearEstoque(resultado);
                }
            }
        }

        return null;
    }
    
    public boolean atualizar(Estoque estoque)
            throws SQLException{

        String sql = "UPDATE estoque SET "
                   + "id_jogo = ?, "
                   + "quantidade_disponivel = ? "
                   + "WHERE id_estoque = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)){

            stmt.setLong(1, estoque.getIdJogo());
            stmt.setLong(2, estoque.getQuantidadeDisponivel());
            stmt.setLong(3, estoque.getIdEstoque());

            return stmt.executeUpdate() > 0;
        }
    }
    
    public boolean excluir(Long idEstoque)
            throws SQLException{

        String sql = "DELETE FROM estoque WHERE id_estoque = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)){

            stmt.setLong(1, idEstoque);

            return stmt.executeUpdate() > 0;
        }
    }
    
    private Estoque mapearEstoque(ResultSet resultado)
            throws SQLException{

        Estoque estoque = new Estoque();

        estoque.setIdEstoque(resultado.getLong("id_estoque"));

        estoque.setIdJogo(resultado.getLong("id_jogo"));

        estoque.setQuantidadeDisponivel(
                resultado.getLong("quantidade_disponivel"));

        Timestamp data = resultado.getTimestamp("ultima_atualizacao");

        if (data != null){
            estoque.setUltimaAtualizacao(data.toLocalDateTime());
        }

        return estoque;
    }
    
}
