package br.com.newstation.beans;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.newstation.command.ListarCommand;
import br.com.newstation.daos.ClienteDao;
import br.com.newstation.dominio.Cliente;
import br.com.newstation.dominio.Documento;
import br.com.newstation.dominio.EntidadeDominio;
import br.com.newstation.dominio.Resultado;

@Model
public class ClienteListarBean {

	
	@Inject
	ClienteDao dao;
	
	private List<Cliente> clientes = new ArrayList<Cliente>();

	private String busca = "";
	
	private Documento doc;

	ListarCommand cmd = new ListarCommand();
	
	public List<Cliente> getClientes() {
		if(clientes.size() != 0) {
			return clientes;
		}
		if (getBusca().equals("")) {
			for(EntidadeDominio e : cmd.executar(new Cliente()).getEntidades()) {
				clientes.add((Cliente) e);
			}
		}
		return clientes;
	}

	public void filtraClientes() {

		this.clientes = dao.filtro(getBusca());
		if (this.clientes == null)
			getClientes();
	}

	
	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Documento getDoc() {
		return doc;
	}

	public void setDoc(Documento doc) {
		this.doc = doc;
	}

	public String getBusca() {
		return busca;
	}

	public void setBusca(String busca) {
		this.busca = busca;
	}

}
