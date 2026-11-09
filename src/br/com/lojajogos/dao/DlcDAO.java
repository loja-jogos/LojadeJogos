/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lojajogos.dao;

import br.com.lojajogos.config.Conexao;
import br.com.lojajogos.model.Dlc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Types;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fabio
 */
public class DlcDAO {
    
    public void inserir(Dlc dlc) throws SQLException{

        String sql = "INSERT INTO dlc "
                   + "(id_jogo, id_dlc, nome, conteudo, "
                   + "data_de_aquisicao, valor) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)){

            stmt.setLong(1, dlc.getIdJogo());
            stmt.setLong(2, dlc.getIdDlc());
            stmt.setString(3, dlc.getNome());
            stmt.setString(4, dlc.getConteudo());

            if (dlc.getDataDeAquisicao() != null){
                stmt.setDate(5, Date.valueOf(
                        dlc.getDataDeAquisicao()));
            } else {
                stmt.setNull(5, Types.DATE);
            }

            stmt.setBigDecimal(6, dlc.getValor());

            stmt.executeUpdate();
        }
    }
    
    public List<Dlc> listarTodos() throws SQLException{

        List<Dlc> dlcs = new ArrayList<>();

        String sql = "SELECT * FROM dlc "
                   + "ORDER BY id_jogo, id_dlc";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet resultado = stmt.executeQuery()) {

            while (resultado.next()){

                Dlc dlc = mapearDlc(resultado);

                dlcs.add(dlc);
            }
        }

        return dlcs;
    }
    
    public Dlc buscarPorChave(Long idJogo, Long idDlc)
            throws SQLException{

        String sql = "SELECT * FROM dlc "
                   + "WHERE id_jogo = ? AND id_dlc = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)){

            stmt.setLong(1, idJogo);
            stmt.setLong(2, idDlc);

            try (ResultSet resultado = stmt.executeQuery()){

                if (resultado.next()){
                    return mapearDlc(resultado);
                }
            }
        }

        return null;
    }
    
    public boolean atualizar(Dlc dlc) throws SQLException{

        String sql = "UPDATE dlc SET "
                   + "nome = ?, "
                   + "conteudo = ?, "
                   + "data_de_aquisicao = ?, "
                   + "valor = ? "
                   + "WHERE id_jogo = ? AND id_dlc = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)){

            stmt.setString(1, dlc.getNome());
            stmt.setString(2, dlc.getConteudo());

            if (dlc.getDataDeAquisicao() != null){
                stmt.setDate(3, Date.valueOf(
                        dlc.getDataDeAquisicao()));
            } else {
                stmt.setNull(3, Types.DATE);
            }

            stmt.setBigDecimal(4, dlc.getValor());
            stmt.setLong(5, dlc.getIdJogo());
            stmt.setLong(6, dlc.getIdDlc());

            return stmt.executeUpdate() > 0;
        }
    }

    public boolean excluir(Long idJogo, Long idDlc)
            throws SQLException{

        String sql = "DELETE FROM dlc "
                   + "WHERE id_jogo = ? AND id_dlc = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)){

            stmt.setLong(1, idJogo);
            stmt.setLong(2, idDlc);

            return stmt.executeUpdate() > 0;
        }
    }
    
    private Dlc mapearDlc(ResultSet resultado)
            throws SQLException{

        Dlc dlc = new Dlc();

        dlc.setIdJogo(resultado.getLong("id_jogo"));
        dlc.setIdDlc(resultado.getLong("id_dlc"));
        dlc.setNome(resultado.getString("nome"));
        dlc.setConteudo(resultado.getString("conteudo"));

        Date data = resultado.getDate("data_de_aquisicao");

        if (data != null){
            dlc.setDataDeAquisicao(data.toLocalDate());
        }

        dlc.setValor(resultado.getBigDecimal("valor"));

        return dlc;
    }
    
}
