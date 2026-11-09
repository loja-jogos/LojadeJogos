/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lojajogos.controller;

import br.com.lojajogos.dao.JogoGeneroDAO;
import br.com.lojajogos.model.JogoGenero;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author gabriel
 */
public class JogoGeneroController {
    
     private final JogoGeneroDAO dao;

    public JogoGeneroController() {
        this.dao = new JogoGeneroDAO();
    }

    public void cadastrar(JogoGenero relacionamento) throws SQLException {
        validar(relacionamento);
        validarChaves(relacionamento.getIdJogo(), relacionamento.getIdGenero());
        if (relacionamento.getGeneroPrincipal() == null) {
            throw new IllegalArgumentException("Informe se o gênero é principal.");
        }
        dao.inserir(relacionamento);
    }

    public List<JogoGenero> listar() throws SQLException {
        return dao.listarTodos();
    }

    public JogoGenero buscarPorID(Long idJogo, Long idGenero) throws SQLException {
        validarChaves(idJogo, idGenero);
        return dao.buscarPorId(idJogo, idGenero);
    }

    public boolean atualizar(JogoGenero relacionamento) throws SQLException {
        validar(relacionamento);
        validarChaves(relacionamento.getIdJogo(), relacionamento.getIdGenero());
        if (relacionamento.getGeneroPrincipal() == null) {
            throw new IllegalArgumentException("Informe se o gênero é principal.");
        }
        return dao.atualizar(relacionamento);
    }

    public boolean excluir(Long idJogo, Long idGenero) throws SQLException {
        validarChaves(idJogo, idGenero);
        return dao.excluir(idJogo, idGenero);
    }

    private void validar(JogoGenero relacionamento) {
        if (relacionamento == null) {
            throw new IllegalArgumentException("Relacionamento jogo-gênero não informado.");
        }
    }

    private void validarChaves(Long idJogo, Long idGenero) {
        validarId(idJogo);
        validarId(idGenero);
    }

    private void validarId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Identificador inválido.");
        }
    }
    
}
