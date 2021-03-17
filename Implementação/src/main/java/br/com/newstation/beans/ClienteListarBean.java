package br.com.newstation.beans;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;

import br.com.newstation.daos.ClienteDao;
import br.com.newstation.dominio.Cliente;
import br.com.newstation.dominio.Documento;

@Model
public class ClienteListarBean {

	private List<Cliente> clientes = new ArrayList<Cliente>();

	private Documento doc;

	public List<Cliente> getClientes() {

		ClienteDao dao = new ClienteDao();
		clientes = dao.listarSemCao();

		return clientes;
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

}
