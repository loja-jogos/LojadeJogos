/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lojajogos.controller;

import br.com.lojajogos.dao.VendaDAO;
import br.com.lojajogos.model.Venda;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author gabriel
 */
public class VendaController {
    
    private final VendaDAO dao;

    public VendaController() {
        this.dao = new VendaDAO();
    }

    public void cadastrar(Venda venda) throws SQLException {
        validar(venda);
        validarDados(venda);
        dao.inserir(venda);
    }

    public List<Venda> listar() throws SQLException {
        return dao.listarTodos();
    }

    public Venda buscarPorId(Long id) throws SQLException {
        validarId(id);
        return dao.buscarPorId(id);
    }

    public boolean atualizar(Venda venda) throws SQLException {
        validar(venda);
        validarId(venda.getIdVenda());
        validarDados(venda);
        return dao.atualizar(venda);
    }

    public boolean excluir(Long id) throws SQLException {
        validarId(id);
        return dao.excluir(id);
    }

    private void validar(Venda venda) {
        if (venda == null) {
            throw new IllegalArgumentException("Venda não informada.");
        }
    }

    private void validarDados(Venda venda) {
        validarId(venda.getIdCliente());
        validarId(venda.getIdFuncionario());
        validarId(venda.getIdFormaPagamento());
    }

    private void validarId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Identificador inválido.");
        }
    }
    
}
