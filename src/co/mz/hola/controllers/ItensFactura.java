package co.mz.hola.controllers;

import java.util.ArrayList;

/**
 *
 * @author Virgilio Chiboleca
 */
public class ItensFactura {
    
    String nomeDoProduto;
    String descricaoDoProduto;
    double precoUnitarioDoProduto, taxaIvaDoProduto, subtotal;
    int quantidades;

    public ItensFactura(String nomeDoProduto, String descricaoDoProduto, double precoUnitarioDoProduto, double taxaIvaDoProduto, int quantidades, double subtotal) {
        this.nomeDoProduto = nomeDoProduto;
        this.descricaoDoProduto = descricaoDoProduto;
        this.precoUnitarioDoProduto = precoUnitarioDoProduto;
        this.taxaIvaDoProduto = taxaIvaDoProduto;
        this.quantidades = quantidades;
        this.subtotal = subtotal;
    }
    public ItensFactura(String nomeDoProduto, String descricaoDoProduto, double precoUnitarioDoProduto, int quantidades, double subtotal) {
        this.nomeDoProduto = nomeDoProduto;
        this.descricaoDoProduto = descricaoDoProduto;
        this.precoUnitarioDoProduto = precoUnitarioDoProduto;
        this.quantidades = quantidades;
        this.subtotal = subtotal;
    }

    public String getNomeDoProduto() {
        return nomeDoProduto;
    }

    public void setNomeDoProduto(String nomeDoProduto) {
        this.nomeDoProduto = nomeDoProduto;
    }

    public String getDescricaoDoProduto() {
        return descricaoDoProduto;
    }

    public void setDescricaoDoProduto(String descricaoDoProduto) {
        this.descricaoDoProduto = descricaoDoProduto;
    }

    public double getPrecoUnitarioDoProduto() {
        return precoUnitarioDoProduto;
    }

    public void setPrecoUnitarioDoProduto(double precoUnitarioDoProduto) {
        this.precoUnitarioDoProduto = precoUnitarioDoProduto;
    }

    public double getTaxaIvaDoProduto() {
        return taxaIvaDoProduto;
    }

    public void setTaxaIvaDoProduto(double taxaIvaDoProduto) {
        this.taxaIvaDoProduto = taxaIvaDoProduto;
    }

    public int getQuantidades() {
        return quantidades;
    }

    public void setQuantidades(int quantidades) {
        this.quantidades = quantidades;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
    
    
}
