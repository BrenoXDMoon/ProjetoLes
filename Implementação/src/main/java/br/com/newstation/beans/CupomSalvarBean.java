package br.com.newstation.beans;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.newstation.daos.CupomDao;
import br.com.newstation.dominio.Cupom;

@Model
public class CupomSalvarBean {

	@Inject
	CupomDao dao;
	
	private Cupom cupom = new Cupom();
	
	@Transactional
	public String salvar() {
		dao.salvar(cupom);
		return null;
	}

	public Cupom getCupom() {
		return cupom;
	}

	public void setCupom(Cupom cupom) {
		this.cupom = cupom;
	}
}
