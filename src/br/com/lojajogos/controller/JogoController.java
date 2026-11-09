/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lojajogos.controller;

import br.com.lojajogos.dao.JogoDAO;
import br.com.lojajogos.model.Jogo;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author gabriel
 */
public class JogoController {
    
    private final JogoDAO dao;

    public JogoController() {
        this.dao = new JogoDAO();
    }

    public void cadastrar(Jogo jogo) throws SQLException {
        validar(jogo);
        validarDados(jogo);
        dao.inserir(jogo);
    }

    public List<Jogo> listar() throws SQLException {
        return dao.listarTodos();
    }

    public Jogo buscarPorId(Long id) throws SQLException {
        validarId(id);
        return dao.buscarPorId(id);
    }

    public boolean atualizar(Jogo jogo) throws SQLException {
        validar(jogo);
        validarId(jogo.getIdJogo());
        validarDados(jogo);
        return dao.atualizar(jogo);
    }

    public boolean excluir(Long id) throws SQLException {
        validarId(id);
        return dao.excluir(id);
    }

    private void validar(Jogo jogo) {
        if (jogo == null) {
            throw new IllegalArgumentException("Jogo não informado.");
        }
    }

    private void validarDados(Jogo jogo) {

    validarTexto(jogo.getNome(),
            "O nome do jogo é obrigatório.");

    validarId(jogo.getIdDesenvolvedora());

    if (jogo.getPreco() == null
            || jogo.getPreco().signum() <= 0) {
        throw new IllegalArgumentException(
                "O preço do jogo deve ser maior que zero.");
    }

    if (jogo.getDataLancamento() == null) {
        throw new IllegalArgumentException(
                "A data de lançamento é obrigatória.");
    }
}

    private void validarTexto(String valor, String mensagem) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(mensagem);
        }
    }

    private void validarId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Identificador inválido.");
        }
    }
    
}
