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
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

/**
 * FXML Controller class
 *
 * @author Virgilio Chiboleca
 */
public class FacturasController implements Initializable {

    Factura factura = new Factura();

    String nomeDoUsuario = VariaveisDoSistema.nomeDoUsuarioCadastrado;
    int idDaEmpresaDoUsuario = VariaveisDoSistema.idDaEmpresaDoUsuarioCadastrado;
    String empresaDoUsuario = VariaveisDoSistema.empresaDoUsuarioCadastrado;
    String enderecoDoUsuario = VariaveisDoSistema.enderecoDoUsuarioCadastrado;
    String contactoDoUsuario = VariaveisDoSistema.contactoDoUsuarioCadastrado;
    int idEmpresaDestinataria = 0;

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
    private TextField tfCodigoDoProduto;
    @FXML
    private ComboBox<Double> cbTaxaIva;
    @FXML
    private ComboBox<Double> cbDescontoComercial;
    @FXML
    private ComboBox<Integer> cbTermosCondicoes;
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
    @FXML
    private TextField tfNumeroDoPedido;
    @FXML
    private Label lblTotalLiquido;
    @FXML
    private Label lblImpostoApagar;
    @FXML
    private Label lblTotalDaFactura;
    @FXML
    private Label lblDiaDoMesActual;
    @FXML
    private Label lblMesActual;
    @FXML
    private Label lblAnoActual;
    @FXML
    private Label lblDiaVencimento;
    @FXML
    private Label lblMesVencimento;
    @FXML
    private Label lblAnoVencimento;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        System.out.println(empresaDoUsuario);
        System.out.println(enderecoDoUsuario);
        System.out.println(nomeDoUsuario);
        System.out.println(idDaEmpresaDoUsuario);

        cbTaxaIva.getItems().add(0.17);
        cbTaxaIva.getItems().add(0.16);
        cbTaxaIva.setValue(0.17);

        cbTermosCondicoes.getItems().add(30);
        cbTermosCondicoes.getItems().add(60);
        cbTermosCondicoes.getItems().add(90);
        cbTermosCondicoes.setValue(30);

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

            //ArrayList<ItensFactura> listaItens = new ArrayList<>(); //depois
            carregarItensNaFactura(Factura.getItens(), tableViewItens); //antes
            //carregarItensNaFactura(listaItens, tableViewItens);
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

        idEmpresaDestinataria = Integer.parseInt(idEmpresaFactura.getCellData(index).toString());

        setNomeEmpresa();
        setNomeEmpresaEnviadoPor();
        setEnderecoEmitente();
        setContactoEmitente();
        actualizarDatas();
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

        setarValoresDaFactura();
    }

    public void lerItens() {
        System.out.println(factura.getItens().get(0).getDescricaoDoProduto());
        //System.out.println(obterNomeUsuarioLogado());
        System.out.println(factura.obterValorLiquidoDaFactura());
        System.out.println(factura.obterValorBrutoDaFactura(cbTaxaIva.getValue()));
        System.out.println("Hello World");
        System.out.println("Ide da empresa de destino: " + idEmpresaDestinataria);
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

    public void actualizarDatas() {
        // Obter a data atual
        LocalDate dataAtual = LocalDate.now();

        // Obter o dia do mês atual
        int diaAtual = dataAtual.getDayOfMonth();
        lblDiaDoMesActual.setText(String.valueOf(diaAtual));

        // Obter o mês atual em formato abreviado com três letras iniciais
        String mesAtualAbreviado = dataAtual.format(DateTimeFormatter.ofPattern("MMM"));
        lblMesActual.setText(mesAtualAbreviado);

        // Obter o ano atual
        int anoAtual = dataAtual.getYear();
        lblAnoActual.setText(String.valueOf(anoAtual));

        // Obter o número de dias selecionados na combobox cbTermosCondicoes
        int diasParaVencimento = cbTermosCondicoes.getValue();
        System.out.println(diasParaVencimento);
        // Criar um período de tempo com a quantidade de dias selecionados
        Period periodo = Period.ofDays(diasParaVencimento);

        // Calcular a data de vencimento adicionando o período à data atual
        LocalDate dataVencimento = dataAtual.plus(periodo);

        // Obter o dia do mês de vencimento
        int diaVencimento = dataVencimento.getDayOfMonth();
        lblDiaVencimento.setText(String.valueOf(diaVencimento));

        // Obter o mês de vencimento em formato abreviado com três letras iniciais
        String mesVencimentoAbreviado = dataVencimento.format(DateTimeFormatter.ofPattern("MMM"));
        lblMesVencimento.setText(mesVencimentoAbreviado);

        // Obter o ano de vencimento
        int anoVencimento = dataVencimento.getYear();
        lblAnoVencimento.setText(String.valueOf(anoVencimento));
    }

    public void setarValoresDaFactura() {
        double valorLiquido = factura.obterValorLiquidoDaFactura();
        double imposto = factura.impostoAPagar(cbTaxaIva.getValue());
        double valorBruto = factura.obterValorBrutoDaFactura(cbTaxaIva.getValue());

        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setCurrencySymbol("MT");
        simbolos.setDecimalSeparator(',');
        simbolos.setGroupingSeparator('.');

        DecimalFormat formatoMonetario = new DecimalFormat("#,##0.00", simbolos);
        String valorLiquidoFormatado = formatoMonetario.format(valorLiquido);
        String impostoFormatado = formatoMonetario.format(imposto);
        String valorBrutoFormatado = formatoMonetario.format(valorBruto);

        lblTotalLiquido.setText("Total Líquido: " + valorLiquidoFormatado + " MT");
        lblImpostoApagar.setText("Imposto a Pagar " + impostoFormatado + " MT");
        lblTotalDaFactura.setText("Total da Factura " + valorBrutoFormatado + " MT");
    }

    public void carregarItensNaFactura(ArrayList<ItensFactura> listaItens, TableView<ItensFactura> tableView) {

        //itensObservableList.add(new ItensFactura(queryId, queryNome, queryNuit, queryEndereco));
        colunaNomeDoProduto.setCellValueFactory(new PropertyValueFactory<>("nomeDoProduto"));
        colunaDescricaoDoProduto.setCellValueFactory(new PropertyValueFactory<>("descricaoDoProduto"));
        colunaPrecoUnitario.setCellValueFactory(new PropertyValueFactory<>("precoUnitarioDoProduto"));
        colunaQuantidades.setCellValueFactory(new PropertyValueFactory<>("quantidades"));
        colunaValorLiquido.setCellValueFactory(new PropertyValueFactory<>("subtotal"));

        //tableView.getColumns().addAll(colunaNomeDoProduto, colunaDescricaoDoProduto, colunaPrecoUnitario, colunaQuantidades, colunaValorLiquido);
        ObservableList<ItensFactura> itensObservableList = FXCollections.observableArrayList();

        itensObservableList.addAll(listaItens); //Antes

        tableView.setItems(itensObservableList); //antes
    }

    @FXML
    public void adicionarFactura() {
        String sql = "INSERT INTO tbfacturas (numeropedido, valorliquido, termos, valorbruto, imposto_a_pagar, cliente, fornecedor) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            pst = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pst.setString(1, tfNumeroDoPedido.getText());
            pst.setDouble(2, factura.obterValorLiquidoDaFactura());
            pst.setInt(3, cbTermosCondicoes.getValue());
            pst.setDouble(4, factura.obterValorBrutoDaFactura(cbTaxaIva.getValue()));
            pst.setDouble(5, factura.impostoAPagar(cbTaxaIva.getValue()));
            pst.setInt(6, idEmpresaDestinataria);
            pst.setInt(7, idDaEmpresaDoUsuario);

            // Executa a inserção da fatura no banco de dados
            int adicionado = pst.executeUpdate();

            if (adicionado > 0) {
                // Obtém o número da fatura gerado
                ResultSet rs = pst.getGeneratedKeys();
                int idFactura = 0;
                if (rs.next()) {
                    idFactura = rs.getInt(1);
                }
                rs.close();

                // Insere os itens na tabela "itens"
                String insertItensSql = "INSERT INTO itens (nomeproduto, descricao, precounitario, quantidade, subtotal, taxaiva, idfactura) VALUES (?, ?, ?, ?, ?, ?, ?)";
                pst = conexao.prepareStatement(insertItensSql);

                for (ItensFactura item : tableViewItens.getItems()) {
                    pst.setString(1, item.getNomeDoProduto());
                    pst.setString(2, item.getDescricaoDoProduto());
                    pst.setDouble(3, item.getPrecoUnitarioDoProduto());
                    pst.setInt(4, item.getQuantidades());
                    pst.setDouble(5, item.getSubtotal());
                    pst.setDouble(6, item.getTaxaIvaDoProduto());
                    pst.setInt(7, idFactura);
                    pst.executeUpdate();
                }

                JOptionPane.showMessageDialog(null, "Factura enviada com sucesso");
                // Limpa os campos após a inserção
                tfNumeroDoPedido.setText("");
                tfNomeProduto.setText("");
                tfDescricaoDoProduto.setText("");
                tfPrecoUnnitario.setText("");
                tfQuantidadesDoProduto.setText("");
                cbTaxaIva.setValue(null);
                cbTermosCondicoes.setValue(null);
                // Limpa a lista de itens da factura
                tableViewItens.getItems().clear();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao adicionar factura: " + e.getMessage());
        }
    }

}
