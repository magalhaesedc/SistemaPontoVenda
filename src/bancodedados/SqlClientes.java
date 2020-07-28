package bancodedados;

public class SqlClientes {

    public static String listar(String busca) {
        return "SELECT * FROM clientes WHERE nome_cl like '%" + busca + "%' or codigo_cl like '%" + busca + "%' ORDER BY nome_cl";
    }

    public static String buscarCodigo(String codigo) {
        return "SELECT * FROM clientes WHERE codigo_cl = '" + codigo + "'";
    }

    public static String REGISTRAR = "INSERT INTO clientes(codigo_cl, nome_cl, sexo_cl, celular_cl) "
            + "VALUES(?,?,?,?)";
    
    public static String ATUALIZAR = "UPDATE clientes SET "
                + "nome_cl=?, "
                + "sexo_cl=?, "
                + "celular_cl=? WHERE codigo_cl=?";
    
    public static String EXCLUIR = "DELETE FROM clientes WHERE codigo_cl = ?";
    public static String EXCLUIR_TUDO = "DELETE FROM clientes";
    public static String MAIOR_CODIGO_CLIENTE = "SELECT MAX(codigo_cl) FROM clientes";
    
}
