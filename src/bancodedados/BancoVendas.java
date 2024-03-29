package bancodedados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import vendas.Venda;

public class BancoVendas {

    private Connection cn = Conectar.getConexao();
    private PreparedStatement ps;

    public ResultSet listarVendas(String busca) {
        try {
            String sql = SqlVendas.listar(busca);
            Statement st = cn.createStatement();
            return st.executeQuery(sql);
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
            return null;
        }
    }

    public ResultSet listarVendas(String busca, String data) {
        try {
            String sql = SqlVendas.listar(busca, data);
            Statement st = cn.createStatement();
            return st.executeQuery(sql);
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
            return null;
        }
    }

    public ResultSet buscarCodigo(String codigo) {
        try {
            String sql = SqlVendas.buscarNumero(codigo);
            Statement st = cn.createStatement();
            return st.executeQuery(sql);
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
            return null;
        }
    }
    
    public String registrarVenda(Venda v) {
        String resultado = null;
        String sql = SqlVendas.REGISTRAR;
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, v.getNumero_ven());
            ps.setString(2, v.getTotal_ven().replace(",", "."));
            ps.setString(3, v.getData_ven());
            ps.setString(4, v.getFormaPagamento_ven());
            ps.setString(5, String.valueOf(v.getEntrada_ven()));
            ps.setString(6, String.valueOf(v.getCliente_ven()));

            if (ps.executeUpdate() != 0) {
                resultado = "Registrado com sucesso!";
            } else {
                resultado = "Erro ao realizar o registro!";
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            resultado = "Erro ao realizar o registro!";
        }
        System.out.println(sql);
        return resultado;
    }

    public ResultSet buscarMaiorNumeroVenda() {
        String sql = SqlVendas.MAIOR_NUMERO_VENDA;
        ResultSet rs = null;
        try {
            Statement st = cn.createStatement();
            rs = st.executeQuery(sql);
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
        }
        return rs;
    }

    public boolean verificarExisteRegistro() {

        boolean existe = true;

        try {
            if (!listarVendas("").next()) {
                existe = false;
            }
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
        }
        return existe;
    }

    public String exluirVenda(String numero) {
        String resultado = null;
        String sql = SqlVendas.EXCLUIR;
        try {

            if (!listarVendas("").next()) {
                return "Nenhuma venda cadastrada!";
            }

            ps = cn.prepareStatement(sql);
            ps.setString(1, numero);

            if (ps.executeUpdate() != 0) {
                resultado = "A venda foi excluido com sucesso!";
            } else {
                resultado = "Erro ao excluir o registro!";
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            resultado = "Erro ao excluir o registro!";
        }
        System.out.println(sql);
        return resultado;
    }

    public String excluirTodos() {
        String resultado = null;
        String sql = SqlVendas.EXCLUIR_TUDO;
        try {

            if (!listarVendas("").next()) {
                return "Nenhum produto cadastrado!";
            }

            ps = cn.prepareStatement(sql);
            if (ps.executeUpdate() != 0) {
                resultado = "Todos os registros foram excluidos com sucesso!";
            } else {
                resultado = "Erro ao excluir todos os registros!";
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            resultado = "Erro ao excluir todos os registros!";
        }
        System.out.println(sql);
        return resultado;
    }
}
