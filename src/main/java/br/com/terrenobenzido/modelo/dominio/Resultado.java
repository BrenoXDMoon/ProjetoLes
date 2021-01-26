package br.com.terrenobenzido.modelo.dominio;

import java.util.ArrayList;
import java.util.List;


public class Resultado {

	private String mensagem;
	private List<EntidadeDominio> entidades;
	
	public Resultado() {
		entidades = new ArrayList<EntidadeDominio>();
	}
	
	public String getMensagem() {
		return mensagem;
	}
	
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	public List<EntidadeDominio> getEntidades() {
		return entidades;
	}
	
	public void setEntidades(List<EntidadeDominio> entidades) {
		this.entidades = entidades;
	}	
	
	public List<EntidadeDominio> add(EntidadeDominio entidade){
		
		entidades.add(entidade);
			
		return entidades;
	}	
}
