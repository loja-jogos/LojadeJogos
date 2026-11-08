/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lojajogos.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author fabio
 */
public class Conexao {

    private static final String URL =
        "jdbc:mysql://localhost:3306/loja_jogos?serverTimezone=America/Sao_Paulo";

    private static final String USUARIO = "root";
    private static final String SENHA = "40028922";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(
            URL,
            USUARIO,
            SENHA
        );
    }
}