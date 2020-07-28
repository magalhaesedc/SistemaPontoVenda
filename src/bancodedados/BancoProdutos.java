package bancodedados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import produtos.Produto;

public class BancoProdutos {

    private Connection cn = Conectar.getConexao();
    private PreparedStatement ps;

    public ResultSet listarProdutos(String busca) {
        try {
            String sql = SqlProdutos.listar(busca);
            Statement st = cn.createStatement();
            return st.executeQuery(sql);
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
            return null;
        }
    }
    
    public boolean verificarExisteRegistro() {
        
        boolean existe = true;
        
        try {
            if(!listarProdutos("").next()){
                existe = false;
            }
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
            existe = false;
        }
        return existe;
    }
    
    public ResultSet listarProdutos(String busca, String tipo) {
        try {
            String sql = SqlProdutos.listar(busca, tipo);
            Statement st = cn.createStatement();
            return st.executeQuery(sql);
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
            return null;
        }
    }

    public ResultSet buscarCodigo(String codigo) {
        try {
            String sql = SqlProdutos.buscarCodigo(codigo);
            Statement st = cn.createStatement();
            return st.executeQuery(sql);
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
            return null;
        }
    }

    public String registrarProduto(Produto p) {
        String resultado = null;
        String sql = SqlProdutos.REGISTRAR;
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, p.getCodigo_pro());
            ps.setString(2, p.getTipo_pro());
            ps.setString(3, p.getNome_pro().toUpperCase());
            ps.setString(4, p.getValor_pro().replace(",", "."));
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

    public String atualizarProduto(Produto p) {
        String resultado = null;
        String sql = SqlProdutos.ATUALIZAR;
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, p.getTipo_pro());
            ps.setString(2, p.getNome_pro().toUpperCase());
            ps.setString(3, p.getValor_pro().replace(",", "."));
            ps.setString(4, p.getCodigo_pro());

            if (ps.executeUpdate() != 0) {
                resultado = "Atualizado com sucesso!";
            } else {
                resultado = "Erro ao atualizar o registro!";
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            resultado = "Erro ao atualizar o registro!";
        }
        System.out.println(sql);
        return resultado;
    }

    public ResultSet buscarMaiorCodigoProduto() {
        String sql = SqlProdutos.MAIOR_CODIGO_PRODUTO;
        ResultSet rs = null;
        try {
            Statement st = cn.createStatement();
            rs = st.executeQuery(sql);
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
        }
        return rs;
    }

    public String exluirProduto(String codigo) {
        String resultado = null;
        String sql = SqlProdutos.EXCLUIR;
        try {
            
            if(!listarProdutos("").next()){
                return "Nenhum produto cadastrado!";
            }
            
            ps = cn.prepareStatement(sql);
            ps.setString(1, codigo);

            if (ps.executeUpdate() != 0) {
                resultado = "O produto foi excluido com sucesso!";
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

    public String exluirTodos() {
        String resultado = null;
        String sql = SqlProdutos.EXCLUIR_TUDO;
        try {
            
            if(!listarProdutos("").next()){
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
