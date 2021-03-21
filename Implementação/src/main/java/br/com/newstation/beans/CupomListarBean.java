package br.com.newstation.beans;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.newstation.daos.CupomDao;
import br.com.newstation.dominio.Cupom;

public class CupomListarBean {

	@Inject
	CupomDao dao;
	
	private List<Cupom> cupons = new ArrayList<Cupom>();
	
	@Transactional
	public String listar() {
		return null;
	}

	public List<Cupom> getCupons() {
		return cupons;
	}

	public void setCupons(List<Cupom> cupons) {
		this.cupons = cupons;
	}
	
}
