/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lojajogos.dao;

import br.com.lojajogos.config.Conexao;
import br.com.lojajogos.model.Funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Types;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fabio
 */
public class FuncionarioDAO {
    
    public void inserir(Funcionario funcionario)
            throws SQLException{

        String sql = "INSERT INTO funcionario "
                   + "(id_supervisor, id_loja, nome, cpf, telefone, data_contratacao) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)){

            if (funcionario.getIdSupervisor() == null){
                stmt.setNull(1, Types.INTEGER);
            } else {
                stmt.setLong(1, funcionario.getIdSupervisor());
            }

            stmt.setLong(2, funcionario.getIdLoja());
            stmt.setString(3, funcionario.getNome());
            stmt.setString(4, funcionario.getCpf());
            stmt.setString(5, funcionario.getTelefone());
            stmt.setDate(6, Date.valueOf(
                    funcionario.getDataContratacao()));

            stmt.executeUpdate();

            try (ResultSet chaves = stmt.getGeneratedKeys()){

                if (chaves.next()){
                    funcionario.setIdFuncionario(chaves.getLong(1));
                }
            }
        }
    }
    
    public List<Funcionario> listarTodos() throws SQLException{

        List<Funcionario> funcionarios = new ArrayList<>();

        String sql = "SELECT * FROM funcionario ORDER BY nome";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet resultado = stmt.executeQuery()){

            while (resultado.next()){

                Funcionario funcionario = mapearFuncionario(resultado);

                funcionarios.add(funcionario);
            }
        }

        return funcionarios;
    }
    
    public Funcionario buscarPorId(Long idFuncionario)
            throws SQLException{

        String sql = "SELECT * FROM funcionario "
                   + "WHERE id_funcionario = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)){

            stmt.setLong(1, idFuncionario);

            try (ResultSet resultado = stmt.executeQuery()){

                if (resultado.next()){
                    return mapearFuncionario(resultado);
                }
            }
        }

        return null;
    }

    public boolean atualizar(Funcionario funcionario)
            throws SQLException{

        String sql = "UPDATE funcionario SET "
                   + "id_supervisor = ?, "
                   + "id_loja = ?, "
                   + "nome = ?, "
                   + "cpf = ?, "
                   + "telefone = ?, "
                   + "data_contratacao = ? "
                   + "WHERE id_funcionario = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)){

            if (funcionario.getIdSupervisor() == null){
                stmt.setNull(1, Types.INTEGER);
            } else {
                stmt.setLong(1, funcionario.getIdSupervisor());
            }

            stmt.setLong(2, funcionario.getIdLoja());
            stmt.setString(3, funcionario.getNome());
            stmt.setString(4, funcionario.getCpf());
            stmt.setString(5, funcionario.getTelefone());
            stmt.setDate(6, Date.valueOf(
                    funcionario.getDataContratacao()));

            stmt.setLong(7, funcionario.getIdFuncionario());

            return stmt.executeUpdate() > 0;
        }
    }
    
    public boolean excluir(Long idFuncionario)
            throws SQLException{

        String sql = "DELETE FROM funcionario "
                   + "WHERE id_funcionario = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)){

            stmt.setLong(1, idFuncionario);

            return stmt.executeUpdate() > 0;
        }
    }
    
    private Funcionario mapearFuncionario(ResultSet resultado)
            throws SQLException{

        Funcionario funcionario = new Funcionario();

        funcionario.setIdFuncionario(
                resultado.getLong("id_funcionario"));

        long idSupervisor = resultado.getLong("id_supervisor");

        if (resultado.wasNull()){
            funcionario.setIdSupervisor(null);
        } else {
            funcionario.setIdSupervisor(idSupervisor);
        }

        funcionario.setIdLoja(
                resultado.getLong("id_loja"));

        funcionario.setNome(
                resultado.getString("nome"));

        funcionario.setCpf(
                resultado.getString("cpf"));

        funcionario.setTelefone(
                resultado.getString("telefone"));

        Date data = resultado.getDate("data_contratacao");

        if (data != null){
            funcionario.setDataContratacao(data.toLocalDate());
        }

        return funcionario;
    }
   
}
