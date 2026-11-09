/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lojajogos.dao;

import br.com.lojajogos.config.Conexao;
import br.com.lojajogos.model.FormaPagamento;

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
public class FormaPagamentoDAO {
    
    public void inserir(FormaPagamento formaPagamento)
            throws SQLException{

        String sql = "INSERT INTO forma_pagamento "
                   + "(descricao) VALUES (?)";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)){

            stmt.setString(1, formaPagamento.getDescricao());

            stmt.executeUpdate();

            try (ResultSet chaves = stmt.getGeneratedKeys()){

                if (chaves.next()){
                    formaPagamento.setIdFormaPagamento(
                            chaves.getLong(1));
                }
            }
        }
    }
    
    public List<FormaPagamento> listarTodos()
            throws SQLException{

        List<FormaPagamento> formasPagamento =
                new ArrayList<>();

        String sql = "SELECT * FROM forma_pagamento "
                   + "ORDER BY descricao";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet resultado = stmt.executeQuery()){

            while (resultado.next()){

                FormaPagamento formaPagamento =
                        mapearFormaPagamento(resultado);

                formasPagamento.add(formaPagamento);
            }
        }

        return formasPagamento;
    }
    
    public FormaPagamento buscarPorId(Long idFormaPagamento)
            throws SQLException{

        String sql = "SELECT * FROM forma_pagamento "
                   + "WHERE id_forma_pagamento = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)){

            stmt.setLong(1, idFormaPagamento);

            try (ResultSet resultado = stmt.executeQuery()){

                if (resultado.next()){
                    return mapearFormaPagamento(resultado);
                }
            }
        }

        return null;
    }
    
    public boolean atualizar(FormaPagamento formaPagamento)
            throws SQLException{

        String sql = "UPDATE forma_pagamento SET "
                   + "descricao = ? "
                   + "WHERE id_forma_pagamento = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)){

            stmt.setString(1, formaPagamento.getDescricao());

            stmt.setLong(2, formaPagamento.getIdFormaPagamento());

            return stmt.executeUpdate() > 0;
        }
    }
     
    public boolean excluir(Long idFormaPagamento)
            throws SQLException{

        String sql = "DELETE FROM forma_pagamento "
                   + "WHERE id_forma_pagamento = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)){

            stmt.setLong(1, idFormaPagamento);

            return stmt.executeUpdate() > 0;
        }
    }
     
    private FormaPagamento mapearFormaPagamento(
            ResultSet resultado) throws SQLException{

        FormaPagamento formaPagamento = new FormaPagamento();

        formaPagamento.setIdFormaPagamento(
                resultado.getLong("id_forma_pagamento"));

        formaPagamento.setDescricao(
                resultado.getString("descricao"));

        return formaPagamento;
    }
    
}
