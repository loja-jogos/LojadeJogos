/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lojajogos.controller;

import br.com.lojajogos.dao.BibliotecaDAO;
import br.com.lojajogos.model.Biblioteca;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author gabriel
 */
public class BibliotecaController {
    
     private final BibliotecaDAO dao;

    public BibliotecaController() {
        this.dao = new BibliotecaDAO();
    }

    public void cadastrar(Biblioteca biblioteca) throws SQLException {
        validar(biblioteca);
        validarChaves(biblioteca.getIdCliente(), biblioteca.getIdJogo());
        dao.inserir(biblioteca);
    }

    public List<Biblioteca> listar() throws SQLException {
        return dao.listarTodos();
    }

    public Biblioteca buscarPorChave(Long idCliente, Long idJogo) throws SQLException {
        validarChaves(idCliente, idJogo);
        return dao.buscarPorChave(idCliente, idJogo);
    }

    public boolean atualizar(Biblioteca biblioteca) throws SQLException {
        validar(biblioteca);
        validarChaves(biblioteca.getIdCliente(), biblioteca.getIdJogo());
        return dao.atualizar(biblioteca);
    }

    public boolean excluir(Long idCliente, Long idJogo) throws SQLException {
        validarChaves(idCliente, idJogo);
        return dao.excluir(idCliente, idJogo);
    }

    private void validar(Biblioteca biblioteca) {

    if (biblioteca == null) {
        throw new IllegalArgumentException(
                "Registro da biblioteca não informado.");
    }

    if (biblioteca.getDataAquisicao() == null) {
        throw new IllegalArgumentException(
                "A data de aquisição é obrigatória.");
    }
}

    private void validarChaves(Long idCliente, Long idJogo) {
        validarId(idCliente);
        validarId(idJogo);
    }

    private void validarId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Identificador inválido.");
        }
    }
    
}
