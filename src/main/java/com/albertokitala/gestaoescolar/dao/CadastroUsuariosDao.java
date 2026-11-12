package com.albertokitala.gestaoescolar.dao;

import com.albertokitala.gestaoescolar.model.Usuarios;
import com.albertokitala.gestaoescolar.util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CadastroUsuariosDao {

    public boolean cadastrar(Usuarios usuarios){
        // Usa exatamente sua tabela : usuarios
        String sql = "INSERT INTO usuarios(username, email, senha, perfil) VALUES (?, ?, ?, ?)";
        try(Connection conn = Conexao.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1,usuarios.getUsername());
            stmt.setString(2,usuarios.getEmail());
            stmt.setString(3,usuarios.getSenha());
            stmt.setString(4,usuarios.getPerfil());

            int linhas = stmt.executeUpdate();
            return linhas > 0;
        }catch (SQLException e){
            System.out.println("Erro ao cadastrar usuarios: "+ e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // validação do email

    public boolean emailJaexiste(String email){

        String sql = "SELECT id FROM usuarios WHERE email = ?";
        try(Connection conn = Conexao.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1,email);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // se achou , já existe
        }catch (SQLException e){
            return false;
        }
    }
}
