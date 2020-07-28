package bancodedados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import usuarios.Usuario;

public class BancoUsuarios {

    private Connection cn = Conectar.getConexao();
    private PreparedStatement ps;

    public ResultSet listarUsuarios(String busca) {
        try {
            String sql = SqlUsuarios.listar(busca);
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
            if(!listarUsuarios("").next()){
                existe = false;
            }
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
            existe = false;
        }
        return existe;
    }

    public ResultSet verificarLogin(String login, String senha) {
        try {
            String sql = SqlUsuarios.buscarLoginSenha(login, senha);
            Statement st = cn.createStatement();
            return st.executeQuery(sql);
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
            return null;
        }
    }

    public ResultSet buscarCodigo(String codigo) {
        try {
            String sql = SqlUsuarios.buscarCodigo(codigo);
            Statement st = cn.createStatement();
            return st.executeQuery(sql);
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
            return null;
        }
    }

    public String registrarUsuario(Usuario u) {
        String resultado = null;
        String sql = SqlUsuarios.REGISTRAR;
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, u.getCodigo_us());
            ps.setString(2, u.getNome_us().toUpperCase());
            ps.setString(3, u.getLogin_us().toUpperCase());
            ps.setString(4, u.getSexo_us());
            ps.setString(5, u.getTipo_us());
            ps.setString(6, u.getSenha());

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

    public String atualizarUsuario(Usuario u) {
        String resultado = null;
        String sql = SqlUsuarios.ATUALIZAR;
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, u.getNome_us().toUpperCase());
            ps.setString(2, u.getLogin_us().toUpperCase());
            ps.setString(3, u.getSexo_us());
            ps.setString(4, u.getTipo_us());
            ps.setString(5, u.getSenha());
            ps.setString(6, u.getCodigo_us());

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

    public ResultSet buscarMaiorCodigoUsuario() {
        String sql = SqlUsuarios.MAIOR_CODIGO_USUARIO;
        ResultSet rs = null;
        try {
            Statement st = cn.createStatement();
            rs = st.executeQuery(sql);
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
        }
        return rs;
    }

    public String exluirUsuario(String codigo) {
        String resultado = null;
        String sql = SqlUsuarios.EXCLUIR;
        try {
            
            if(!listarUsuarios("").next()){
                return "Nenhum usuário cadastrado!";
            }
            
            ps = cn.prepareStatement(sql);
            ps.setString(1, codigo);

            if (ps.executeUpdate() != 0) {
                resultado = "O usuário foi excluido com sucesso!";
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
        String sql = SqlUsuarios.EXCLUIR_TUDO;
        try {
            
            if(!listarUsuarios("").next()){
                return "Nenhum usuário cadastrado!";
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
