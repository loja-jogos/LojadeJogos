/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lojajogos.controller;

import br.com.lojajogos.dao.FormaPagamentoDAO;
import br.com.lojajogos.model.FormaPagamento;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author gabriel
 */
public class FormaPagamentoController {
    
    private final FormaPagamentoDAO dao;

    public FormaPagamentoController() {
        this.dao = new FormaPagamentoDAO();
    }

    public void cadastrar(FormaPagamento formaPagamento) throws SQLException {
        validar(formaPagamento);
        validarDescricao(formaPagamento);
        dao.inserir(formaPagamento);
    }

    public List<FormaPagamento> listar() throws SQLException {
        return dao.listarTodos();
    }

    public FormaPagamento buscarPorId(Long id) throws SQLException {
        validarId(id);
        return dao.buscarPorId(id);
    }

    public boolean atualizar(FormaPagamento formaPagamento) throws SQLException {
        validar(formaPagamento);
        validarId(formaPagamento.getIdFormaPagamento());
        validarDescricao(formaPagamento);
        return dao.atualizar(formaPagamento);
    }

    public boolean excluir(Long id) throws SQLException {
        validarId(id);
        return dao.excluir(id);
    }

    private void validar(FormaPagamento formaPagamento) {
        if (formaPagamento == null) {
            throw new IllegalArgumentException("Forma de pagamento não informada.");
        }
    }

    private void validarDescricao(FormaPagamento formaPagamento) {
        if (formaPagamento.getDescricao() == null
                || formaPagamento.getDescricao().trim().isEmpty()) {
            throw new IllegalArgumentException("A descrição da forma de pagamento é obrigatória.");
        }
    }

    private void validarId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID da forma de pagamento inválido.");
        }
    }
    
}
