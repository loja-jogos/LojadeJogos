/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lojajogos.dao;

import br.com.lojajogos.config.Conexao;
import br.com.lojajogos.model.JogoGenero;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fabio
 */
public class JogoGeneroDAO {
    
    public void inserir(JogoGenero jogoGenero)
            throws SQLException{

        String sql = "INSERT INTO jogo_genero "
                   + "(id_jogo, id_genero, genero_principal) "
                   + "VALUES (?, ?, ?)";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)){

            stmt.setLong(1, jogoGenero.getIdJogo());
            stmt.setLong(2, jogoGenero.getIdGenero());

            stmt.setBoolean(3,
                    Boolean.TRUE.equals(jogoGenero.getGeneroPrincipal()));

            stmt.executeUpdate();
        }
    }
    
    public List<JogoGenero> listarTodos() throws SQLException{

        List<JogoGenero> relacionamentos = new ArrayList<>();

        String sql = "SELECT * FROM jogo_genero "
                   + "ORDER BY id_jogo, id_genero";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet resultado = stmt.executeQuery()){

            while (resultado.next()){

                JogoGenero jogoGenero =
                        mapearJogoGenero(resultado);

                relacionamentos.add(jogoGenero);
            }
        }

        return relacionamentos;
    }

    public JogoGenero buscarPorId(Long idJogo, Long idGenero)
            throws SQLException{

        String sql = "SELECT * FROM jogo_genero "
                   + "WHERE id_jogo = ? AND id_genero = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)){

            stmt.setLong(1, idJogo);
            stmt.setLong(2, idGenero);

            try (ResultSet resultado = stmt.executeQuery()){

                if (resultado.next()){
                    return mapearJogoGenero(resultado);
                }
            }
        }

        return null;
    }
    
    public boolean atualizar(JogoGenero jogoGenero)
            throws SQLException{

        String sql = "UPDATE jogo_genero SET "
                   + "genero_principal = ? "
                   + "WHERE id_jogo = ? AND id_genero = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)){

            stmt.setBoolean(1,
                    Boolean.TRUE.equals(jogoGenero.getGeneroPrincipal()));

            stmt.setLong(2, jogoGenero.getIdJogo());
            stmt.setLong(3, jogoGenero.getIdGenero());

            return stmt.executeUpdate() > 0;
        }
    }

    public boolean excluir(Long idJogo, Long idGenero)
            throws SQLException{

        String sql = "DELETE FROM jogo_genero "
                   + "WHERE id_jogo = ? AND id_genero = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)){

            stmt.setLong(1, idJogo);
            stmt.setLong(2, idGenero);

            return stmt.executeUpdate() > 0;
        }
    }
    
    private JogoGenero mapearJogoGenero(ResultSet resultado)
            throws SQLException{

        JogoGenero jogoGenero = new JogoGenero();

        jogoGenero.setIdJogo(resultado.getLong("id_jogo"));

        jogoGenero.setIdGenero(resultado.getLong("id_genero"));

        jogoGenero.setGeneroPrincipal(
                resultado.getBoolean("genero_principal"));

        return jogoGenero;
    }
    
}
