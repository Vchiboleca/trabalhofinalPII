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
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Virgilio Chiboleca
 */
public class TelaDeLoginController implements Initializable {
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
    private JFXButton sairLogin;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           
    } 
    @FXML
    private void sairLogin(ActionEvent event){
        System.exit(0);
    }
    
    @FXML
    private void entrar(MouseEvent event1) throws IOException{
        
        Stage stage = new Stage();
        
        Parent root = FXMLLoader.load(getClass().getResource("Principal.fxml"));
        
        stage.initStyle(StageStyle.UNDECORATED);
        
        final double[] x = {0};
        final double[] y = {0};
         
    root.setOnMousePressed(event ->{
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
       ((Node)event1.getSource()).getScene().getWindow().hide();
        
    }
    
}
