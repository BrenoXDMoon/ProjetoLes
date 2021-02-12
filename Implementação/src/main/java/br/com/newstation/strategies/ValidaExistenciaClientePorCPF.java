package br.com.newstation.strategies;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.newstation.dominio.Cliente;
import br.com.newstation.dominio.EntidadeDominio;

public class ValidaExistenciaClientePorCPF implements IStrategy {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public String processar(EntidadeDominio ent) {
		Cliente cli = (Cliente) ent;
		String jpql = "select c from Cliente c where c.cpf = :cpf";
		
		List<Cliente> ls = new ArrayList<Cliente>(); //(List<Cliente>) manager.createQuery(jpql, Cliente.class).setParameter("cpf", cli.getDocumentos().get(0));
		
		if(ls.isEmpty()) {
			return null;
		}else {
			return "- Cliente já cadastrado!!";
		}
	}
}
