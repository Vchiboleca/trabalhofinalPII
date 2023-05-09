package co.mz.hola.dal;

import java.sql.*;

/**
 *
 * @author Virgilio Chiboleca
 */
public class ModuloConexao {
   
    //Metodo responsavel por estabelecer a conexao com o banco de dados
    
    public static Connection conector(){
        
        java.sql.Connection conexao = null;
        //A linha abaixo chama o driver mysql para java
        
        String driver = "com.mysql.jdbc.Driver";
        //Informacoes do banco de dados
        
        String url = "jdbc:mysql://localhost:3306/dbinfox";
        String user = "root";
        String password = "";
        
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
