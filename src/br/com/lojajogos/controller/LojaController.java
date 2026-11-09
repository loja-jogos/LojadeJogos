/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lojajogos.controller;

import br.com.lojajogos.dao.LojaDAO;
import br.com.lojajogos.model.Loja;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author gabriel
 */
public class LojaController {
    
    private final LojaDAO dao;

    public LojaController() {
        this.dao = new LojaDAO();
    }

    public void cadastrar(Loja loja) throws SQLException {
        validar(loja);
        validarDados(loja);
        dao.inserir(loja);
    }

    public List<Loja> listar() throws SQLException {
        return dao.listarTodos();
    }

    public Loja buscarPorId(Long id) throws SQLException {
        validarId(id);
        return dao.buscarPorId(id);
    }

    public boolean atualizar(Loja loja) throws SQLException {
        validar(loja);
        validarId(loja.getIdLoja());
        validarDados(loja);
        return dao.atualizar(loja);
    }

    public boolean excluir(Long id) throws SQLException {
        validarId(id);
        return dao.excluir(id);
    }

    private void validar(Loja loja) {
        if (loja == null) {
            throw new IllegalArgumentException("Loja não informada.");
        }
    }

    private void validarDados(Loja loja) {
        validarTexto(loja.getNomeLoja(), "O nome da loja é obrigatório.");
        validarTexto(loja.getTelefone(), "O telefone da loja é obrigatório.");
        validarTexto(loja.getEndereco(), "O endereço da loja é obrigatório.");
    }

    private void validarTexto(String valor, String mensagem) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(mensagem);
        }
    }

    private void validarId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID da loja inválido.");
        }
    }
    
}
