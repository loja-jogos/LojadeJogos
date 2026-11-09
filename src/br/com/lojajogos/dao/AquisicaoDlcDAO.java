/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lojajogos.dao;

import br.com.lojajogos.config.Conexao;
import br.com.lojajogos.model.AquisicaoDlc;

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
public class AquisicaoDlcDAO {
    
    public void inserir(AquisicaoDlc aquisicao)
            throws SQLException{

        String sql = "INSERT INTO aquisicao_dlc "
                   + "(id_cliente, id_jogo, id_dlc, data_aquisicao) "
                   + "VALUES (?, ?, ?, ?)";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)){

            stmt.setLong(1, aquisicao.getIdCliente());
            stmt.setLong(2, aquisicao.getIdJogo());
            stmt.setLong(3, aquisicao.getIdDlc());

            stmt.setTimestamp(4,
                    Timestamp.valueOf(aquisicao.getDataAquisicao()));

            stmt.executeUpdate();
        }
    }
    
    public List<AquisicaoDlc> listarTodos()
            throws SQLException{

        List<AquisicaoDlc> aquisicoes = new ArrayList<>();

        String sql = "SELECT * FROM aquisicao_dlc "
                   + "ORDER BY id_cliente, id_jogo, id_dlc";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet resultado = stmt.executeQuery()){

            while (resultado.next()){

                AquisicaoDlc aquisicao =
                        mapearAquisicaoDlc(resultado);

                aquisicoes.add(aquisicao);
            }
        }

        return aquisicoes;
    }
    
    public AquisicaoDlc buscarPorChave(
            Long idCliente, Long idJogo, Long idDlc)
            throws SQLException{

        String sql = "SELECT * FROM aquisicao_dlc "
                   + "WHERE id_cliente = ? "
                   + "AND id_jogo = ? "
                   + "AND id_dlc = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)){

            stmt.setLong(1, idCliente);
            stmt.setLong(2, idJogo);
            stmt.setLong(3, idDlc);

            try (ResultSet resultado = stmt.executeQuery()){

                if (resultado.next()){
                    return mapearAquisicaoDlc(resultado);
                }
            }
        }

        return null;
    }
    
    public boolean atualizar(AquisicaoDlc aquisicao)
            throws SQLException{

        String sql = "UPDATE aquisicao_dlc SET "
                   + "data_aquisicao = ? "
                   + "WHERE id_cliente = ? "
                   + "AND id_jogo = ? "
                   + "AND id_dlc = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)){

            stmt.setTimestamp(1,
                    Timestamp.valueOf(aquisicao.getDataAquisicao()));

            stmt.setLong(2, aquisicao.getIdCliente());
            stmt.setLong(3, aquisicao.getIdJogo());
            stmt.setLong(4, aquisicao.getIdDlc());

            return stmt.executeUpdate() > 0;
        }
    }
     
    public boolean excluir(
            Long idCliente, Long idJogo, Long idDlc)
            throws SQLException{

        String sql = "DELETE FROM aquisicao_dlc "
                   + "WHERE id_cliente = ? "
                   + "AND id_jogo = ? "
                   + "AND id_dlc = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)){

            stmt.setLong(1, idCliente);
            stmt.setLong(2, idJogo);
            stmt.setLong(3, idDlc);

            return stmt.executeUpdate() > 0;
        }
    }
    
    private AquisicaoDlc mapearAquisicaoDlc(
            ResultSet resultado) throws SQLException{

        AquisicaoDlc aquisicao = new AquisicaoDlc();

        aquisicao.setIdCliente(resultado.getLong("id_cliente"));

        aquisicao.setIdJogo(resultado.getLong("id_jogo"));

        aquisicao.setIdDlc(resultado.getLong("id_dlc"));

        Timestamp data = resultado.getTimestamp("data_aquisicao");

        if (data != null){
            aquisicao.setDataAquisicao(data.toLocalDateTime());
        }

        return aquisicao;
    }
    
}
