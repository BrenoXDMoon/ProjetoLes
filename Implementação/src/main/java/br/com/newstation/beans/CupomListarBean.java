package br.com.newstation.beans;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.newstation.daos.CupomDao;
import br.com.newstation.dominio.Cupom;
import br.com.newstation.dominio.EntidadeDominio;

@Model
public class CupomListarBean {

//	@Inject
	CupomDao dao = new CupomDao();
	
	private List<Cupom> cupons = new ArrayList<Cupom>();
	
	@Transactional
	public List<Cupom> listar() {
		
		cupons = converteListaEntidade(dao.listar(new EntidadeDominio()).getEntidades());
		return cupons;
	}
	
	@Transactional
	public List<Cupom> listarAtivos() {
		return dao.listarAtivos();
	}
	
	@Transactional
	public List<Cupom> getCupomByCliente(){
		LoginBean lb = new LoginBean();
		return dao.listarCuponsByCliente(lb.getId());
	}
	
	@Transactional
	public List<Cupom> getCuponsDesconto() {
		return dao.listarCuponsDesconto();
	}
	
	@Transactional
	public List<Cupom> getCuponsTroca() {
		return dao.listarCuponsTroca();
	}

	public void setCupons(List<Cupom> cupons) {
		this.cupons = cupons;
	}
	
	private List<Cupom>converteListaEntidade(List<EntidadeDominio> lst){
		List<Cupom> cupons = new ArrayList<Cupom>();
		for(EntidadeDominio e : lst) {
			Cupom c = (Cupom) e;
			cupons.add(c);
		}
		
		return cupons;
	}
}
