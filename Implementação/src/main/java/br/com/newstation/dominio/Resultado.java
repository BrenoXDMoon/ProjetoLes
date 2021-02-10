package br.com.newstation.dominio;

import java.util.ArrayList;
import java.util.List;

public class Resultado {

	private String mensagem;
	private EntidadeDominio entidade;
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
	
	public EntidadeDominio getEntidade() {
		return entidade;
	}
	
	public void setEntidade(EntidadeDominio entidade) {
		this.entidade = entidade;
	}
	
	public List<EntidadeDominio> getEntidades() {
		return entidades;
	}
	
	public void setEntidades(List<EntidadeDominio> entidades) {
		this.entidades = entidades;
	}
	
	public void add(EntidadeDominio ent) {
		entidades.add(ent);
	}
}
