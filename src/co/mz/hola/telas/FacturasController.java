package co.mz.hola.telas;

import co.mz.hola.controllers.Empresa;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import java.sql.*;
import co.mz.hola.dal.ModuloConexao;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Virgilio Chiboleca
 */
public class FacturasController implements Initializable {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    @FXML
    private JFXButton btnProsseguirFN;
    @FXML
    private JFXTabPane tabPaneNovaFactForms;
    @FXML
    private Tab tab2;
    @FXML
    private JFXTextField tfProcuraEmpresaFactura;
    @FXML
    private TableView<Empresa> tabelaEmpresaFactura;
    @FXML
    private TableColumn<Empresa, Integer> idEmpresaFactura;
    @FXML
    private TableColumn<Empresa, String> nomeEmpresaFactura;
    @FXML
    private TableColumn<Empresa, String> nuitEmpresaFactura;
    @FXML
    private TableColumn<Empresa, String> cotactoEmpresaFactura;

    ObservableList<Empresa> empresaObservableList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        conexao = ModuloConexao.conector();
        
        String sql = "select id, nomeEmpresa, nuit, contactoEmpresa from tbempresas";
        
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            
            while(rs.next()){
                Integer queryId = rs.getInt("id");
                String queryNome = rs.getString("nomeEmpresa");
                String queryNuit = rs.getString("nuit");
                String queryContacto = rs.getString("contactoEmpresa");
                
                empresaObservableList.add(new Empresa(queryId, queryNome, queryNuit, queryContacto));
                
                idEmpresaFactura.setCellValueFactory(new PropertyValueFactory<>("id"));
                nomeEmpresaFactura.setCellValueFactory(new PropertyValueFactory<>("nomeDaEmpresa"));
                nuitEmpresaFactura.setCellValueFactory(new PropertyValueFactory<>("nuitDaEmpresa"));
                cotactoEmpresaFactura.setCellValueFactory(new PropertyValueFactory<>("contactoDaEmpresa"));
                
                tabelaEmpresaFactura.setItems(empresaObservableList);
                
                FilteredList<Empresa> dadosFiltrados = new FilteredList<>(empresaObservableList, b -> true);
                
                tfProcuraEmpresaFactura.textProperty().addListener((observable, oldValue, newValue) -> {
                
                    dadosFiltrados.setPredicate(empresa -> {
                        
                        if (newValue.isEmpty() || newValue.trim().isEmpty() || newValue == null) {
                            return true;
                        }
                        
                        String palavraProcurada = newValue.toLowerCase();
                        
                        if (empresa.getNomeDaEmpresa().toLowerCase().indexOf(palavraProcurada) > -1) {
                            return true;
                        } else if(empresa.getNuitDaEmpresa().toLowerCase().indexOf(palavraProcurada) > -1) {
                            return true;
                        } else if(empresa.getContactoDaEmpresa().toLowerCase().indexOf(palavraProcurada) > -1) {
                            return true;
                        } else {
                            return false;
                        }
                        
                    });
                });
                
                SortedList<Empresa> dadosResultado = new SortedList<>(dadosFiltrados);
                
                dadosResultado.comparatorProperty().bind(tabelaEmpresaFactura.comparatorProperty());
                
                tabelaEmpresaFactura.setItems(dadosResultado);
            }
        } catch (SQLException e) {
            Logger.getLogger(FacturasController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
        
        
        
        btnProsseguirFN.setOnMouseClicked(event -> {

            tabPaneNovaFactForms.getSelectionModel().select(tab2);
        });

    }

}
