package controle;

import bancodedados.BancoClientes;
import java.sql.SQLException;
import clientes.Cliente;


public class ControleCliente {
    
    BancoClientes bancoClientes = new BancoClientes();
    
    public String registrarCliente(String codigo, String nome, String sexo, String celular){
        
        if(codigo.equals("") || nome.equals("") || celular.equals("") || sexo.equals("SEXO") ){
            return "Preencha todos os dados";
        }
        try {
            if(bancoClientes.buscarCodigo(codigo).next()){
                return "O código \"" + codigo + "\" já existe.\nPor favor, escolha outro.";
            }
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
        }
        
        Cliente c = new Cliente();
        c.setCodigo_cl(codigo);
        c.setNome_cl(nome);
        c.setSexo_cl(sexo);
        c.setCelular_cl(celular);
        return bancoClientes.registrarCliente(c);
    }
    
    public String atualizarCliente(String codigo, String nome, String sexo, String celular){
        
        if(codigo.equals("") || nome.equals("") || celular.equals("") || sexo.equals("SEXO")){
            return "Preencha todos os dados";
        }
        
        Cliente c = new Cliente();
        c.setCodigo_cl(codigo);
        c.setNome_cl(nome);
        c.setSexo_cl(sexo);
        c.setCelular_cl(celular);
        return bancoClientes.atualizarCliente(c);
    }
    
}