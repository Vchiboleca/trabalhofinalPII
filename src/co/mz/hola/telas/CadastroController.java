package co.mz.hola.telas;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.sql.*;
import co.mz.hola.dal.ModuloConexao;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Virgilio Chiboleca
 */
public class CadastroController implements Initializable {

    @FXML
    private JFXTextField cadNomeUsuario;
    @FXML
    private JFXTextField tfContacto;
    @FXML
    private JFXTextField tfUtilizador;
    @FXML
    private JFXTextField tfApelido;
    @FXML
    private JFXTextField tfEmail;
    @FXML
    private JFXTextField tfSenha;
    @FXML
    private JFXComboBox<String> cbPerfil;
    @FXML
    private JFXComboBox<String> cbEmpresa;
    @FXML
    private JFXButton btnAdicionar;
    @FXML
    private JFXButton btnConsultar;
    @FXML
    private JFXButton btnAlterar;
    @FXML
    private JFXButton btnRemover;
    @FXML
    private TextField idUser;

    //Variaveis de Conexao 
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cbPerfil.getItems().add("Gestor");
        cbPerfil.getItems().add("Operador");

        cbEmpresa.getItems().add("Empresa1");
        cbEmpresa.getItems().add("Hola");

        conexao = ModuloConexao.conector();
    }
    
    
    //Metodo para adicionar usuarios
    @FXML
    public void consultar() {
        String sql = "select * from tbusuarios where iuser = ?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, idUser.getText());
            rs = pst.executeQuery();

            if (rs.next()) {
                cadNomeUsuario.setText(rs.getString(2));
                tfApelido.setText(rs.getString(3));
                tfUtilizador.setText(rs.getString(4));
                tfSenha.setText(rs.getString(5));
                tfContacto.setText(rs.getString(6));
                tfEmail.setText(rs.getString(7));
                cbPerfil.setValue(rs.getString(8));
                cbEmpresa.setValue(rs.getString(9));
            } else {
                JOptionPane.showMessageDialog(null, "Usuário não cadastrado");
                //Para limpar campos apos a consulta
                idUser.setText(null);
                cadNomeUsuario.setText(null);
                tfApelido.setText(null);
                tfUtilizador.setText(null);
                tfSenha.setText(null);
                tfContacto.setText(null);
                tfEmail.setText(null);
                cbPerfil.setValue(null);
                cbEmpresa.setValue(null);

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }
    
    //Metodo para adicionar usuarios
    @FXML
    public void adicionar() {
        String sql = "insert into tbusuarios(nome, apelido, usuario, senha, contacto, email, perfil, empresa) values(?,?,?,?,?,?,?,?)";
        
        try {
            pst = conexao.prepareStatement(sql);
            
            pst.setString(1, cadNomeUsuario.getText());
            pst.setString(2, tfApelido.getText());
            pst.setString(3, tfUtilizador.getText());
            pst.setString(4, tfSenha.getText());
            pst.setString(5, tfContacto.getText());
            pst.setString(6, tfEmail.getText());
            pst.setString(7, cbPerfil.getValue());
            pst.setString(8, cbEmpresa.getValue());
            
            //Actualizacao da tabela tbusuarios com os dados do usuario
            
            //Confirmacao de insercao dos dados na tabela
            int adicionado = pst.executeUpdate();
            
            if (adicionado > 0) {
                JOptionPane.showMessageDialog(null, "Usuário adicionado com sucesso");
                idUser.setText(null);
                cadNomeUsuario.setText(null);
                tfApelido.setText(null);
                tfUtilizador.setText(null);
                tfSenha.setText(null);
                tfContacto.setText(null);
                tfEmail.setText(null);
                cbPerfil.setValue(null);
                cbEmpresa.setValue(null);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

}
