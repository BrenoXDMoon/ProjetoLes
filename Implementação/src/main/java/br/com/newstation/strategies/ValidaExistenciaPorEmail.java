package br.com.newstation.strategies;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.newstation.beans.ClienteSalvarBean;
import br.com.newstation.daos.ClienteDao;
import br.com.newstation.dominio.Cliente;
import br.com.newstation.dominio.EntidadeDominio;

public class ValidaExistenciaPorEmail implements IStrategy {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public String processar(EntidadeDominio ent) {
		
		ClienteDao dao = new ClienteDao();
		ClienteSalvarBean csb = new ClienteSalvarBean();
		Cliente cliente = (Cliente) ent;
		
		for(Cliente cli: dao.listarSemCao()) {
			if(cliente.getEmail().equals(cli.getEmail())) {
				csb.setEmailError(true);
				System.out.println("Email já Cadastrado");
	            return "Email já Cadastrado";
			}
		}
		System.out.println("EMAIL VALIDADO");
		return null;
	}
}
