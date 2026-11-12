package com.albertokitala.gestaoescolar.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ControllerMenuPrincipal {


    @FXML private BorderPane mainPane; //  vai ser o BorderPane principal
    @FXML private Button btAlunos;
    @FXML private Button btDashboard;
    @FXML private Button btnAjuda;
    @FXML private Button btnCalendario;
    @FXML private Button btnComunicados;
    @FXML private Button btnConfiguracao;
    @FXML private Button btnFinanceiro;
    @FXML private Button btnNotas;
    @FXML private Button btnProfessor;
    @FXML private Button btnSair;
    @FXML private Button btnTurma;
    @FXML private VBox painelCentro;

     private List<Node> dashboardSalvo;


     @FXML
     public void initialize(){

         // Guardar o Dashbord original
         if(painelCentro  != null && painelCentro.getChildren().size()>0){
             dashboardSalvo = new ArrayList<>(painelCentro.getChildren());

         }
         selecionarBotao(btDashboard);
     }
     // pinta o botão clicado sem desabilitar nenhum
    private  void selecionarBotao(Button selecionado){
         Button[] todos ={ btDashboard,btAlunos,btnProfessor,btnTurma,btnNotas,btnFinanceiro,btnComunicados,btnCalendario,btnConfiguracao,btnAjuda,btnSair};
         for(Button b: todos){
             if(b != null){
                 b.setDisable(false); // Nunca desabilita, para sempre poder clicar
                 if(b== selecionado){
                     b.setStyle("-fx-background-color: #1e4a6e;-fx-text-fill: white; -fx-background-radius: 6; ");
                 }else{
                     b.setStyle("-fx-background-color: transparent;-fx-text-fill: white; ");
                 }
             }
         }
    }



   // Metodo para abrir a tela novoAluno
    @FXML
    void OnActionAbrirAlunos(ActionEvent event) {
        try{

           Node tela = FXMLLoader.load(getClass().getResource("/com/albertokitala/gestaoescolar/novoAlunos.fxml"));
           painelCentro.getChildren().setAll(tela);
           selecionarBotao(btAlunos);
        }catch (IOException e){
            e.printStackTrace();
        }

    }


    // Metodo para abrir o Dahboard
    @FXML
    void OnActionAbrirDashboard(ActionEvent event) {
        if(dashboardSalvo != null){
            painelCentro.getChildren().setAll(dashboardSalvo);

        }else{
            painelCentro.getChildren().clear();
        }
        selecionarBotao(btDashboard);
        System.out.println("Voltou para Dashboard");

    }


    // Metodo para habititar todos os botões

    private void habilitarTodos(){
        for(Button b : new Button[]{ btDashboard, btAlunos,btnProfessor,btnTurma,btnNotas,btnFinanceiro,btnComunicados,btnCalendario,btnConfiguracao,btnAjuda,btnSair}){
            if(b != null){
                b.setDisable(false);
                b.setStyle("-fx-background-color: transparent; -fx-text-fill: white;");
            }
        }
    }


    @FXML
    public void OnActionCadastrarNovoAluno(ActionEvent event) {
       try{
           FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/albertokitala/gestaoescolar/formCadastramentoAluno.fxml"));
           Parent root = loader.load();

           Stage stage = new Stage();
           stage.setTitle("Cadastrar Novo Aluno");
           stage.setScene(new Scene(root));
           stage.initModality(Modality.APPLICATION_MODAL);
           stage.show();
       }catch (IOException e){
           e.printStackTrace();
       }
    }

    public void OnActionEscolhaFoto(ActionEvent event) {
    }

    public void OnActionCancelar(ActionEvent event) {
    }

    public void OnActionCadastrar(ActionEvent event) {
    }

    public void OnActioAbrirProfessor(ActionEvent event) {
        try{

            Node tela = FXMLLoader.load(getClass().getResource("/com/albertokitala/gestaoescolar/ProfessorLista.fxml"));
            painelCentro.getChildren().setAll(tela);
            selecionarBotao(btnProfessor);
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    public void OnActionAbrirNovoProfessor(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/albertokitala/gestaoescolar/formCadastramentoProfessor.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Cadastrar Novo Professor");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void OnActionAbrirTurma(ActionEvent event) {
        try{

            Node tela = FXMLLoader.load(getClass().getResource("/com/albertokitala/gestaoescolar/TurmaLista.fxml"));
            painelCentro.getChildren().setAll(tela);
            selecionarBotao(btnTurma);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void OnActionCadastrarNovaTurma(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/albertokitala/gestaoescolar/formCadastrarNovaTurma.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Cadastrar Nova Turma");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /* Abrir a tela notas Turmas*/
    public void OnActionAbrirNotasTurmas(ActionEvent event) {
        try{
                Node tela = FXMLLoader.load(getClass().getResource("/com/albertokitala/gestaoescolar/NotasPorTurma.fxml"));
                painelCentro.getChildren().setAll(tela);
                selecionarBotao(btnNotas);
            }catch (IOException e){
            e.printStackTrace();
        }
    }
    /* Abrir a tela dos assuntos finaceiros*/
    public void OnActionFinanceiros(ActionEvent event) {

        try{
            Node tela = FXMLLoader.load(getClass().getResource("/com/albertokitala/gestaoescolar/GestFinanceira.fxml"));
            painelCentro.getChildren().setAll(tela);
            selecionarBotao(btnFinanceiro);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void OnActionRegistraPagamento(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/albertokitala/gestaoescolar/RegistPagamento.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Registrar Pagamento");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }



    }
}
