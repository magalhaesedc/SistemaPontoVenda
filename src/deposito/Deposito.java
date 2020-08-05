
package deposito;

public class Deposito {
    
    private String codigo_produto;
    private String codigo_venda;
    private String quantidade;

    public String getCodigo_produto() {
        return codigo_produto;
    }

    public void setCodigo_produto(String codigo_produto) {
        this.codigo_produto = codigo_produto;
    }

    public String getCodigo_venda() {
        return codigo_venda;
    }

    public void setCodigo_venda(String codigo_venda) {
        this.codigo_venda = codigo_venda;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }
    
}
