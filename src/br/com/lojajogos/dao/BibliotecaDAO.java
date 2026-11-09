/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lojajogos.dao;

import br.com.lojajogos.config.Conexao;
import br.com.lojajogos.model.Biblioteca;
import br.com.lojajogos.model.StatusAcessoBiblioteca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fabio
 */
public class BibliotecaDAO {
    
    public void inserir(Biblioteca biblioteca)
            throws SQLException{

        String sql = "INSERT INTO biblioteca "
                   + "(id_cliente, id_jogo, data_aquisicao, status_acesso) "
                   + "VALUES (?, ?, ?, ?)";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)){

            stmt.setLong(1, biblioteca.getIdCliente());
            stmt.setLong(2, biblioteca.getIdJogo());

            stmt.setTimestamp(3,
                    Timestamp.valueOf(biblioteca.getDataAquisicao()));

            StatusAcessoBiblioteca status = biblioteca.getStatusAcesso();

            if (status == null){
                status = StatusAcessoBiblioteca.LIBERADO;
            }

            stmt.setString(4, status.name());

            stmt.executeUpdate();

            biblioteca.setStatusAcesso(status);
        }
    }
    
    public List<Biblioteca> listarTodos() throws SQLException{

        List<Biblioteca> bibliotecas = new ArrayList<>();

        String sql = "SELECT * FROM biblioteca "
                   + "ORDER BY id_cliente, id_jogo";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet resultado = stmt.executeQuery()){

            while (resultado.next()){

                Biblioteca biblioteca = mapearBiblioteca(resultado);

                bibliotecas.add(biblioteca);
            }
        }

        return bibliotecas;
    }
    
    public Biblioteca buscarPorChave(Long idCliente, Long idJogo)
            throws SQLException{

        String sql = "SELECT * FROM biblioteca "
                   + "WHERE id_cliente = ? AND id_jogo = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)){

            stmt.setLong(1, idCliente);
            stmt.setLong(2, idJogo);

            try (ResultSet resultado = stmt.executeQuery()){

                if (resultado.next()){
                    return mapearBiblioteca(resultado);
                }
            }
        }

        return null;
    }
    
    public boolean atualizar(Biblioteca biblioteca)
            throws SQLException{

        String sql = "UPDATE biblioteca SET "
                   + "data_aquisicao = ?, "
                   + "status_acesso = ? "
                   + "WHERE id_cliente = ? AND id_jogo = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)){

            stmt.setTimestamp(1,
                    Timestamp.valueOf(biblioteca.getDataAquisicao()));

            StatusAcessoBiblioteca status = biblioteca.getStatusAcesso();

            if (status == null){
                status = StatusAcessoBiblioteca.LIBERADO;
            }

            stmt.setString(2, status.name());
            stmt.setLong(3, biblioteca.getIdCliente());
            stmt.setLong(4, biblioteca.getIdJogo());

            return stmt.executeUpdate() > 0;
        }
    }
    
    public boolean excluir(Long idCliente, Long idJogo)
            throws SQLException{

        String sql = "DELETE FROM biblioteca "
                   + "WHERE id_cliente = ? AND id_jogo = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)){

            stmt.setLong(1, idCliente);
            stmt.setLong(2, idJogo);

            return stmt.executeUpdate() > 0;
        }
    }
    
    private Biblioteca mapearBiblioteca(ResultSet resultado)
            throws SQLException{

        Biblioteca biblioteca = new Biblioteca();

        biblioteca.setIdCliente(resultado.getLong("id_cliente"));

        biblioteca.setIdJogo(resultado.getLong("id_jogo"));

        Timestamp data = resultado.getTimestamp("data_aquisicao");

        if (data != null){
            biblioteca.setDataAquisicao(data.toLocalDateTime());
        }

        biblioteca.setStatusAcesso(
                StatusAcessoBiblioteca.valueOf(
                        resultado.getString("status_acesso")));

        return biblioteca;
    }

    
}
