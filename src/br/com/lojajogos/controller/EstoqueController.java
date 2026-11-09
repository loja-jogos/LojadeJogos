/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lojajogos.controller;

import br.com.lojajogos.dao.EstoqueDAO;
import br.com.lojajogos.model.Estoque;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author gabriel
 */
public class EstoqueController {
    
    private final EstoqueDAO dao;

    public EstoqueController() {
        this.dao = new EstoqueDAO();
    }

    public void cadastrar(Estoque estoque) throws SQLException {
        validar(estoque);
        validarId(estoque.getIdJogo());
        validarQuantidade(estoque.getQuantidadeDisponivel());
        dao.inserir(estoque);
    }

    public List<Estoque> listar() throws SQLException {
        return dao.listarTodos();
    }

    public Estoque buscarPorId(Long id) throws SQLException {
        validarId(id);
        return dao.buscarPorId(id);
    }

    public boolean atualizar(Estoque estoque) throws SQLException {
        validar(estoque);
        validarId(estoque.getIdEstoque());
        validarId(estoque.getIdJogo());
        validarQuantidade(estoque.getQuantidadeDisponivel());
        return dao.atualizar(estoque);
    }

    public boolean excluir(Long id) throws SQLException {
        validarId(id);
        return dao.excluir(id);
    }

    private void validar(Estoque estoque) {
        if (estoque == null) {
            throw new IllegalArgumentException("Estoque não informado.");
        }
    }

    private void validarQuantidade(Long quantidade) {
        if (quantidade == null || quantidade < 0) {
            throw new IllegalArgumentException("A quantidade disponível não pode ser nula ou negativa.");
        }
    }

    private void validarId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Identificador inválido.");
        }
    }
    
}
