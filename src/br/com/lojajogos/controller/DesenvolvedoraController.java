/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lojajogos.controller;

import br.com.lojajogos.dao.DesenvolvedoraDAO;
import br.com.lojajogos.model.Desenvolvedora;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author gabriel
 */
public class DesenvolvedoraController {
    
    private final DesenvolvedoraDAO dao;

    public DesenvolvedoraController() {
        this.dao = new DesenvolvedoraDAO();
    }

    public void cadastrar(Desenvolvedora desenvolvedora) throws SQLException {
        validar(desenvolvedora);
        validarTexto(desenvolvedora.getNome(), "O nome da desenvolvedora é obrigatório.");
        validarTexto(desenvolvedora.getPais(), "O país da desenvolvedora é obrigatório.");
        dao.inserir(desenvolvedora);
    }

    public List<Desenvolvedora> listar() throws SQLException {
        return dao.ListarTodos();
    }

    public Desenvolvedora buscarPorId(Long id) throws SQLException {
        validarId(id);
        return dao.buscarPorId(id);
    }

    public boolean atualizar(Desenvolvedora desenvolvedora) throws SQLException {
        validar(desenvolvedora);
        validarId(desenvolvedora.getIdDesenvolvedora());
        validarTexto(desenvolvedora.getNome(), "O nome da desenvolvedora é obrigatório.");
        validarTexto(desenvolvedora.getPais(), "O país da desenvolvedora é obrigatório.");
        return dao.atualizar(desenvolvedora);
    }

    public boolean excluir(Long id) throws SQLException {
        validarId(id);
        return dao.excluir(id);
    }

    private void validar(Desenvolvedora desenvolvedora) {
        if (desenvolvedora == null) {
            throw new IllegalArgumentException("Desenvolvedora não informada.");
        }
    }

    private void validarTexto(String valor, String mensagem) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(mensagem);
        }
    }

    private void validarId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID da desenvolvedora inválido.");
        }
    }
    
}
