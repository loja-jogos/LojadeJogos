/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lojajogos.dao;

import br.com.lojajogos.config.Conexao;
import br.com.lojajogos.model.Genero;

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
public class GeneroDAO {
    
     public void inserir(Genero genero) throws SQLException{

        String sql = "INSERT INTO genero "
                   + "(nome) VALUES (?)";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)){

            stmt.setString(1, genero.getNome());

            stmt.executeUpdate();

            try (ResultSet chaves = stmt.getGeneratedKeys()){

                if (chaves.next()){
                    genero.setIdGenero(chaves.getLong(1));
                }
            }
        }
    }
     
    public List<Genero> listarTodos() throws SQLException{

        List<Genero> generos = new ArrayList<>();

        String sql = "SELECT * FROM genero ORDER BY nome";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet resultado = stmt.executeQuery()){

            while (resultado.next()){

                Genero genero = mapearGenero(resultado);

                generos.add(genero);
            }
        }

        return generos;
    }
    
    public Genero buscarPorId(Long idGenero) throws SQLException{

        String sql = "SELECT * FROM genero WHERE id_genero = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)){

            stmt.setLong(1, idGenero);

            try (ResultSet resultado = stmt.executeQuery()){

                if (resultado.next()){
                    return mapearGenero(resultado);
                }
            }
        }

        return null;
    }
    
    public boolean atualizar(Genero genero) throws SQLException{

        String sql = "UPDATE genero SET "
                   + "nome = ? "
                   + "WHERE id_genero = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)){

            stmt.setString(1, genero.getNome());
            stmt.setLong(2, genero.getIdGenero());

            return stmt.executeUpdate() > 0;
        }
    }
    
    public boolean excluir(Long idGenero) throws SQLException{

        String sql = "DELETE FROM genero WHERE id_genero = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)){

            stmt.setLong(1, idGenero);

            return stmt.executeUpdate() > 0;
        }
    }
    
    private Genero mapearGenero(ResultSet resultado)
            throws SQLException{

        Genero genero = new Genero();

        genero.setIdGenero(resultado.getLong("id_genero"));
        genero.setNome(resultado.getString("nome"));

        return genero;
    }

}
