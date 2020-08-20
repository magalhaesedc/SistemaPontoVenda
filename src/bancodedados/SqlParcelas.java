package bancodedados;

public class SqlParcelas {

    public static String listar(String busca) {
        return "SELECT * FROM parcelas WHERE codigo_venda= " + busca + " ORDER BY codigo_venda";
    }

    public static String listar(String busca, String data) {
        return "SELECT * FROM parcelas WHERE numero_ven like '%" + busca + "%' and data_ven like '%" + data + "%' ORDER BY data_ven";
    }

    public static String buscarNumero(String numero) {
        return "SELECT * FROM vendas WHERE numero_ven = '" + numero + "'";
    }
    
    public static String quantidadeTotalParcelas(String codigo_venda) {
        return "SELECT COUNT(id) FROM parcelas WHERE codigo_venda= " + codigo_venda;
    }
    
    public static String quantidadeParcelasPendentes(String codigo_venda) {
        return "SELECT COUNT(id) FROM parcelas WHERE status=0 and codigo_venda= " + codigo_venda;
    }
    
    
    
    public static String quantidadeParcelasPagas(String codigo_venda) {
        return "SELECT COUNT(id) FROM parcelas WHERE status=1 and codigo_venda= " + codigo_venda;
    }
    
    public static String valorPendente(String codigo_venda) {
        return "SELECT SUM(valor) FROM parcelas WHERE status=0 and codigo_venda= " + codigo_venda;
    }

    public static String REGISTRAR = "INSERT INTO parcelas(valor, numero, data_vencimento, data_pagamento, status, codigo_venda) "
            + "VALUES(?,?,?,?,?,?)";

    public static String EXCLUIR = "DELETE FROM parcelas WHERE id = ?";

    public static String EXCLUIR_TUDO = "DELETE FROM parcelas";

    public static String MAIOR_NUMERO_PARCELA = "SELECT MAX(id) FROM parcelas";

}
