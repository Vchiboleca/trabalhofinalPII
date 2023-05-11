package co.mz.hola.telas;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.sql.*;
import co.mz.hola.dal.ModuloConexao;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.Label;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Virgilio Chiboleca
 */
public class TelaDeLoginController implements Initializable {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    @FXML
    private Label lblStatus;
    @FXML
    private JFXTextField tfNomeUsuario;
    @FXML
    private JFXPasswordField tfSenhaUsuario;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conexao = ModuloConexao.conector();
        //Teste conexao
        //System.out.println(conexao);
        if (conexao != null) {
            lblStatus.setText("Conectado");

        } else {
            lblStatus.setText("Nao conectado");
        }
    }

    @FXML
    private void sairLogin(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void entrar(MouseEvent event1) throws IOException {

        String sql = "SELECT * FROM tbusuarios WHERE usuario = ? AND senha = ? ";

        try {
            //Preparacao da consulta a base de dados
            pst = conexao.prepareStatement(sql);
            pst.setString(1, tfNomeUsuario.getText());
            pst.setString(2, tfSenhaUsuario.getText());

            //Execusao da query
            rs = pst.executeQuery();

            //Verifica se existe o usuario e a senha correspondente
            if (rs.next()) {
                //Obtencao do conteudo do campo perfil
                String perfil = rs.getString(8);
                //System.out.println(perfil);
                if (perfil.equals("Gestor")) {
                    
                    
                    Stage stage = new Stage();
                    
                    Parent root = FXMLLoader.load(getClass().getResource("Principal.fxml"));

                    stage.initStyle(StageStyle.UNDECORATED);
                    
                    
                    
                    final double[] x = {0};
                    final double[] y = {0};

                    root.setOnMousePressed(event -> {
                        x[0] = event.getSceneX();
                        y[0] = event.getSceneY();
                    });

                    root.setOnMouseDragged(event -> {
                        stage.setX(event.getScreenX() - x[0]);
                        stage.setY(event.getScreenY() - y[0]);
                    });

                    stage.setScene(new Scene(root));
                    stage.setTitle("Hola v1.0");

                   


                    stage.show();

                    ((Node) event1.getSource()).getScene().getWindow().hide();
                     
                }
            } else {
                JOptionPane.showMessageDialog(null, "Usuario e/ou senha invalido");
            }
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, e);
        }

    }

}
