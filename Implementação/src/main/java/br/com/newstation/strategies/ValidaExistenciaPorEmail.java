package br.com.newstation.strategies;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.newstation.beans.ClienteListarBean;
import br.com.newstation.beans.ClienteSalvarBean;
import br.com.newstation.dominio.Cliente;
import br.com.newstation.dominio.EntidadeDominio;

public class ValidaExistenciaPorEmail implements IStrategy {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public String processar(EntidadeDominio ent) {
		
		ClienteListarBean clb = new ClienteListarBean();
		ClienteSalvarBean csb = new ClienteSalvarBean();
		
		Cliente cliente = (Cliente) ent;
		
		for(Cliente cli: clb.getClientes()) {
			if(cliente.getEmail().equals(cli.getEmail())) {
				csb.setEmailError(true);
	            return "Email já Cadastrado";
			}
		}
		return null;
	}
}
