/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lojajogos.dao;

import br.com.lojajogos.config.Conexao;
import br.com.lojajogos.model.ItemVenda;

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
public class ItemVendaDAO {
    
    public void inserir(ItemVenda itemVenda)
            throws SQLException{

        String sql = "INSERT INTO item_venda "
                   + "(id_venda, id_jogo, quantidade, preco_unitario) "
                   + "VALUES (?, ?, ?, ?)";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)){

            stmt.setLong(1, itemVenda.getIdVenda());
            stmt.setLong(2, itemVenda.getIdJogo());
            stmt.setLong(3, itemVenda.getQuantidade());
            stmt.setBigDecimal(4, itemVenda.getPrecoUnitario());

            stmt.executeUpdate();
        }
    }
    
    public List<ItemVenda> listarTodos() throws SQLException{

        List<ItemVenda> itensVenda = new ArrayList<>();

        String sql = "SELECT * FROM item_venda "
                   + "ORDER BY id_venda, id_jogo";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet resultado = stmt.executeQuery()){

            while (resultado.next()){

                ItemVenda itemVenda = mapearItemVenda(resultado);

                itensVenda.add(itemVenda);
            }
        }

        return itensVenda;
    }
    
    public ItemVenda buscarPorChave(Long idVenda, Long idJogo)
            throws SQLException{

        String sql = "SELECT * FROM item_venda "
                   + "WHERE id_venda = ? AND id_jogo = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)){

            stmt.setLong(1, idVenda);
            stmt.setLong(2, idJogo);

            try (ResultSet resultado = stmt.executeQuery()){

                if (resultado.next()){
                    return mapearItemVenda(resultado);
                }
            }
        }

        return null;
    }
    
    public boolean atualizar(ItemVenda itemVenda)
            throws SQLException{

        String sql = "UPDATE item_venda SET "
                   + "quantidade = ?, "
                   + "preco_unitario = ? "
                   + "WHERE id_venda = ? AND id_jogo = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)){

            stmt.setLong(1, itemVenda.getQuantidade());
            stmt.setBigDecimal(2, itemVenda.getPrecoUnitario());
            stmt.setLong(3, itemVenda.getIdVenda());
            stmt.setLong(4, itemVenda.getIdJogo());

            return stmt.executeUpdate() > 0;
        }
    }
    
    public boolean excluir(Long idVenda, Long idJogo)
            throws SQLException{

        String sql = "DELETE FROM item_venda "
                   + "WHERE id_venda = ? AND id_jogo = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)){

            stmt.setLong(1, idVenda);
            stmt.setLong(2, idJogo);

            return stmt.executeUpdate() > 0;
        }
    }
    
    private ItemVenda mapearItemVenda(ResultSet resultado)
            throws SQLException{

        ItemVenda itemVenda = new ItemVenda();

        itemVenda.setIdVenda(resultado.getLong("id_venda"));

        itemVenda.setIdJogo(resultado.getLong("id_jogo"));

        itemVenda.setQuantidade(resultado.getLong("quantidade"));

        itemVenda.setPrecoUnitario(
                resultado.getBigDecimal("preco_unitario"));

        return itemVenda;
    }

    
}
