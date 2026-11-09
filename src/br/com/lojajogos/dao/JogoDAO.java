/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lojajogos.dao;

import br.com.lojajogos.config.Conexao;
import br.com.lojajogos.model.Jogo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.Date;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fabio
 */
public class JogoDAO {
    
    public void inserir(Jogo jogo) throws SQLException{

        String sql = "INSERT INTO jogo "
                   + "(nome, preco, id_desenvolvedora, data_lancamento) "
                   + "VALUES (?, ?, ?, ?)";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)){

            stmt.setString(1, jogo.getNome());
            stmt.setBigDecimal(2, jogo.getPreco());
            stmt.setLong(3, jogo.getIdDesenvolvedora());
            stmt.setDate(4, Date.valueOf(jogo.getDataLancamento()));

            stmt.executeUpdate();

            try (ResultSet chaves = stmt.getGeneratedKeys()){

                if (chaves.next()){
                    jogo.setIdJogo(chaves.getLong(1));
                }
            }
        }
    }
    
    public List<Jogo> listarTodos() throws SQLException{

        List<Jogo> jogos = new ArrayList<>();

        String sql = "SELECT * FROM jogo ORDER BY nome";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet resultado = stmt.executeQuery()){

            while (resultado.next()){

                Jogo jogo = mapearJogo(resultado);

                jogos.add(jogo);
            }
        }

        return jogos;
    }
     
    public Jogo buscarPorId(Long idJogo) throws SQLException{

        String sql = "SELECT * FROM jogo WHERE id_jogo = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)){

            stmt.setLong(1, idJogo);

            try (ResultSet resultado = stmt.executeQuery()){

                if (resultado.next()){
                    return mapearJogo(resultado);
                }
            }
        }

        return null;
    }
     
    public boolean atualizar(Jogo jogo) throws SQLException{

        String sql = "UPDATE jogo SET "
                   + "nome = ?, "
                   + "preco = ?, "
                   + "id_desenvolvedora = ?, "
                   + "data_lancamento = ? "
                   + "WHERE id_jogo = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)){

            stmt.setString(1, jogo.getNome());
            stmt.setBigDecimal(2, jogo.getPreco());
            stmt.setLong(3, jogo.getIdDesenvolvedora());
            stmt.setDate(4, Date.valueOf(jogo.getDataLancamento()));
            stmt.setLong(5, jogo.getIdJogo());

            return stmt.executeUpdate() > 0;
        }
    }
     
    public boolean excluir(Long idJogo) throws SQLException{

        String sql = "DELETE FROM jogo WHERE id_jogo = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)){

            stmt.setLong(1, idJogo);

            return stmt.executeUpdate() > 0;
        }
    }
      
    private Jogo mapearJogo(ResultSet resultado)
            throws SQLException{

        Jogo jogo = new Jogo();

        jogo.setIdJogo(resultado.getLong("id_jogo"));
        jogo.setNome(resultado.getString("nome"));
        jogo.setPreco(resultado.getBigDecimal("preco"));
        jogo.setIdDesenvolvedora(
                resultado.getLong("id_desenvolvedora"));

        Date data = resultado.getDate("data_lancamento");

        if (data != null){
            jogo.setDataLancamento(data.toLocalDate());
        }

        return jogo;
    }
    
}
