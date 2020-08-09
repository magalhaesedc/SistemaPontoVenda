package controle;

import bancodedados.BancoProdutos;
import java.sql.ResultSet;
import java.sql.SQLException;
import produtos.Produto;


public class ControleProduto {
    
    BancoProdutos bancoProdutos = new BancoProdutos();
    
    public String registrarProduto(String codigo, String tipo, String nome, String valor){
        
        if(codigo.equals("") || nome.equals("") || tipo.equals("TIPO PRODUTO") || valor.equals("")){
            return "Preencha todos os dados";
        }
        try {
            if(bancoProdutos.buscarCodigo(codigo).next()){
                return "O código \"" + codigo + "\" já existe.\nPor favor, escolha outro.";
            }
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
        }
        
        Produto p = new Produto();
        p.setCodigo_pro(codigo);
        p.setTipo_pro(tipo);
        p.setNome_pro(nome);
        p.setValor_pro(valor);
        return bancoProdutos.registrarProduto(p);
    }
    
    public String atualizarProduto(String codigo, String tipo, String nome, String valor){
        
        if(codigo.equals("") || nome.equals("") || tipo.equals("TIPO PRODUTO") || valor.equals("")){
            return "Preencha todos os dados";
        }

        Produto p = new Produto();
        p.setCodigo_pro(codigo);
        p.setTipo_pro(tipo);
        p.setNome_pro(nome);
        p.setValor_pro(valor);
        return bancoProdutos.atualizarProduto(p);
    }
    
    public String removerQuantidade(String codigo_produto, String quantidade) {
        try {
            ResultSet protudoAtual = bancoProdutos.buscarCodigo(codigo_produto);
            
            if(protudoAtual.next()){
                int quantidadeAtual = protudoAtual.getInt("quantidade_pro");
                int quantidadeRemover = Integer.parseInt(quantidade);
                Produto p = new Produto();
                p.setCodigo_pro(protudoAtual.getString("codigo_pro"));
                p.setNome_pro(protudoAtual.getString("nome_pro"));
                p.setTipo_pro(protudoAtual.getString("tipo_pro"));
                p.setValor_pro(protudoAtual.getString("valor_pro"));              
                p.setQuantidade_pro(String.valueOf(quantidadeAtual - quantidadeRemover));
                return bancoProdutos.atualizarProduto(p);
            }else{
                return null;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
}