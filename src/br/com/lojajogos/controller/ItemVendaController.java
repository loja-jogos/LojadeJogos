/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lojajogos.controller;

import br.com.lojajogos.dao.ItemVendaDAO;
import br.com.lojajogos.model.ItemVenda;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author gabriel
 */
public class ItemVendaController {
    
    private final ItemVendaDAO dao;

    public ItemVendaController() {
        this.dao = new ItemVendaDAO();
    }

    public void cadastrar(ItemVenda item) throws SQLException {
        validar(item);
        validarDados(item);
        dao.inserir(item);
    }

    public List<ItemVenda> listar() throws SQLException {
        return dao.listarTodos();
    }

    public ItemVenda buscarPorChave(Long idVenda, Long idJogo) throws SQLException {
        validarChaves(idVenda, idJogo);
        return dao.buscarPorChave(idVenda, idJogo);
    }

    public boolean atualizar(ItemVenda item) throws SQLException {
        validar(item);
        validarChaves(item.getIdVenda(), item.getIdJogo());
        validarDados(item);
        return dao.atualizar(item);
    }

    public boolean excluir(Long idVenda, Long idJogo) throws SQLException {
        validarChaves(idVenda, idJogo);
        return dao.excluir(idVenda, idJogo);
    }

    private void validar(ItemVenda item) {
        if (item == null) {
            throw new IllegalArgumentException("Item da venda não informado.");
        }
    }

    private void validarDados(ItemVenda item) {
        validarChaves(item.getIdVenda(), item.getIdJogo());
        if (item.getQuantidade() == null || item.getQuantidade() <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }
        if (item.getPrecoUnitario() == null || item.getPrecoUnitario().signum() <= 0) {
            throw new IllegalArgumentException("O preço unitário não pode ser nulo ou negativo.");
        }
    }

    private void validarChaves(Long idVenda, Long idJogo) {
        validarId(idVenda);
        validarId(idJogo);
    }

    private void validarId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Identificador inválido.");
        }
    }
    
}
