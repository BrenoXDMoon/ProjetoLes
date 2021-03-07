package br.com.newstation.beans;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.newstation.daos.EnderecoDao;
import br.com.newstation.dominio.Endereco;
import br.com.newstation.dominio.TIPO_ENDERECO;

@Model
public class EnderecoBean {

	private Endereco endereco = new Endereco();
	
	@Inject
	private EnderecoDao dao;

	@Transactional
	public String salvar(){
		
		try {
			
			dao.salvar(endereco);
			
			return "cliente/perfil?faces-redirect=true";
			
		}catch (Exception e) {
			
			return "cliente/endereco/form?faces-redirect=true";
			
		}
	}
	
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public TIPO_ENDERECO[] getTipos() {
		return TIPO_ENDERECO.values();
	}
	
}
