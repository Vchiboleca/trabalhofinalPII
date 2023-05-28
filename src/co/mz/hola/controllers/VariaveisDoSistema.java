package co.mz.hola.controllers;

/**
 *
 * @author Virgilio Chiboleca
 */
public class VariaveisDoSistema {
    
    public static String empresaDoUsuarioCadastrado;
    public static String enderecoDoUsuarioCadastrado;
    public static String contactoDoUsuarioCadastrado;

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
