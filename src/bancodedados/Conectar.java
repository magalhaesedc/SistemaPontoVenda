package bancodedados;

import controle.ControleMetodos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conectar {

    static ControleMetodos controleMetodos = new ControleMetodos();

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    //private static final String URL = "jdbc:mysql://localhost:3306/pontovenda?useTimezone=true&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
    //private static final String USER = "admin";
    //private static final String PASS = "admin";

    static public String banco = "";
    static public String senha = "";
    static public String servidor = "";
    static public String usuario = "";

//    public static Connection getConexao(String usuario, String senha, String banco, String servidor) {
//
//        String URL = "jdbc:mysql://" + servidor + "/" + banco + "?useTimezone=true&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
//
//        try {
//            Class.forName(DRIVER);
//            return DriverManager.getConnection(URL, usuario, senha);
//        } catch (ClassNotFoundException | SQLException ex) {
//            throw new RuntimeException("Erro na Conexão", ex);
//        }
//    }
    public static Connection getConexao() {

        try {
            ConfiguracoesBanco configuracoesBanco = controleMetodos.lerXML();
            String URL = "jdbc:mysql://" + configuracoesBanco.getServidor() + "/" + configuracoesBanco.getBanco() + "?useTimezone=true&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, configuracoesBanco.getUsuario(), configuracoesBanco.getSenha());
        } catch (Exception ex) {
            throw new RuntimeException("Erro na Conexão", ex);
        }
    }

    public static Connection testConexao(String usuario, String senha, String banco, String servidor) throws ClassNotFoundException, SQLException {

        String URL = "jdbc:mysql://" + servidor + "/" + banco + "?useTimezone=true&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
        Class.forName(DRIVER);
        return DriverManager.getConnection(URL, usuario, senha);

    }

    public static void closeConexao(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.err.println("Erro: " + ex);
            }
        }
    }

    public static void closeConexao(Connection conn, PreparedStatement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                System.err.println("Erro: " + ex);
            }
        }
        closeConexao(conn);
    }

    public static void closeConexao(Connection conn, PreparedStatement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                System.err.println("Erro: " + ex);
            }
        }
        closeConexao(conn, stmt);
    }
}
