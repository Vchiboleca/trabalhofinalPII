package co.mz.hola.telas;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Virgilio Chiboleca
 */
public class ItensFacturasRecebidasController implements Initializable {

    @FXML
    private ImageView logoFoto;
    @FXML
    private Label nodeNome;
    @FXML
    private Label nodeNrFactura;
    @FXML
    private Label nodeMontante;
    @FXML
    private Label nodeData;
    @FXML
    private Label nodePrazoEmDias;
    @FXML
    private JFXButton nodeStatus;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
