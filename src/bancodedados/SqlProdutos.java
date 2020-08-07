package bancodedados;

public class SqlProdutos {

    public static String listar(String busca) {
        return "SELECT * FROM produtos WHERE nome_pro like '%" + busca + "%' or codigo_pro like '%" + busca + "%' ORDER BY nome_pro";
    }

    public static String listar(String busca, String tipo) {
        return "SELECT * FROM produtos WHERE (nome_pro like '%" + busca + "%' or codigo_pro like '%" + busca + "%') and tipo_pro like '%" + tipo + "%' ORDER BY nome_pro";
    }

    public static String buscarCodigo(String codigo) {
        return "SELECT * FROM produtos WHERE codigo_pro = '" + codigo + "'";
    }

    public static String REGISTRAR = "INSERT INTO produtos(codigo_pro, tipo_pro, nome_pro, valor_pro) "
            + "VALUES(?,?,?,?)";

    public static String ATUALIZAR = "UPDATE produtos SET "
            + "tipo_pro=?, "
            + "nome_pro=?, "
            + "valor_pro=?, "
            + "quantidade_pro=? WHERE codigo_pro=?";

    public static String EXCLUIR = "DELETE FROM produtos WHERE codigo_pro = ?";
    public static String EXCLUIR_TUDO = "DELETE FROM produtos";
    public static String MAIOR_CODIGO_PRODUTO = "SELECT MAX(codigo_pro) FROM produtos";

}
