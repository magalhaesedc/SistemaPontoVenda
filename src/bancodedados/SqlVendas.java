package bancodedados;

public class SqlVendas {

    public static String listar(String busca) {
        return "SELECT * FROM vendas WHERE numero_ven like '%" + busca + "%' or data_ven like '%" + busca + "%' ORDER BY data_ven";
    }
    
    public static String listar(String busca, String data) {
        return "SELECT * FROM vendas WHERE numero_ven like '%" + busca + "%' and data_ven like '%" + data + "%' ORDER BY data_ven";
    }

    public static String buscarNumero(String numero) {
        return "SELECT * FROM vendas WHERE numero_ven = " + numero;
    }
    
    public static String REGISTRAR = "INSERT INTO vendas(numero_ven, total_ven, data_ven, forma_pagamento_ven, entrada, cod_cliente_ven) "
            + "VALUES(?,?,?,?,?,?)";
    
    public static String EXCLUIR = "DELETE FROM vendas WHERE numero_ven = ?";
    
    public static String EXCLUIR_TUDO = "DELETE FROM vendas";

    public static String MAIOR_NUMERO_VENDA = "SELECT MAX(numero_ven) FROM vendas";
    
}
