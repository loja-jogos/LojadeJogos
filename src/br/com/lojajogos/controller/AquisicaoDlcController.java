/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lojajogos.controller;

import br.com.lojajogos.dao.AquisicaoDlcDAO;
import br.com.lojajogos.model.AquisicaoDlc;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author gabriel
 */
public class AquisicaoDlcController {
    
    private final AquisicaoDlcDAO dao;

    public AquisicaoDlcController() {
        this.dao = new AquisicaoDlcDAO();
    }

    public void cadastrar(AquisicaoDlc aquisicao) throws SQLException {
        validar(aquisicao);
        validarChaves(aquisicao.getIdCliente(), aquisicao.getIdJogo(), aquisicao.getIdDlc());
        dao.inserir(aquisicao);
    }

    public List<AquisicaoDlc> listar() throws SQLException {
        return dao.listarTodos();
    }

    public AquisicaoDlc buscarPorChave(Long idCliente, Long idJogo, Long idDlc)
            throws SQLException {
        validarChaves(idCliente, idJogo, idDlc);
        return dao.buscarPorChave(idCliente, idJogo, idDlc);
    }

    public boolean atualizar(AquisicaoDlc aquisicao) throws SQLException {
        validar(aquisicao);
        validarChaves(aquisicao.getIdCliente(), aquisicao.getIdJogo(), aquisicao.getIdDlc());
        return dao.atualizar(aquisicao);
    }

    public boolean excluir(Long idCliente, Long idJogo, Long idDlc)
            throws SQLException {
        validarChaves(idCliente, idJogo, idDlc);
        return dao.excluir(idCliente, idJogo, idDlc);
    }

    private void validar(AquisicaoDlc aquisicao) {
        if (aquisicao == null) {
            throw new IllegalArgumentException("Aquisição de DLC não informada.");
        }
        if (aquisicao.getDataAquisicao() == null) {
            throw new IllegalArgumentException("A data de aquisição da DLC é obrigatória.");
        }
    }

    private void validarChaves(Long idCliente, Long idJogo, Long idDlc) {
        validarId(idCliente);
        validarId(idJogo);
        validarId(idDlc);
    }

    private void validarId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Identificador inválido.");
        }
    }
    
}
