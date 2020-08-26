package bancodedados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import vendas.Parcela;

public class BancoParcelas {

    private Connection cn = Conectar.getConexao();
    private PreparedStatement ps;

    public ResultSet listarParcelas(String busca) {
        try {
            String sql = SqlParcelas.listar(busca);
            Statement st = cn.createStatement();
            return st.executeQuery(sql);
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
            return null;
        }
    }

    public String pagar(String dataPagamento, String codigoVenda, String dataVencimento) {
        String resultado = null;
        String sql = SqlParcelas.PAGAR;
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, dataPagamento);
            ps.setString(2, codigoVenda);
            ps.setString(3, dataVencimento);

            if (ps.executeUpdate() != 0) {
                resultado = "Pago com sucesso!";
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

    public String quantidadeTotalParcelas(String codigo_venda) {
        try {
            String sql = SqlParcelas.quantidadeTotalParcelas(codigo_venda);
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            rs.next();
            return rs.getString("COUNT(id)");
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
            return null;
        }
    }

    public String quantidadeParcelasPagas(String codigo_venda) {
        try {
            String sql = SqlParcelas.quantidadeParcelasPagas(codigo_venda);
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            rs.next();
            return rs.getString("COUNT(id)");
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
            return null;
        }
    }

    public String quantidadeParcelasPendentes(String codigo_venda) {
        try {
            String sql = SqlParcelas.quantidadeParcelasPendentes(codigo_venda);
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            rs.next();
            return rs.getString("COUNT(id)");
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
            return null;
        }
    }

    public String valorPendente(String codigo_venda) {
        try {
            String sql = SqlParcelas.valorPendente(codigo_venda);
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            rs.next();
            return rs.getString("SUM(valor)");
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
            return null;
        }
    }

    public String registrarParcela(Parcela p) {
        String resultado = null;
        String sql = SqlParcelas.REGISTRAR;
        try {
            ps = cn.prepareStatement(sql);
            ps.setDouble(1, p.getValor());
            ps.setInt(2, p.getNumero());
            ps.setString(3, p.getDataVencimento());
            ps.setString(4, p.getDataPagamento());
            ps.setDouble(5, p.getStatus());
            ps.setString(6, p.getCodigo_venda());

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
            if (!listarParcelas("").next()) {
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

            if (!listarParcelas("").next()) {
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

            if (!listarParcelas("").next()) {
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
