package co.mz.hola.telas;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Virgilio Chiboleca
 */
public class Main extends Application {
    double x,y = 0;
    
    @Override
    public void start(Stage stage) throws IOException {
        
        
        Parent root = FXMLLoader.load(getClass().getResource("TelaDeLogin.fxml"));
        
        stage.initStyle(StageStyle.UNDECORATED);
        
        root.setOnMousePressed(event ->{
            x = event.getSceneX();
            y = event.getSceneY();
            
        });
        
        root.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
            
        });
        
        stage.setScene(new Scene(root));
        stage.setTitle("Hola v1.0");
        stage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
