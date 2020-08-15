package bancodedados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import deposito.Deposito;

public class BancoDeposito {

    private Connection cn = Conectar.getConexao();
    private PreparedStatement ps;

    public ResultSet listarDeposito(String busca) {
        try {
            String sql = SqlDeposito.listar(busca);
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

    public String registrarDeposito(Deposito d) {
        String resultado = null;
        String sql = SqlDeposito.REGISTRAR;
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, d.getCodigo_produto());
            ps.setString(2, d.getCodigo_venda());
            ps.setString(3, d.getQuantidade());

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
            if (!listarDeposito("").next()) {
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

            if (!listarDeposito("").next()) {
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

            if (!listarDeposito("").next()) {
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
