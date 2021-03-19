package br.com.newstation.beans;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.newstation.daos.EstoqueDao;
import br.com.newstation.dominio.Estoque;

@Model
public class EstoqueEditarBean {

	@Inject
	private EstoqueDao eDao;
	
	@Transactional
	public void update(Estoque estoque) {
		eDao.update(estoque);
	}
	
}
