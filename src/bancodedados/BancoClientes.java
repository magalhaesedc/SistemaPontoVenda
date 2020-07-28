package bancodedados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import clientes.Cliente;

public class BancoClientes {

    private Connection cn = Conectar.getConexao();
    private PreparedStatement ps;

    public ResultSet listarClientes(String busca) {
        try {
            String sql = SqlClientes.listar(busca);
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
            if(!listarClientes("").next()){
                existe = false;
            }
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
            existe = false;
        }
        return existe;
    }

    public ResultSet buscarCodigo(String codigo) {
        try {
            String sql = SqlClientes.buscarCodigo(codigo);
            Statement st = cn.createStatement();
            return st.executeQuery(sql);
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
            return null;
        }
    }

    public String registrarCliente(Cliente c) {
        String resultado = null;
        String sql = SqlClientes.REGISTRAR;
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, c.getCodigo_cl());
            ps.setString(2, c.getNome_cl().toUpperCase());
            ps.setString(3, c.getSexo_cl());
            ps.setString(4, c.getCelular_cl());

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

    public String atualizarCliente(Cliente c) {
        String resultado = null;
        String sql = SqlClientes.ATUALIZAR;
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, c.getNome_cl().toUpperCase());
            ps.setString(2, c.getSexo_cl());
            ps.setString(3, c.getCelular_cl());
            ps.setString(4, c.getCodigo_cl());

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

    public ResultSet buscarMaiorCodigoCliente() {
        String sql = SqlClientes.MAIOR_CODIGO_CLIENTE;
        ResultSet rs = null;
        try {
            Statement st = cn.createStatement();
            rs = st.executeQuery(sql);
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
        }
        return rs;
    }

    public String exluirCliente(String codigo) {
        String resultado = null;
        String sql = SqlClientes.EXCLUIR;
        try {
            
            if(!listarClientes("").next()){
                return "Nenhum cliente cadastrado!";
            }
            
            ps = cn.prepareStatement(sql);
            ps.setString(1, codigo);

            if (ps.executeUpdate() != 0) {
                resultado = "O cliente foi excluido com sucesso!";
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
        String sql = SqlClientes.EXCLUIR_TUDO;
        try {
            
            if(!listarClientes("").next()){
                return "Nenhum cliente cadastrado!";
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
