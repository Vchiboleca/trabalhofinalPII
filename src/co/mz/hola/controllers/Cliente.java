package co.mz.hola.controllers;

import java.time.LocalDate;

/**
 *
 * @author Virgilio Chiboleca
 */
public class Cliente {
    private String nomeEmpresa;
    private int idFactura;
    private double valorBruto;
    private LocalDate dataEmissao;
    private LocalDate dataVencimento;
    private String status;

    public Cliente(String nomeEmpresa, int idFactura, double valorBruto, LocalDate dataEmissao, LocalDate dataVencimento, String status) {
        this.nomeEmpresa = nomeEmpresa;
        this.idFactura = idFactura;
        this.valorBruto = valorBruto;
        this.dataEmissao = dataEmissao;
        this.dataVencimento = dataVencimento;
        this.status = status;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public double getValorBruto() {
        return valorBruto;
    }

    public void setValorBruto(double valorBruto) {
        this.valorBruto = valorBruto;
    }

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDate dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

