
package PastaConexao;

import java.sql.*;

public class Conexao { 
    public static Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/merccontrol?useSSL=false&serverTimezone=UTC";
        String usuario = "root";
        String senha = "root";
        
        Connection conn = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conexão realizada com sucesso!");
            return conn;  // Retorna a conexão aberta
            
        } catch (ClassNotFoundException e) {
            System.out.println("Driver não encontrado!");
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            System.out.println("Erro ao conectar!");
            e.printStackTrace();
            return null;
        }
    }
    
 
    public static void testarConexao() {
        Connection conn = getConnection();
        if (conn != null) {
            try {
                System.out.println("Banco conectado: " + conn.getCatalog());
                System.out.println("Versão do MySQL: " + conn.getMetaData().getDatabaseProductVersion());
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                fecharConexao(conn);
            }
        }
    }
    
    public static void fecharConexao(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Conexão fechada!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}