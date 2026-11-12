package com.albertokitala.gestaoescolar.dao;

import com.albertokitala.gestaoescolar.util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {

    public boolean autenticar(String username, String senha){
        String sql = "SELECT * FROM usuarios WHERE username =? AND senha = ?";

        try(Connection conn = Conexao.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1,username);
            stmt.setString(2,senha);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                return  true; // Achou o usuario
            }
        }catch (SQLException e){
            System.out.println("Erro no SQL: " +e.getMessage());

            //vai aparecer no teu console do intellij
        }return  false;
    }
}