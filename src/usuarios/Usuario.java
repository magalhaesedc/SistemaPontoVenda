
package usuarios;


public class Usuario {
    
    private String codigo_us;
    private String nome_us;
    private String login_us;
    private String sexo_us;
    private String tipo_us;
    private String senha;

    /**
     * @return the codigo_us
     */
    public String getCodigo_us() {
        return codigo_us;
    }

    /**
     * @param codigo_us the codigo_us to set
     */
    public void setCodigo_us(String codigo_us) {
        this.codigo_us = codigo_us;
    }

    /**
     * @return the nome_us
     */
    public String getNome_us() {
        return nome_us;
    }

    /**
     * @param nome_us the nome_us to set
     */
    public void setNome_us(String nome_us) {
        this.nome_us = nome_us;
    }
    
    
    public String getLogin_us() {
        return login_us;
    }

    
    public void setLogin_us(String login_us) {
        this.login_us = login_us;
    }

    /**
     * @return the sexo_us
     */
    public String getSexo_us() {
        return sexo_us;
    }

    /**
     * @param sexo_us the sexo_us to set
     */
    public void setSexo_us(String sexo_us) {
        this.sexo_us = sexo_us;
    }

    /**
     * @return the tipo_us
     */
    public String getTipo_us() {
        return tipo_us;
    }

    /**
     * @param tipo_us the tipo_us to set
     */
    public void setTipo_us(String tipo_us) {
        this.tipo_us = tipo_us;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    
}
