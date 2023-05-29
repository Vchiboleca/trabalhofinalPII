package co.mz.hola.controllers;

/**
 *
 * @author Virgilio Chiboleca
 */
public class VariaveisDoSistema {
    
    public static String nomeDoUsuarioCadastrado;

    
    public static String empresaDoUsuarioCadastrado;
    public static String enderecoDoUsuarioCadastrado;
    public static String contactoDoUsuarioCadastrado;
    public static int idDaEmpresaDoUsuarioCadastrado;

    public static int getIdDaEmpresaDoUsuarioCadastrado() {
        return idDaEmpresaDoUsuarioCadastrado;
    }

    public static void setIdDaEmpresaDoUsuarioCadastrado(int idDaEmpresaDoUsuarioCadastrado) {
        VariaveisDoSistema.idDaEmpresaDoUsuarioCadastrado = idDaEmpresaDoUsuarioCadastrado;
    }
    
    public static String getNomeDoUsuarioCadastrado() {
        return nomeDoUsuarioCadastrado;
    }

    public static void setNomeDoUsuarioCadastrado(String nomeDoUsuarioCadastrado) {
        VariaveisDoSistema.nomeDoUsuarioCadastrado = nomeDoUsuarioCadastrado;
    }

    public static String getContactoDoUsuarioCadastrado() {
        return contactoDoUsuarioCadastrado;
    }

    public static void setContactoDoUsuarioCadastrado(String contactoDoUsuarioCadastrado) {
        VariaveisDoSistema.contactoDoUsuarioCadastrado = contactoDoUsuarioCadastrado;
    }

    public static String getEnderecoDoUsuarioCadastrado() {
        return enderecoDoUsuarioCadastrado;
    }

    public static void setEnderecoDoUsuarioCadastrado(String enderecoDoUsuarioCadastrado) {
        VariaveisDoSistema.enderecoDoUsuarioCadastrado = enderecoDoUsuarioCadastrado;
    }

    public VariaveisDoSistema() {
    }

    public String getEmpresaDoUsuarioCadastrado() {
        return empresaDoUsuarioCadastrado;
    }

    public static void setEmpresaDoUsuarioCadastrado(String nomeEmpresa) {
        empresaDoUsuarioCadastrado = nomeEmpresa;
    }
    
    
}
