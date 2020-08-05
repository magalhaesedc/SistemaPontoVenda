package bancodedados;

public class SqlDeposito {

    public static String listar(String busca) {
        return "SELECT * FROM vendas WHERE numero_ven like '%" + busca + "%' or data_ven like '%" + busca + "%' ORDER BY data_ven";
    }
    
    public static String listar(String busca, String data) {
        return "SELECT * FROM vendas WHERE numero_ven like '%" + busca + "%' and data_ven like '%" + data + "%' ORDER BY data_ven";
    }

    public static String buscarNumero(String numero) {
        return "SELECT * FROM vendas WHERE numero_ven = '" + numero + "'";
    }
    
    public static String REGISTRAR = "INSERT INTO deposito(codigo_produto, codigo_venda, quantidade) "
            + "VALUES(?,?,?)";
    
    public static String EXCLUIR = "DELETE FROM deposito WHERE numero_ven = ?";
    
    public static String EXCLUIR_TUDO = "DELETE FROM deposito";

    public static String MAIOR_NUMERO_VENDA = "SELECT MAX(id) FROM deposito";
    
}
