package com.albertokitala.gestaoescolar.controller;

import com.albertokitala.gestaoescolar.dao.CadastroUsuariosDao;
import com.albertokitala.gestaoescolar.model.Usuarios;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CadastroUsuarioController implements Initializable {

    public Label lblVoltarLogin;
    @FXML private Button btnCadastrar;
    @FXML private Button close;
    @FXML private ComboBox<String> comboPerfil;
    @FXML private Button minimize;
    @FXML private PasswordField txtConfirmarSenha;
    @FXML private TextField txtEmail;
    @FXML private TextField txtNomeUtilizador;
    @FXML private PasswordField txtSenha;


    private CadastroUsuariosDao dao = new CadastroUsuariosDao();

    // Metodo para  fechar a tela de Login
    @FXML
    public  void exit(){
        System.exit(0);
    }


    @FXML
    public void OnActionMinimize() {
        Stage stage = (Stage)minimize.getScene().getWindow();
        stage.setIconified(true);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboPerfil.setItems(FXCollections.observableArrayList(
                "Admin", "Professor", "Aluno", "Secretaria"
        ));

    }

    @FXML
    public void onActionCadastrar(ActionEvent event) {

        String username = txtNomeUtilizador.getText();
        String email = txtEmail.getText();
        String senha = txtSenha.getText();
        String confirmar = txtConfirmarSenha.getText();
        String perfil = comboPerfil.getValue();

        if (username.isEmpty() || email.isEmpty() || senha.isEmpty() || perfil == null) {
            mostrarAlerta(" Atenção", " Preenha todos os campos!", Alert.AlertType.WARNING);
            return;
        }
        if (!senha.equals(confirmar)) {
            mostrarAlerta("Erro", "As senhas não coincidem!", Alert.AlertType.ERROR);
            return;
        }

        if (dao.emailJaexiste(email)) {
            mostrarAlerta("Erro", "Este email já está cadastrado!", Alert.AlertType.ERROR);
            return;
        }

        Usuarios novo = new Usuarios(username, email, senha, perfil);
        if (dao.cadastrar(novo)) {
            mostrarAlerta("Sucesso", "Usuario " + username + " Cadastrado com sucesso!", Alert.AlertType.INFORMATION);
            limparCampos();
        } else {
            mostrarAlerta("Erro", "Erro ao cadastrar usuário no banco!", Alert.AlertType.ERROR);
        }
    }

    @FXML


    // Limpar o campos da tela
    private void limparCampos(){
        txtNomeUtilizador.clear();
        txtEmail.clear();
        txtSenha.clear();
        txtConfirmarSenha.clear();
        comboPerfil.setValue(null);
    }
    // Metodo da janela
    private void mostrarAlerta(String titulo, String mensagem, Alert.AlertType tipo){
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    public void handleVoltarLogin() {

        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/albertokitala/gestaoescolar/Login.fxml"));
            Scene scene = new Scene(loader.load());
            Stage  stage = (Stage) txtNomeUtilizador.getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
