package br.com.newstation.beans;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.newstation.daos.CupomDao;
import br.com.newstation.dominio.Cupom;
import br.com.newstation.dominio.TIPO_CUPOM;

@Model
public class CupomEditarBean {

	@Inject
	CupomDao dao;
	
	private Cupom cupom = new Cupom();
	
	private static Integer id;
	
	@Transactional
	public String editar() {
		cupom.setId(id);
		dao.editar(cupom);
		return "/admin/cupom/lista?faces-redirect=true";
	}
	
	public String redir(Cupom cupom) {
		id = cupom.getId();
		return "/admin/cupom/edit-form?faces-redirect=true";
	}
	
	public void carregaDetalhe() {
		
		this.cupom =  dao.buscarById(id);
	}
	
	public TIPO_CUPOM[] getTiposCupom() {
		return TIPO_CUPOM.values();
	}

	public Cupom getCupom() {
		return cupom;
	}

	public void setCupom(Cupom cupom) {
		this.cupom = cupom;
	}
	
}
