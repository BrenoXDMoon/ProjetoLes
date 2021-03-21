package br.com.newstation.beans;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.newstation.daos.CupomDao;
import br.com.newstation.dominio.Cupom;

@Model
public class CupomListarBean {

	@Inject
	CupomDao dao;
	
	private List<Cupom> cupons = new ArrayList<Cupom>();
	
	@Transactional
	public String listar() {
		cupons = dao.listar();
		return null;
	}
	
	@Transactional
	public List<Cupom> getCupons() {
		return dao.listar();
	}

	public void setCupons(List<Cupom> cupons) {
		this.cupons = cupons;
	}
	
}
