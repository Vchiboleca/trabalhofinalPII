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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

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
    @FXML
    private TableColumn<Empresa, Integer> idEmpresaCadastro;
    @FXML
    private TableColumn<Empresa, String> nomeEmpresaCadastro;
    @FXML
    private TableColumn<Empresa, String> nuitEmpresaCadastro;
    @FXML
    private TableColumn<Empresa, String> enderecoEmpresaCadastro;
    @FXML
    private TableColumn<Empresa, String> contactoEmpresaCadastro;
    @FXML
    private TableColumn<Empresa, String> emailEmpresaCadastro;
    @FXML
    private TableColumn<Empresa, String> responsavelEmpresaCadastro;
    @FXML
    private TableColumn<Empresa, String> contactoRespEmpresaCadastro;
    @FXML
    private TableColumn<Empresa, String> actividadeEmpresaCadastro;
    @FXML
    private TableColumn<Empresa, String> sectorEmpresaCadastro;
    @FXML
    private TableColumn<Empresa, Integer> numFuncionariosEmpresaCadastro;
    @FXML
    private TableView<Empresa> tabelaEmpresasCadastro;

    ObservableList<Empresa> empresaCadastroObservableList = FXCollections.observableArrayList();
    ObservableList<Empresa> escolherEmpresaObservableList = FXCollections.observableArrayList();

    @FXML
    private TextField tfIdEmpresa;
    @FXML
    private TableView<Empresa> tabelaEscolherEmpresa;
    @FXML
    private TableColumn<Empresa, String> colunaUnicaEmpresa;
    @FXML
    private JFXTextField txtProcurarEmpresaCadastro;
    @FXML
    private JFXTextField tfEmpresa;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cbPerfil.getItems().add("Gestor");
        cbPerfil.getItems().add("Operador");

        //cbEmpresa.getItems().add("Empresa1");
        //cbEmpresa.getItems().add("Hola");
        conexao = ModuloConexao.conector();

        String sql = "select id, nomeEmpresa, nuit, enderecoEmpresa, contactoEmpresa, emailEmpresa, nomeResponsavelEmpresa, contactoDoResponsavel, ramoActividade, sector, numeroFuncionarios from tbempresas";

        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                Integer queryId = rs.getInt("id");
                String queryNome = rs.getString("nomeEmpresa");
                String queryNuit = rs.getString("nuit");
                String queryEndereco = rs.getString("enderecoEmpresa");
                String queryContacto = rs.getString("contactoEmpresa");
                String queryEmail = rs.getString("emailEmpresa");
                String queryNomeResponsavel = rs.getString("nomeResponsavelEmpresa");
                String queryContactoResponsavel = rs.getString("contactoDoResponsavel");
                String queryRamo = rs.getString("ramoActividade");
                String querySector = rs.getString("sector");
                Integer queryNrFuncionarios = rs.getInt("numeroFuncionarios");

                empresaCadastroObservableList.add(new Empresa(queryId, queryNome, queryNuit, queryEndereco, queryContacto, queryEmail, queryNomeResponsavel, queryContactoResponsavel, queryRamo, querySector, queryNrFuncionarios));

                escolherEmpresaObservableList.add(new Empresa(queryNome));

                idEmpresaCadastro.setCellValueFactory(new PropertyValueFactory<>("id"));
                nomeEmpresaCadastro.setCellValueFactory(new PropertyValueFactory<>("nomeDaEmpresa"));
                nuitEmpresaCadastro.setCellValueFactory(new PropertyValueFactory<>("nuitDaEmpresa"));
                enderecoEmpresaCadastro.setCellValueFactory(new PropertyValueFactory<>("enderecoDaEmpresa"));
                contactoEmpresaCadastro.setCellValueFactory(new PropertyValueFactory<>("contactoDaEmpresa"));
                emailEmpresaCadastro.setCellValueFactory(new PropertyValueFactory<>("emailDaEmpresa"));
                responsavelEmpresaCadastro.setCellValueFactory(new PropertyValueFactory<>("nomeDoResponsavelDaEmpresa"));
                contactoRespEmpresaCadastro.setCellValueFactory(new PropertyValueFactory<>("contactoDoResponsavelDaEmpresa"));
                actividadeEmpresaCadastro.setCellValueFactory(new PropertyValueFactory<>("ramoDaEmpresa"));
                sectorEmpresaCadastro.setCellValueFactory(new PropertyValueFactory<>("sectoDaEmpresar"));
                numFuncionariosEmpresaCadastro.setCellValueFactory(new PropertyValueFactory<>("numFuncionariosDaEmpresa"));

                //Tabela no cadastro de usuario
                colunaUnicaEmpresa.setCellValueFactory(new PropertyValueFactory<>("nomeDaEmpresa"));

                tabelaEmpresasCadastro.setItems(empresaCadastroObservableList);

                tabelaEscolherEmpresa.setItems(escolherEmpresaObservableList);

                FilteredList<Empresa> dadosFiltrados = new FilteredList<>(empresaCadastroObservableList, b -> true);

                tfProcuraEmpresa.textProperty().addListener((observable, oldValue, newValue) -> {

                    dadosFiltrados.setPredicate(empresa -> {

                        if (newValue.isEmpty() || newValue.trim().isEmpty() || newValue == null) {
                            return true;
                        }

                        String palavraProcurada = newValue.toLowerCase();

                        if (empresa.getNomeDaEmpresa().toLowerCase().indexOf(palavraProcurada) > -1) {
                            return true;
                        } else if (empresa.getNuitDaEmpresa().toLowerCase().indexOf(palavraProcurada) > -1) {
                            return true;
                        } else if (empresa.getContactoDaEmpresa().toLowerCase().indexOf(palavraProcurada) > -1) {
                            return true;
                        } else {
                            return false;
                        }

                    });
                });

                SortedList<Empresa> dadosResultado = new SortedList<>(dadosFiltrados);

                dadosResultado.comparatorProperty().bind(tabelaEmpresasCadastro.comparatorProperty());

                tabelaEmpresasCadastro.setItems(dadosResultado);

                //Para tabela Empresa no formulario de Cadastro de Usuarios
                FilteredList<Empresa> dadosFiltradosCadastro = new FilteredList<>(escolherEmpresaObservableList, b -> true);

                txtProcurarEmpresaCadastro.textProperty().addListener((observable, oldValue, newValue) -> {

                    dadosFiltradosCadastro.setPredicate(empresa -> {

                        if (newValue.isEmpty() || newValue.trim().isEmpty() || newValue == null) {
                            return true;
                        }

                        String palavraProcurada = newValue.toLowerCase();

                        if (empresa.getNomeDaEmpresa().toLowerCase().indexOf(palavraProcurada) > -1) {
                            return true;
                        } else {
                            return false;
                        }

                    });
                });

                SortedList<Empresa> dadosResultadoCadastro = new SortedList<>(dadosFiltradosCadastro);

                dadosResultadoCadastro.comparatorProperty().bind(tabelaEmpresasCadastro.comparatorProperty());

                tabelaEscolherEmpresa.setItems(dadosResultadoCadastro);

            }
        } catch (SQLException e) {
            Logger.getLogger(FacturasController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }

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
                //cbEmpresa.setValue(rs.getString(9));
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
                //cbEmpresa.setValue(null);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }
    // Método para obter o ID da empresa com base no nome

    private int obterIdEmpresa(String nomeEmpresa) {
        String sql = "SELECT id FROM tbempresas WHERE nomeEmpresa = ?";
        int idEmpresa = 0;

        try {
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setString(1, nomeEmpresa);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                idEmpresa = rs.getInt("id");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

        return idEmpresa;
    }
    
    
    //Metodo para adicionar usuarios
    @FXML
    public void adicionar() {
        String sql = "insert into tbusuarios(nome, apelido, usuario, senha, contacto, email, perfil, empresa_id) values(?,?,?,?,?,?,?,?)";

        try {
            pst = conexao.prepareStatement(sql);

            pst.setString(1, cadNomeUsuario.getText());
            pst.setString(2, tfApelido.getText());
            pst.setString(3, tfUtilizador.getText());
            pst.setString(4, tfSenha.getText());
            pst.setString(5, tfContacto.getText());
            pst.setString(6, tfEmail.getText());
            pst.setString(7, cbPerfil.getValue());

            String nome = tfEmpresa.getText();
            int idDaEmpresaSelecionada = obterIdEmpresa(nome);
            pst.setInt(8, idDaEmpresaSelecionada);

            //Validacao dos campos obrigatorios
            if ((cadNomeUsuario.getText().isEmpty()) || (tfApelido.getText().isEmpty()) || (tfUtilizador.getText().isEmpty()) || (tfSenha.getText().isEmpty()) || (tfEmpresa.getText().isEmpty())) {
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
                    tfEmpresa.setText(null);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //Criando o metodo para alterar dados do usuario
    @FXML
    public void alterar() {
        String sql = "update tbusuarios set nome=?, apelido=?, usuario=?, senha=?, contacto=?, email=?, perfil=? where iuser=?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, cadNomeUsuario.getText());
            pst.setString(2, tfApelido.getText());
            pst.setString(3, tfUtilizador.getText());
            pst.setString(4, tfSenha.getText());
            pst.setString(5, tfContacto.getText());
            pst.setString(6, tfEmail.getText());
            pst.setString(7, cbPerfil.getValue());
            //pst.setString(8, cbEmpresa.getValue());
            pst.setString(8, idUser.getText());

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

                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

    }

    //Metodo para adicionar Empresas
    @FXML
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

    @FXML
    public void setarCampos(MouseEvent event) {

        Integer index = tabelaEmpresasCadastro.getSelectionModel().getSelectedIndex();

        if (index <= -1) {
            return;
        }

        tfIdEmpresa.setText(idEmpresaCadastro.getCellData(index).toString());
        nomeEmpresa.setText(nomeEmpresaCadastro.getCellData(index).toString());
        nuitEmpresa.setText(nuitEmpresaCadastro.getCellData(index).toString());
        enderecoEmpresa.setText(enderecoEmpresaCadastro.getCellData(index).toString());
        contactoEmpresa.setText(contactoEmpresaCadastro.getCellData(index).toString());
        emailEmpresa.setText(emailEmpresaCadastro.getCellData(index).toString());
        nomeResponsavelEmpresa.setText(responsavelEmpresaCadastro.getCellData(index).toString());
        contactoResponsavelEmpresa.setText(contactoRespEmpresaCadastro.getCellData(index).toString());
        ramoActividade.setText(actividadeEmpresaCadastro.getCellData(index).toString());
        sector.setText(sectorEmpresaCadastro.getCellData(index).toString());
        numeroFuncionarios.setText(numFuncionariosEmpresaCadastro.getCellData(index).toString());

    }

    @FXML
    public void setarCampoEmpresa(MouseEvent event) {

        Integer index = tabelaEscolherEmpresa.getSelectionModel().getSelectedIndex();
        if (index <= -1) {

            return;
        }

        tfEmpresa.setText(colunaUnicaEmpresa.getCellData(index).toString());
    }

    @FXML
    public void alterarDadosEmpresa() {
        String sql = "update tbempresas set nomeEmpresa=?, nuit=?, enderecoEmpresa=?, contactoEmpresa=?, emailEmpresa=?, nomeResponsavelEmpresa=?, contactoDoResponsavel=?, ramoActividade=?, sector=?, numeroFuncionarios=? where id=?";

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
            pst.setString(11, tfIdEmpresa.getText());

            if ((nomeEmpresa.getText().isEmpty()) || (nuitEmpresa.getText().isEmpty()) || (enderecoEmpresa.getText().isEmpty()) || (contactoEmpresa.getText().isEmpty()) || (emailEmpresa.getText().isEmpty()) || (nomeResponsavelEmpresa.getText().isEmpty()) || (ramoActividade.getText().isEmpty()) || (sector.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
            } else {

                //Actualizacao da tabela tbusuarios com os dados do usuario
                //Confirmacao de insercao dos dados na tabela
                int adicionado = pst.executeUpdate();

                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados da empresa alterados com sucesso");
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
    

}
