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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import co.mz.hola.controllers.Empresa;

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

    @FXML
    private JFXTextField tfProcuraEmpresa;
    @FXML
    private TableView<Empresa> tabelaEmpresas;
    @FXML
    private TextField nomeEmpresa;
    @FXML
    private TextField nuitEmpresa;
    @FXML
    private TextField enderecoEmpresa;
    @FXML
    private TextField contactoEmpresa;
    @FXML
    private TextField emailEmpresa;
    @FXML
    private TextField nomeResponsavelEmpresa;
    @FXML
    private TextField contactoResponsavelEmpresa;
    @FXML
    private TextField ramoActividade;
    @FXML
    private TextField sector;
    @FXML
    private TextField numeroFuncionarios;

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

            //Validacao dos campos obrigatorios
            if ((cadNomeUsuario.getText().isEmpty()) || (tfApelido.getText().isEmpty()) || (tfUtilizador.getText().isEmpty()) || (tfSenha.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
            } else {

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
                    cbEmpresa.setValue(null);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //Criando o metodo para alterar dados do usuario
    @FXML
    public void alterar() {
        String sql = "update tbusuarios set nome=?, apelido=?, usuario=?, senha=?, contacto=?, email=?, perfil=?, empresa=? where iuser=?";

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
            pst.setString(9, idUser.getText());

            if ((cadNomeUsuario.getText().isEmpty()) || (tfApelido.getText().isEmpty()) || (tfUtilizador.getText().isEmpty()) || (tfSenha.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
            } else {

                //Actualizacao da tabela tbusuarios com os dados do usuario
                //Confirmacao de insercao dos dados na tabela
                int adicionado = pst.executeUpdate();

                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados do usuário alterados com sucesso");
                    idUser.setText(null);
                    cadNomeUsuario.setText(null);
                    tfApelido.setText(null);
                    tfUtilizador.setText(null);
                    tfSenha.setText(null);
                    tfContacto.setText(null);
                    tfEmail.setText(null);
                    cbEmpresa.setValue(null);
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @FXML
    public void remover() {

        //Confirmacao de remocao de usuario
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover o usuário?", "Atenção", JOptionPane.YES_NO_OPTION);

        if (confirma == JOptionPane.YES_OPTION) {

            String sql = "delete from tbusuarios where iuser = ?";

            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, idUser.getText());
                int apagado = pst.executeUpdate();

                if (apagado > 0) {

                    JOptionPane.showMessageDialog(null, "Usuário removido com sucesso");
                    //Apaga os campos
                    idUser.setText(null);
                    cadNomeUsuario.setText(null);
                    tfApelido.setText(null);
                    tfUtilizador.setText(null);
                    tfSenha.setText(null);
                    tfContacto.setText(null);
                    tfEmail.setText(null);
                    cbEmpresa.setValue(null);

                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

    }

    //Metodo para adicionar Empresas
    public void adicionarEmpresas() {
        String sql = "insert into tbempresas(nomeEmpresa, nuit, enderecoEmpresa, contactoEmpresa, emailEmpresa, nomeResponsavelEmpresa, contactoDoResponsavel, ramoActividade, sector, numeroFuncionarios) values(?,?,?,?,?,?,?,?,?,?)";

        try {

            pst = conexao.prepareStatement(sql);

            pst.setString(1, nomeEmpresa.getText());
            pst.setString(2, nuitEmpresa.getText());
            pst.setString(3, enderecoEmpresa.getText());
            pst.setString(4, contactoEmpresa.getText());
            pst.setString(5, emailEmpresa.getText());
            pst.setString(6, nomeResponsavelEmpresa.getText());
            pst.setString(7, contactoResponsavelEmpresa.getText());
            pst.setString(8, ramoActividade.getText());
            pst.setString(9, sector.getText());
            pst.setString(10, numeroFuncionarios.getText());

            //Validacao dos campos obrigatorios
            if ((nomeEmpresa.getText().isEmpty()) || (nuitEmpresa.getText().isEmpty()) || (enderecoEmpresa.getText().isEmpty()) || (contactoEmpresa.getText().isEmpty()) || (emailEmpresa.getText().isEmpty()) || (nomeResponsavelEmpresa.getText().isEmpty()) || (ramoActividade.getText().isEmpty()) || (sector.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
            } else {

                //Actualizacao da tabela tbusuarios com os dados do usuario
                //Confirmacao de insercao dos dados na tabela
                int adicionado = pst.executeUpdate();

                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Empresa adicionada com sucesso");
                    nomeEmpresa.setText(null);
                    nuitEmpresa.setText(null);
                    enderecoEmpresa.setText(null);
                    contactoEmpresa.setText(null);
                    emailEmpresa.setText(null);
                    nomeResponsavelEmpresa.setText(null);
                    contactoResponsavelEmpresa.setText(null);
                    ramoActividade.setText(null);
                    sector.setText(null);
                    numeroFuncionarios.setText(null);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //Metodo para pesquisar clientes pelo nome com filtros
    public void pesquisarEmpresas() {

        
    }

}
