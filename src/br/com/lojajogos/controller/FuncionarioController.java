/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lojajogos.controller;

import br.com.lojajogos.dao.FuncionarioDAO;
import br.com.lojajogos.model.Funcionario;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author gabriel
 */
public class FuncionarioController {
    
     private final FuncionarioDAO dao;

    public FuncionarioController() {
        this.dao = new FuncionarioDAO();
    }

    public void cadastrar(Funcionario funcionario) throws SQLException {
        validar(funcionario);
        validarDados(funcionario);
        dao.inserir(funcionario);
    }

    public List<Funcionario> listar() throws SQLException {
        return dao.listarTodos();
    }

    public Funcionario buscarPorId(Long id) throws SQLException {
        validarId(id);
        return dao.buscarPorId(id);
    }

    public boolean atualizar(Funcionario funcionario) throws SQLException {
        validar(funcionario);
        validarId(funcionario.getIdFuncionario());
        validarDados(funcionario);
        return dao.atualizar(funcionario);
    }

    public boolean excluir(Long id) throws SQLException {
        validarId(id);
        return dao.excluir(id);
    }

    private void validar(Funcionario funcionario) {
        if (funcionario == null) {
            throw new IllegalArgumentException("Funcionário não informado.");
        }
    }

    private void validarDados(Funcionario funcionario) {

    validarTexto(funcionario.getNome(),
            "O nome do funcionário é obrigatório.");

    validarTexto(funcionario.getCpf(),
            "O CPF do funcionário é obrigatório.");

    if (!funcionario.getCpf().matches("[0-9]{11}")) {
        throw new IllegalArgumentException(
                "O CPF deve possuir exatamente 11 números.");
    }

    validarId(funcionario.getIdLoja());

    if (funcionario.getIdSupervisor() != null) {
        validarId(funcionario.getIdSupervisor());
    }

    if (funcionario.getDataContratacao() == null) {
        throw new IllegalArgumentException(
                "A data de contratação é obrigatória.");
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
