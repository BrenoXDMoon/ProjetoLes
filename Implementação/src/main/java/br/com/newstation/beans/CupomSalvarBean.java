package br.com.newstation.beans;

import java.util.UUID;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.newstation.daos.CupomDao;
import br.com.newstation.dominio.Cupom;
import br.com.newstation.dominio.TIPO_CUPOM;

@Model
public class CupomSalvarBean {

	@Inject
	CupomDao dao;
	
	private Cupom cupom = new Cupom();
	
	@Transactional
	public String salvar() {
		cupom.setCodigo(UUID.randomUUID().toString().split("-")[0]);
//		System.out.println(UUID.randomUUID().toString().split("-")[0]);
		cupom.setAtivo(true);
		dao.salvar(cupom);
		return null;
	}

	public Cupom getCupom() {
		return cupom;
	}

	public void setCupom(Cupom cupom) {
		this.cupom = cupom;
	}
	
	public TIPO_CUPOM[] getTiposCupom() {
		return TIPO_CUPOM.values();
	}
}
