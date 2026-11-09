/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lojajogos.controller;

import br.com.lojajogos.dao.GeneroDAO;
import br.com.lojajogos.model.Genero;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author gabriel
 */
public class GeneroController {
    
    private final GeneroDAO dao;

    public GeneroController() {
        this.dao = new GeneroDAO();
    }

    public void cadastrar(Genero genero) throws SQLException {
        validar(genero);
        validarNome(genero);
        dao.inserir(genero);
    }

    public List<Genero> listar() throws SQLException {
        return dao.listarTodos();
    }

    public Genero buscarPorId(Long id) throws SQLException {
        validarId(id);
        return dao.buscarPorId(id);
    }

    public boolean atualizar(Genero genero) throws SQLException {
        validar(genero);
        validarId(genero.getIdGenero());
        validarNome(genero);
        return dao.atualizar(genero);
    }

    public boolean excluir(Long id) throws SQLException {
        validarId(id);
        return dao.excluir(id);
    }

    private void validar(Genero genero) {
        if (genero == null) {
            throw new IllegalArgumentException("Gênero não informado.");
        }
    }

    private void validarNome(Genero genero) {
        if (genero.getNome() == null || genero.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do gênero é obrigatório.");
        }
    }

    private void validarId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID do gênero inválido.");
        }
    }
    
}
