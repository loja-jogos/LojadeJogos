/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lojajogos.controller;

import br.com.lojajogos.dao.ClienteDAO;
import br.com.lojajogos.model.Cliente;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author gabriel
 */
public class ClienteController {
    
    private final ClienteDAO dao;

    public ClienteController() {
        this.dao = new ClienteDAO();
    }

    public void cadastrar(Cliente cliente) throws SQLException {
        validar(cliente);
        validarDados(cliente);
        dao.inserir(cliente);
    }

    public List<Cliente> listar() throws SQLException {
        return dao.ListarTodos();
    }

    public Cliente buscarPorId(Long id) throws SQLException {
        validarId(id);
        return dao.buscarPorId(id);
    }

    public boolean atualizar(Cliente cliente) throws SQLException {
        validar(cliente);
        validarId(cliente.getIdCliente());
        validarDados(cliente);
        return dao.atualizar(cliente);
    }

    public boolean excluir(Long id) throws SQLException {
        validarId(id);
        return dao.excluir(id);
    }

    private void validar(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não informado.");
        }
    }

    private void validarDados(Cliente cliente) {
        validarTexto(cliente.getNome(), "O nome do cliente é obrigatório.");
        validarTexto(cliente.getCpf(), "O CPF do cliente é obrigatório.");
        validarTexto(cliente.getTelefone(), "O telefone do cliente é obrigatório.");
        validarTexto(cliente.getEmail(), "O email do cliente é obrigatório.");
    }

    private void validarTexto(String valor, String mensagem) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(mensagem);
        }
    }

    private void validarId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID do cliente inválido.");
        }
    }
    
}
