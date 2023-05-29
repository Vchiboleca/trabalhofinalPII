package co.mz.hola.controllers;

import java.util.ArrayList;

/**
 *
 * @author Virgilio Chiboleca
 */
public class Factura {

    public static ArrayList<ItensFactura> itens;

    public Factura() {
        itens = new ArrayList<>();
    }

    public void registarItens(String nomeDoProduto, String descricaoDoProduto, double precoUnitarioDoProduto, double taxaIvaDoProduto, int quantidades, double subtotal) {
        ItensFactura item = new ItensFactura(nomeDoProduto, descricaoDoProduto, precoUnitarioDoProduto, taxaIvaDoProduto, quantidades, subtotal);
        itens.add(item);
    }

    public static ArrayList<ItensFactura> getItens() {
        return itens;
    }

    public static void setItens(ArrayList<ItensFactura> itens) {
        Factura.itens = itens;
    }

    public double obterValorLiquidoDaFactura() {
        double valorLiquido = 0;
        for (ItensFactura item : itens) {
            valorLiquido += item.getSubtotal();
        }
        return valorLiquido;
    }

    public double obterValorBrutoDaFactura(Double taxa) {
        double valorBruto = 0;
        
        valorBruto = obterValorLiquidoDaFactura() + obterValorLiquidoDaFactura() * taxa;
        
        return valorBruto;
    }
    
    public double impostoAPagar(Double taxa) {
        double imposto = 0;
        
        imposto =  obterValorLiquidoDaFactura() * taxa;
        
        return imposto;
    }

}
