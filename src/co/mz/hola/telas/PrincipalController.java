package co.mz.hola.telas;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author Virgilio Chiboleca
 */
public class PrincipalController implements Initializable {

    
    @FXML
    private ImageView sair;
    
    @FXML
    private ImageView menu;
    @FXML
    private ImageView btnCadastro2;
    @FXML
    private AnchorPane pane1;
    @FXML
    private AnchorPane pane2;
    @FXML
    private StackPane contentor;
    @FXML
    private ImageView btnCadastro1;
    @FXML
    private JFXButton btnDashboard;
    @FXML
    private ImageView imgMPRelatorio;
    @FXML
    private JFXButton btnMPCadastro;
    @FXML
    private JFXButton btnMPRelatorio;

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        sair.setOnMouseClicked(event -> {
            System.exit(0);
        });
        
        btnCadastro2.setOnMouseClicked(event -> {
            try {
            Parent painel = FXMLLoader.load(getClass().getResource("Cadastro.fxml"));
            contentor.getChildren().removeAll();
            contentor.getChildren().setAll(painel);
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        });
        
        pane1.setVisible(false);
        
        
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), pane1);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();
        
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5), pane2);
        translateTransition.setByX(-600);
        translateTransition.play();
        
        menu.setOnMouseClicked(event ->{
        
            pane1.setVisible(true);
            
            FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(0.5), pane1);
            fadeTransition1.setFromValue(0);
            fadeTransition1.setToValue(0.15);
            fadeTransition1.play();

            TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5), pane2);
            translateTransition1.setByX(+600);
            translateTransition1.play();
        });
        
        pane1.setOnMouseClicked(event -> {
            
            FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(0.5), pane1);
            fadeTransition1.setFromValue(0.15);
            fadeTransition1.setToValue(0);
            fadeTransition1.play();
            
            fadeTransition1.setOnFinished(event1 -> {
                pane1.setVisible(false);
            });
            
            TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5), pane2);
            translateTransition1.setByX(-600);
            translateTransition1.play();
            
        });
        
        try {
            Parent painel = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
            contentor.getChildren().removeAll();
            contentor.getChildren().setAll(painel);
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    public ImageView getBtnCadastro2() {
        return btnCadastro2;
    }

    public void setBtnCadastro2(ImageView btnCadastro2) {
        this.btnCadastro2 = btnCadastro2;
    }

    public ImageView getImgMPRelatorio() {
        return imgMPRelatorio;
    }

    public void setImgMPRelatorio(ImageView imgMPRelatorio) {
        this.imgMPRelatorio = imgMPRelatorio;
    }

    public JFXButton getBtnMPCadastro() {
        return btnMPCadastro;
    }

    public void setBtnMPCadastro(JFXButton btnMPCadastro) {
        this.btnMPCadastro = btnMPCadastro;
    }

    public JFXButton getBtnMPRelatorio() {
        return btnMPRelatorio;
    }

    public void setBtnMPRelatorio(JFXButton btnMPRelatorio) {
        this.btnMPRelatorio = btnMPRelatorio;
    }
    
    public void activacaoBotao (){
        btnMPCadastro.setDisable(false);
    }

    
    @FXML
        public void dashboard(javafx.event.ActionEvent actionEvent) throws IOException{
            Parent painel = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
            contentor.getChildren().removeAll();
            contentor.getChildren().setAll(painel);
        }
        
    @FXML
        public void facturas(javafx.event.ActionEvent actionEvent) throws IOException{
            Parent painel = FXMLLoader.load(getClass().getResource("Facturas.fxml"));
            contentor.getChildren().removeAll();
            contentor.getChildren().setAll(painel);
        }
        
    @FXML
        public void clientes(javafx.event.ActionEvent actionEvent) throws IOException{
            Parent painel = FXMLLoader.load(getClass().getResource("Clientes.fxml"));
            contentor.getChildren().removeAll();
            contentor.getChildren().setAll(painel);
        }
        
    @FXML
        public void fornecedores(javafx.event.ActionEvent actionEvent) throws IOException{
            Parent painel = FXMLLoader.load(getClass().getResource("Fornecedores.fxml"));
            contentor.getChildren().removeAll();
            contentor.getChildren().setAll(painel);
        } 
        
    @FXML
        public void cadastro(javafx.event.ActionEvent actionEvent) throws IOException{
            Parent painel = FXMLLoader.load(getClass().getResource("Cadastro.fxml"));
            contentor.getChildren().removeAll();
            contentor.getChildren().setAll(painel);
        }
        
    @FXML
        public void relatorio(javafx.event.ActionEvent actionEvent) throws IOException{
            Parent painel = FXMLLoader.load(getClass().getResource("Relatorio.fxml"));
            contentor.getChildren().removeAll();
            contentor.getChildren().setAll(painel);
        }

           
}
