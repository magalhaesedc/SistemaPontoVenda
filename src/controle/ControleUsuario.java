package controle;

import bancodedados.BancoUsuarios;
import java.sql.SQLException;
import usuarios.Usuario;


public class ControleUsuario {
    
    BancoUsuarios bancoUsuarios = new BancoUsuarios();
    
    public String registrarUsuario(String codigo, String nome, String login, String sexo, String tipo, String senha){
        
        if(codigo.equals("") || nome.equals("") || login.equals("") || sexo.equals("SEXO") || tipo.equals("TIPO USUÁRIO") || senha.equals("")){
            return "Preencha todos os dados";
        }
        try {
            if(bancoUsuarios.buscarCodigo(codigo).next()){
                return "O código \"" + codigo + "\" já existe.\nPor favor, escolha outro.";
            }
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
        }
        
        Usuario u = new Usuario();
        u.setCodigo_us(codigo);
        u.setNome_us(nome);
        u.setLogin_us(login);
        u.setSexo_us(sexo);
        u.setTipo_us(tipo);
        u.setSenha(senha);
        return bancoUsuarios.registrarUsuario(u);
    }
    
    public String atualizarUsuario(String codigo, String nome, String login, String sexo, String tipo, String senha){
        
        if(codigo.equals("") || nome.equals("") || login.equals("") || sexo.equals("SEXO") || tipo.equals("TIPO USUÁRIO") || senha.equals("")){
            return "Preencha todos os dados";
        }
        
        Usuario u = new Usuario();
        u.setCodigo_us(codigo);
        u.setNome_us(nome);
        u.setLogin_us(login);
        u.setSexo_us(sexo);
        u.setTipo_us(tipo);
        u.setSenha(senha);
        return bancoUsuarios.atualizarUsuario(u);
    }
    
}