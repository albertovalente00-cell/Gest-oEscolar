package com.albertokitala.gestaoescolar.model;

public class Usuarios {


    private int id;
    private String username;
    private String email;
    private String senha;
    private String perfil;

    // Construtor completo para o cadastro

    public Usuarios() {
    }

    public Usuarios(String username, String email, String senha, String perfil) {
        this.username = username;
        this.email = email;
        this.senha = senha;
        this.perfil = perfil;
    }

    // Construtor completo para o cadastro

    public Usuarios(int id, String username, String email, String senha, String perfil) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.senha = senha;
        this.perfil = perfil;
    }

    // Metodo Getters e Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    // Metodo toString

    @Override
    public String toString() {
        return "Usuarios{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", perfil='" + perfil + '\'' +
                '}';
    }
}
