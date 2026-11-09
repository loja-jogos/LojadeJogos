/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lojajogos.controller;

import br.com.lojajogos.dao.DlcDAO;
import br.com.lojajogos.model.Dlc;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author gabriel
 */
public class DlcController {
    
    private final DlcDAO dao;

    public DlcController() {
        this.dao = new DlcDAO();
    }

    public void cadastrar(Dlc dlc) throws SQLException {
        validar(dlc);
        validarDados(dlc);
        dao.inserir(dlc);
    }

    public List<Dlc> listar() throws SQLException {
        return dao.listarTodos();
    }

    public Dlc buscarPorChave(Long idJogo, Long idDlc) throws SQLException {
        validarChaves(idJogo, idDlc);
        return dao.buscarPorChave(idJogo, idDlc);
    }

    public boolean atualizar(Dlc dlc) throws SQLException {
        validar(dlc);
        validarChaves(dlc.getIdJogo(), dlc.getIdDlc());
        validarDados(dlc);
        return dao.atualizar(dlc);
    }

    public boolean excluir(Long idJogo, Long idDlc) throws SQLException {
        validarChaves(idJogo, idDlc);
        return dao.excluir(idJogo, idDlc);
    }

    private void validar(Dlc dlc) {
        if (dlc == null) {
            throw new IllegalArgumentException("DLC não informada.");
        }
    }

    private void validarDados(Dlc dlc) {
        validarChaves(dlc.getIdJogo(), dlc.getIdDlc());
        if (dlc.getNome() == null || dlc.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome da DLC é obrigatório.");
        }
        if (dlc.getValor() == null || dlc.getValor().signum() <= 0) {
            throw new IllegalArgumentException("O valor da DLC deve ser maior que zero.");
        }
    }

    private void validarChaves(Long idJogo, Long idDlc) {
        validarId(idJogo);
        validarId(idDlc);
    }

    private void validarId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Identificador inválido.");
        }
    }
    
}
