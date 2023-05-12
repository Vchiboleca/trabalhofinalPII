package co.mz.hola.dal;

import java.sql.*;

/**
 *
 * @author Virgilio Chiboleca
 */
public class ModuloConexao {
   
    //Metodo responsavel por estabelecer a conexao com o banco de dados
    
    public static Connection conector(){
        
        Connection conexao = null;
        //A linha abaixo chama o driver mysql para java
        
        String driver = "com.mysql.cj.jdbc.Driver";
        //Informacoes do banco de dados
        
        /*
        String url = "jdbc:mysql://containers-us-west-160.railway.app:6812/railway";
        String user = "root";
        String password = "MQ4BFiuZw639ihS7Np0g"; */
        
        String url = "jdbc:mysql://dbhola.ct7kledrwnvs.us-east-1.rds.amazonaws.com:3306/dbhola";
        String user = "root";
        String password = "D7WaRlyKN2ZJFyigWG1J"; 
        
        /*Modulo de Conexao Local - Mais rapido para testes
        String url = "jdbc:mysql://localhost:3306/dbhola";
        String user = "root";
        String password = ""; */
        
        //Estabelece a conexao com o banco de dados
        
            try {
                Class.forName(driver);
                conexao = DriverManager.getConnection(url, user, password);
                return conexao;
            } catch (Exception e) {
                return null;
            }
        
    }
}
