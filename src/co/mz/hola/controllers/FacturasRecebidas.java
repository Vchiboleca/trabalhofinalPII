
package co.mz.hola.controllers;

import java.util.Date;

/**
 *
 * @author Virgilio Chiboleca
 */
public class FacturasRecebidas {
    
    private String nomeDoCliente;
    private int idDaFactura;
    private double montante;
    private Date dataDeEmissao;
    private int vencimento;
    private String status;

    public String getNomeDoCliente() {
        return nomeDoCliente;
    }

    public void setNomeDoCliente(String nomeDoCliente) {
        this.nomeDoCliente = nomeDoCliente;
    }

    public int getIdDaFactura() {
        return idDaFactura;
    }

    public void setIdDaFactura(int idDaFactura) {
        this.idDaFactura = idDaFactura;
    }

    public double getMontante() {
        return montante;
    }

    public void setMontante(double montante) {
        this.montante = montante;
    }

    public Date getDataDeEmissao() {
        return dataDeEmissao;
    }

    public void setDataDeEmissao(Date dataDeEmissao) {
        this.dataDeEmissao = dataDeEmissao;
    }

    public int getVencimento() {
        return vencimento;
    }

    public void setVencimento(int vencimento) {
        this.vencimento = vencimento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    
}
