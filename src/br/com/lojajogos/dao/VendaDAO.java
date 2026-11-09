/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lojajogos.dao;

import br.com.lojajogos.config.Conexao;
import br.com.lojajogos.model.Venda;
import br.com.lojajogos.model.StatusVenda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fabio
 */
public class VendaDAO {
    
    public void inserir(Venda venda) throws SQLException{

        String sql = "INSERT INTO venda "
                   + "(status, id_cliente, id_funcionario, "
                   + "id_forma_pagamento) "
                   + "VALUES (?, ?, ?, ?)";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)){

            StatusVenda status = venda.getStatus();

            if (status == null){
                status = StatusVenda.PENDENTE;
            }

            stmt.setString(1, status.name());
            stmt.setLong(2, venda.getIdCliente());
            stmt.setLong(3, venda.getIdFuncionario());
            stmt.setLong(4, venda.getIdFormaPagamento());

            stmt.executeUpdate();

            try (ResultSet chaves = stmt.getGeneratedKeys()){

                if (chaves.next()){
                    venda.setIdVenda(chaves.getLong(1));
                    venda.setStatus(status);
                }
            }
        }
    }
    
    public List<Venda> listarTodos() throws SQLException{

        List<Venda> vendas = new ArrayList<>();

        String sql = "SELECT * FROM venda ORDER BY data_hora DESC";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet resultado = stmt.executeQuery()){

            while (resultado.next()){

                Venda venda = mapearVenda(resultado);

                vendas.add(venda);
            }
        }

        return vendas;
    }
    
    public Venda buscarPorId(Long idVenda) throws SQLException{

        String sql = "SELECT * FROM venda WHERE id_venda = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)){

            stmt.setLong(1, idVenda);

            try (ResultSet resultado = stmt.executeQuery()){

                if (resultado.next()){
                    return mapearVenda(resultado);
                }
            }
        }

        return null;
    }
    
    public boolean atualizar(Venda venda) throws SQLException{

        String sql = "UPDATE venda SET "
                   + "data_hora = COALESCE(?, data_hora), "
                   + "status = ?, "
                   + "id_cliente = ?, "
                   + "id_funcionario = ?, "
                   + "id_forma_pagamento = ? "
                   + "WHERE id_venda = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)){

            if (venda.getDataHora() != null){
                stmt.setTimestamp(1,
                        Timestamp.valueOf(venda.getDataHora()));
            } else {
                stmt.setNull(1, Types.TIMESTAMP);
            }

            StatusVenda status = venda.getStatus();

            if (status == null){
                status = StatusVenda.PENDENTE;
            }

            stmt.setString(2, status.name());
            stmt.setLong(3, venda.getIdCliente());
            stmt.setLong(4, venda.getIdFuncionario());
            stmt.setLong(5, venda.getIdFormaPagamento());
            stmt.setLong(6, venda.getIdVenda());

            return stmt.executeUpdate() > 0;
        }
    }
    
    public boolean excluir(Long idVenda) throws SQLException{

        String sql = "DELETE FROM venda WHERE id_venda = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)){

            stmt.setLong(1, idVenda);

            return stmt.executeUpdate() > 0;
        }
    }
    
    private Venda mapearVenda(ResultSet resultado)
            throws SQLException{

        Venda venda = new Venda();

        venda.setIdVenda(resultado.getLong("id_venda"));

        Timestamp data = resultado.getTimestamp("data_hora");

        if (data != null) {
            venda.setDataHora(data.toLocalDateTime());
        }

        venda.setStatus(
                StatusVenda.valueOf(resultado.getString("status")));

        venda.setIdCliente(resultado.getLong("id_cliente"));

        venda.setIdFuncionario(
                resultado.getLong("id_funcionario"));

        venda.setIdFormaPagamento(
                resultado.getLong("id_forma_pagamento"));

        return venda;
    }
    
}
