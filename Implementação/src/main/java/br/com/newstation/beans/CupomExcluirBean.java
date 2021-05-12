package br.com.newstation.beans;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.newstation.daos.CupomDao;
import br.com.newstation.dominio.Cupom;
import br.com.newstation.infra.Log;

@Model
public class CupomExcluirBean {

	@Inject
	CupomDao dao;
	
	private Cupom cupom = new Cupom();
	
	@Transactional
	public String excluir(Cupom cup) {
		Log.salvar("Alteração","admin");
		dao.excluir(cup);
		
		return "/admin/cupom/lista?faces-redirect=true";
	}

	public Cupom getCupom() {
		return cupom;
	}

	public void setCupom(Cupom cupom) {
		this.cupom = cupom;
	}
	
}
