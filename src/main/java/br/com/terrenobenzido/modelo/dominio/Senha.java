package br.com.terrenobenzido.modelo.dominio;

public class Senha extends EntidadeDominio{

	 private String senhaTemp;
	    private String senha;
	    private String confirmaSenha;

	    public Senha(String senhaTemp, String senha, String confirmaSenha) {
	        this.senhaTemp = senhaTemp;
	        this.senha = senha;
	        this.confirmaSenha = confirmaSenha;
	    }

	    public Senha(String senha){
	        this.senha = senha;
	    }
	    
	    public Senha(){
	        
	    }
	    
	    public String getSenhaTemp() {
	        return senhaTemp;
	    }

	    public void setSenhaTemp(String senhaTemp) {
	        this.senhaTemp = senhaTemp;
	    }

	    public String getSenha() {
	        return senha;
	    }

	    public void setSenha(String senha) {
	        this.senha = senha;
	    }

	    public String getConfirmaSenha() {
	        return confirmaSenha;
	    }

	    public void setConfirmaSenha(String confirmaSenha) {
	        this.confirmaSenha = confirmaSenha;
	    }
}
