package com.albertokitala.gestaoescolar.controller;

import com.albertokitala.gestaoescolar.util.Conexao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML private Button btnLogin;
    @FXML private Button close;
    @FXML private Button minimize;
    @FXML private TextField txtNomeUtilizador;
    @FXML private PasswordField txtSenhaUtilizador;
    @FXML private Label lblMensagem;

    // Metodo para  fechar a tela de Login
    @FXML
    public  void exit(){
        System.exit(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblMensagem.setText("");
        lblMensagem.setVisible(false);

    }
    // Metodo para minimizar a tela de Login
    @FXML
    public void OnActionMinimize() {
        Stage stage = (Stage)minimize.getScene().getWindow();
        stage.setIconified(true);
    }

    // Metodo para activar o botão do Login
    @FXML
    public void onActionLoginClick(ActionEvent event) {
        String usuario = txtNomeUtilizador.getText().trim();
        String senha = txtSenhaUtilizador.getText().trim();

        if(usuario.isEmpty() || senha.isEmpty()){
            mostrarErro("Preencha todos os campos!");
            return;
        }

        String sql = "SELECT * FROM usuarios WHERE username = ?  AND senha = ?";

        try(Connection conn = Conexao.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            if(conn == null){
                mostrarErro("Falha na conexao com Banco de dado|");
                return;
            }

            stmt.setString(1, usuario);
            stmt.setString(2,senha);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                mostrarSucesso("Login OK! Bem-vindo "+usuario);

                  //Abre o Menu Principal
                abrirMenuPrincipal();
            }else {
                mostrarErro("utilizador ou senha errados!");
            }

        } catch (Exception e) {
            mostrarErro("Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Metodo para mostrar erro

    private void mostrarErro(String msg){
        lblMensagem.getStyleClass().setAll("label-mensagem");
        lblMensagem.setText(msg);
        lblMensagem.setVisible(true);
    }

    //Metodo para mostrar a mensagem de acesso ao sistema Login

    private void mostrarSucesso(String msg){
        lblMensagem.getStyleClass().setAll("label-mensagem-sucesso");
        lblMensagem.setText(msg);
        lblMensagem.setVisible(true);
    }

    public void onClickIrParaCadastro() {
        try{

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/albertokitala/gestaoescolar/CadastraUsuario.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Criar Conta");
            stage.setScene(new Scene(root));
            stage.show();;


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao abrir  cadastro : "+ e.getMessage());
        }
    }

    // Metodo para abrir o menu principal
    private void abrirMenuPrincipal(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/albertokitala/gestaoescolar/MenuPrincipal.fxml"));
            Parent root =loader.load();

            Stage stage = new Stage();
            stage.setTitle("Gestão Escolar - Menu Principal");
            stage.setScene(new Scene(root));
            stage.setMaximized(true);
            stage.show();

            //Fechar a janela de Login
            Stage loginStage = (Stage) btnLogin.getScene().getWindow();
            loginStage.close();
        } catch (Exception e) {
            e.printStackTrace();
            mostrarErro("Erro ao abrir Menu Principal: " +e.getMessage());
        }
    }
}
