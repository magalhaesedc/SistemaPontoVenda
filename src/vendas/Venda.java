
package vendas;

public class Venda {
    
    private String numero_ven;
    private String total_ven;
    private String data_ven;
    private String formaPagamento_ven;
    private int parcelas_ven;
    private double entrada_ven;

    public String getNumero_ven() {
        return numero_ven;
    }

    public void setNumero_ven(String numero_ven) {
        this.numero_ven = numero_ven;
    }

    public String getTotal_ven() {
        return total_ven;
    }

    public void setTotal_ven(String total_ven) {
        this.total_ven = total_ven;
    }

    public String getData_ven() {
        return data_ven;
    }

    public void setData_ven(String data_ven) {
        this.data_ven = data_ven;
    }

    public String getFormaPagamento_ven() {
        return formaPagamento_ven;
    }

    public void setFormaPagamento_ven(String formaPagamento_ven) {
        this.formaPagamento_ven = formaPagamento_ven;
    }

    public int getParcelas_ven() {
        return parcelas_ven;
    }

    public void setParcelas_ven(int parcelas_ven) {
        this.parcelas_ven = parcelas_ven;
    }

    public double getEntrada_ven() {
        return entrada_ven;
    }

    public void setEntrada_ven(double entrada_ven) {
        this.entrada_ven = entrada_ven;
    }
    
}
