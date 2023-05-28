package co.mz.hola.controllers;

/**
 *
 * @author Virgilio Chiboleca
 */
public class Empresa {
    private int id;
    private String nomeDaEmpresa;
    private String nuitDaEmpresa;
    private String enderecoDaEmpresa;
    private String contactoDaEmpresa;
    private String emailDaEmpresa;
    private String nomeDoResponsavelDaEmpresa;
    private String contactoDoResponsavelDaEmpresa;
    private String ramoDaEmpresa;
    private String sectoDaEmpresar;
    private int numFuncionariosDaEmpresa;

    public Empresa(int id, String nomeDaEmpresa, String nuitDaEmpresa, String enderecoDaEmpresa, String contactoDaEmpresa, String emailDaEmpresa, String nomeDoResponsavelDaEmpresa, String contactoDoResponsavelDaEmpresa, String ramoDaEmpresa, String sectoDaEmpresar, int numFuncionariosDaEmpresa) {
        this.id = id;
        this.nomeDaEmpresa = nomeDaEmpresa;
        this.nuitDaEmpresa = nuitDaEmpresa;
        this.enderecoDaEmpresa = enderecoDaEmpresa;
        this.contactoDaEmpresa = contactoDaEmpresa;
        this.emailDaEmpresa = emailDaEmpresa;
        this.nomeDoResponsavelDaEmpresa = nomeDoResponsavelDaEmpresa;
        this.contactoDoResponsavelDaEmpresa = contactoDoResponsavelDaEmpresa;
        this.ramoDaEmpresa = ramoDaEmpresa;
        this.sectoDaEmpresar = sectoDaEmpresar;
        this.numFuncionariosDaEmpresa = numFuncionariosDaEmpresa;
    }
    public Empresa(int id, String nomeDaEmpresa, String nuitDaEmpresa, String contactoDaEmpresa, String enderecoDaEmpresa){
        this.id = id;
        this.nomeDaEmpresa = nomeDaEmpresa;
        this.nuitDaEmpresa = nuitDaEmpresa;
        this.contactoDaEmpresa = contactoDaEmpresa;
        this.enderecoDaEmpresa = enderecoDaEmpresa;
    }

    public Empresa(String nomeDaEmpresa) {
        this.nomeDaEmpresa = nomeDaEmpresa;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeDaEmpresa() {
        return nomeDaEmpresa;
    }

    public void setNomeDaEmpresa(String nomeDaEmpresa) {
        this.nomeDaEmpresa = nomeDaEmpresa;
    }

    public String getNuitDaEmpresa() {
        return nuitDaEmpresa;
    }

    public void setNuitDaEmpresa(String nuitDaEmpresa) {
        this.nuitDaEmpresa = nuitDaEmpresa;
    }

    public String getEnderecoDaEmpresa() {
        return enderecoDaEmpresa;
    }

    public void setEnderecoDaEmpresa(String enderecoDaEmpresa) {
        this.enderecoDaEmpresa = enderecoDaEmpresa;
    }

    public String getContactoDaEmpresa() {
        return contactoDaEmpresa;
    }

    public void setContactoDaEmpresa(String contactoDaEmpresa) {
        this.contactoDaEmpresa = contactoDaEmpresa;
    }

    public String getEmailDaEmpresa() {
        return emailDaEmpresa;
    }

    public void setEmailDaEmpresa(String emailDaEmpresa) {
        this.emailDaEmpresa = emailDaEmpresa;
    }

    public String getNomeDoResponsavelDaEmpresa() {
        return nomeDoResponsavelDaEmpresa;
    }

    public void setNomeDoResponsavelDaEmpresa(String nomeDoResponsavelDaEmpresa) {
        this.nomeDoResponsavelDaEmpresa = nomeDoResponsavelDaEmpresa;
    }

    public String getContactoDoResponsavelDaEmpresa() {
        return contactoDoResponsavelDaEmpresa;
    }

    public void setContactoDoResponsavelDaEmpresa(String contactoDoResponsavelDaEmpresa) {
        this.contactoDoResponsavelDaEmpresa = contactoDoResponsavelDaEmpresa;
    }

    public String getRamoDaEmpresa() {
        return ramoDaEmpresa;
    }

    public void setRamoDaEmpresa(String ramoDaEmpresa) {
        this.ramoDaEmpresa = ramoDaEmpresa;
    }

    public String getSectoDaEmpresar() {
        return sectoDaEmpresar;
    }

    public void setSectoDaEmpresar(String sectoDaEmpresar) {
        this.sectoDaEmpresar = sectoDaEmpresar;
    }

    public int getNumFuncionariosDaEmpresa() {
        return numFuncionariosDaEmpresa;
    }

    public void setNumFuncionariosDaEmpresa(int numFuncionariosDaEmpresa) {
        this.numFuncionariosDaEmpresa = numFuncionariosDaEmpresa;
    }
}

