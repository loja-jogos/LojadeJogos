/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lojajogos.app;

import br.com.lojajogos.config.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author fabio
 */
public class TesteConexao {

    public static void main(String[] args) {

        String sql = "SELECT COUNT(*) FROM jogo";

        try (
            Connection conexao = Conexao.conectar();
            PreparedStatement comando =
                conexao.prepareStatement(sql);
            ResultSet resultado = comando.executeQuery()
        ) {

            resultado.next();

            System.out.println("Conexão realizada com sucesso!");
            System.out.println(
                "Quantidade de jogos cadastrados: "
                + resultado.getInt(1)
            );

        } catch (SQLException e) {

            System.out.println("Erro na conexão com MySQL:");
            System.out.println(e.getMessage());

        }
    }
}