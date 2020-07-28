/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientes;

/**
 *
 * @author edson
 */
public class Cliente {
    
    private String codigo_cl;
    private String nome_cl;
    private String sexo_cl;
    private String celular_cl;

    public String getCodigo_cl() {
        return codigo_cl;
    }

    public void setCodigo_cl(String codigo_cl) {
        this.codigo_cl = codigo_cl;
    }

    public String getNome_cl() {
        return nome_cl;
    }

    public void setNome_cl(String nome_cl) {
        this.nome_cl = nome_cl;
    }

    public String getSexo_cl() {
        return sexo_cl;
    }

    public void setSexo_cl(String sexo_cl) {
        this.sexo_cl = sexo_cl;
    }

    public String getCelular_cl() {
        return celular_cl;
    }

    public void setCelular_cl(String celular_cl) {
        this.celular_cl = celular_cl;
    }
    
}
