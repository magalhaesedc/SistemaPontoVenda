package bancodedados;

public class SqlUsuarios {

    public static String listar(String busca) {
        return "SELECT * FROM usuarios WHERE nome_us like '%" + busca + "%' or codigo_us like '%" + busca + "%' ORDER BY nome_us";
    }

    public static String buscarNomeSenha(String nome, String senha) {
        return "SELECT * FROM usuarios WHERE nome_us = '" + nome + "' and senha = '" + senha + "'";
    }
    
    public static String buscarLoginSenha(String login, String senha) {
        return "SELECT * FROM usuarios WHERE login_us = '" + login + "' and senha = '" + senha + "'";
    }

    public static String buscarCodigo(String codigo) {
        return "SELECT * FROM usuarios WHERE codigo_us = '" + codigo + "'";
    }

    public static String REGISTRAR = "INSERT INTO usuarios(codigo_us, nome_us, login_us, sexo_us, tipo_us, senha) "
            + "VALUES(?,?,?,?,?,?)";
    
    public static String ATUALIZAR = "UPDATE usuarios SET "
                + "nome_us=?, "
                + "login_us=?, "
                + "sexo_us=?, "
                + "tipo_us=?, "
                + "senha=? WHERE codigo_us=?";
    
    public static String EXCLUIR = "DELETE FROM usuarios WHERE codigo_us = ?";
    public static String EXCLUIR_TUDO = "DELETE FROM usuarios";
    public static String MAIOR_CODIGO_USUARIO = "SELECT MAX(codigo_us) FROM usuarios";
    
}
