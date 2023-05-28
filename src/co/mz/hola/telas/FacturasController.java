package co.mz.hola.telas;

import co.mz.hola.controllers.Empresa;
import co.mz.hola.controllers.Factura;
import co.mz.hola.controllers.ItensFactura;
import co.mz.hola.controllers.VariaveisDoSistema;
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
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import co.mz.hola.telas.TelaDeLoginController;
import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Virgilio Chiboleca
 */
public class FacturasController implements Initializable {

    Factura factura = new Factura();
    String empresaDoUsuario = VariaveisDoSistema.empresaDoUsuarioCadastrado;
    String enderecoDoUsuario = VariaveisDoSistema.enderecoDoUsuarioCadastrado;
    String contactoDoUsuario = VariaveisDoSistema.contactoDoUsuarioCadastrado;

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
    private TableView<ItensFactura> tableViewItens;
    @FXML
    private TableColumn<Empresa, Integer> idEmpresaFactura;
    @FXML
    private TableColumn<Empresa, String> nomeEmpresaFactura;
    @FXML
    private TableColumn<Empresa, String> nuitEmpresaFactura;
    @FXML
    private TableColumn<Empresa, String> cotactoEmpresaFactura;
    @FXML
    private TableColumn<Empresa, String> enderecoEmpresaFactura;

    ObservableList<Empresa> empresaObservableList = FXCollections.observableArrayList();
    ObservableList<ItensFactura> itensObservableList = FXCollections.observableArrayList();

    @FXML
    private Label nomeEmitente;
    @FXML
    private Label nomeEmitenteEviadoPor;
    @FXML
    private Label enderecoEmitente;
    @FXML
    private Label nomeDestinatario;
    @FXML
    private Label enderecoDestinatario;
    @FXML
    private Label numeroFactura;
    @FXML
    private Label contactoEmitente;
    @FXML
    private TextField tfNomeProduto;
    @FXML
    private TextField tfDescricaoDoProduto;
    @FXML
    private TextField tfPrecoUnnitario;
    @FXML
    private TextField tfQuantidadesDoProduto;
    @FXML
    private TextField tfCodigoDoProduto;
    @FXML
    private ComboBox<Double> cbTaxaIva;
    @FXML
    private ComboBox<Double> cbDescontoComercial;
    @FXML
    private ComboBox<Double> cbTermosCondicoes;
    @FXML
    private JFXButton btnAdicionarItens;
    @FXML
    private Button btnTeste;
    @FXML
    private TableColumn<ItensFactura, String> colunaNomeDoProduto;
    @FXML
    private TableColumn<ItensFactura, String> colunaDescricaoDoProduto;
    @FXML
    private TableColumn<ItensFactura, Integer> colunaQuantidades;
    @FXML
    private TableColumn<ItensFactura, Double> colunaPrecoUnitario;
    @FXML
    private TableColumn<ItensFactura, Double> colunaValorLiquido;
    @FXML
    private JFXButton btnEmitirNova;
    @FXML
    private Tab tab1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        System.out.println(empresaDoUsuario);
        System.out.println(enderecoDoUsuario);

        cbTaxaIva.getItems().add(0.17);
        cbTaxaIva.getItems().add(0.16);

        conexao = ModuloConexao.conector();
        String sql = "select id, nomeEmpresa, nuit, contactoEmpresa, enderecoEmpresa from tbempresas";

        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                Integer queryId = rs.getInt("id");
                String queryNome = rs.getString("nomeEmpresa");
                String queryNuit = rs.getString("nuit");
                String queryContacto = rs.getString("contactoEmpresa");
                String queryEndereco = rs.getString("enderecoEmpresa");

                empresaObservableList.add(new Empresa(queryId, queryNome, queryNuit, queryContacto, queryEndereco));

                idEmpresaFactura.setCellValueFactory(new PropertyValueFactory<>("id"));
                nomeEmpresaFactura.setCellValueFactory(new PropertyValueFactory<>("nomeDaEmpresa"));
                nuitEmpresaFactura.setCellValueFactory(new PropertyValueFactory<>("nuitDaEmpresa"));
                cotactoEmpresaFactura.setCellValueFactory(new PropertyValueFactory<>("contactoDaEmpresa"));
                enderecoEmpresaFactura.setCellValueFactory(new PropertyValueFactory<>("enderecoDaEmpresa"));

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

                dadosResultado.comparatorProperty().bind(tabelaEmpresaFactura.comparatorProperty());

                tabelaEmpresaFactura.setItems(dadosResultado);
            }
        } catch (SQLException e) {
            Logger.getLogger(FacturasController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }

        btnProsseguirFN.setOnMouseClicked(event -> {
            

            carregarItensNaFactura(Factura.getItens(), tableViewItens);
            tabPaneNovaFactForms.getSelectionModel().select(tab2);

        });
        
        btnEmitirNova.setOnMouseClicked(event -> {
            
            tabPaneNovaFactForms.getSelectionModel().select(tab1);

        });

        btnTeste.setOnMouseClicked(event -> {

            lerItens();
        });

        /* btnAdicionarItens.setOnMouseClicked(event -> {
        
            adicionarItens();
        
        }); */
    }

    @FXML
    public void setarCamposFactura(MouseEvent event) {

        Integer index = tabelaEmpresaFactura.getSelectionModel().getSelectedIndex();

        if (index <= -1) {
            return;
        }

        nomeDestinatario.setText(nomeEmpresaFactura.getCellData(index).toString());
        enderecoDestinatario.setText(enderecoEmpresaFactura.getCellData(index).toString());
        contactoEmitente.setText(cotactoEmpresaFactura.getCellData(index).toString());

        setNomeEmpresa();
        setNomeEmpresaEnviadoPor();
        setEnderecoEmitente();
        setContactoEmitente();
    }

    public void adicionarFactura() {
        String sql = "insert into itens(nomeproduto, descricao, taxaiva, precounitario, quantidade) values(?,?,?,?,?)";

        try {
            pst = conexao.prepareStatement(sql);

            pst.setString(1, tfNomeProduto.getText());
            pst.setString(2, tfDescricaoDoProduto.getText());
            pst.setDouble(3, cbTaxaIva.getValue());
            pst.setDouble(4, Double.parseDouble(tfPrecoUnnitario.getText()));
            pst.setInt(5, Integer.parseInt(tfQuantidadesDoProduto.getText()));

            //Validacao dos campos obrigatorios
            if ((tfNomeProduto.getText().isEmpty()) || (tfDescricaoDoProduto.getText().isEmpty()) || (cbTaxaIva.getValue().isNaN()) || (tfPrecoUnnitario.getText().isEmpty()) || (tfQuantidadesDoProduto.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
            } else {

                int adicionado = pst.executeUpdate();

                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Usuário adicionado com sucesso");
                    //idUser.setText(null);
                    //cadNomeUsuario.setText(null);
                    //tfApelido.setText(null);

                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @FXML
    public void adicionarItens() {

        String nomeDoProduto = tfNomeProduto.getText();
        String descricaoDoProduto = tfDescricaoDoProduto.getText();
        double precoUnitarioDoProduto = Double.parseDouble(tfPrecoUnnitario.getText());
        double taxaIvaDoProduto = cbTaxaIva.getValue();
        int quantidades = Integer.parseInt(tfQuantidadesDoProduto.getText());
        double subtotal = quantidades * precoUnitarioDoProduto;

        ItensFactura item = new ItensFactura(nomeDoProduto, descricaoDoProduto, precoUnitarioDoProduto, taxaIvaDoProduto, quantidades, subtotal);
        factura.getItens().add(item);

    }

    public void lerItens() {
        System.out.println(factura.getItens().get(0).getDescricaoDoProduto());
        //System.out.println(obterNomeUsuarioLogado());

    }

    public void setNomeEmpresa() {
        this.nomeEmitente.setText(empresaDoUsuario);
    }

    public void setNomeEmpresaEnviadoPor() {
        this.nomeEmitenteEviadoPor.setText(empresaDoUsuario);
    }

    public void setEnderecoEmitente() {
        this.enderecoEmitente.setText(enderecoDoUsuario);
    }

    public void setContactoEmitente() {
        this.contactoEmitente.setText(contactoDoUsuario);
    }

    public void carregarItensNaFactura(ArrayList<ItensFactura> listaItens, TableView<ItensFactura> tableView) {
        
        //itensObservableList.add(new ItensFactura(queryId, queryNome, queryNuit, queryEndereco));

        colunaNomeDoProduto.setCellValueFactory(new PropertyValueFactory<>("nomeDoProduto"));
        colunaDescricaoDoProduto.setCellValueFactory(new PropertyValueFactory<>("descricaoDoProduto"));
        colunaPrecoUnitario.setCellValueFactory(new PropertyValueFactory<>("precoUnitarioDoProduto"));
        colunaQuantidades.setCellValueFactory(new PropertyValueFactory<>("quantidades"));
        colunaValorLiquido.setCellValueFactory(new PropertyValueFactory<>("subtotal"));
                
        itensObservableList.addAll(listaItens);

        tableView.setItems(itensObservableList);
    }

}
