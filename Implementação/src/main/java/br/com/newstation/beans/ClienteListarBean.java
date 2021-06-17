package br.com.newstation.beans;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;

import br.com.newstation.command.ListarCommand;
import br.com.newstation.dominio.Cliente;
import br.com.newstation.dominio.Documento;
import br.com.newstation.dominio.EntidadeDominio;
import br.com.newstation.dominio.Resultado;

@Model
public class ClienteListarBean {

	private List<Cliente> clientes = new ArrayList<Cliente>();

	private Documento doc;

	public List<Cliente> getClientes() {

		ListarCommand cmd = new ListarCommand();
		Resultado resultado = cmd.executar(new Cliente());
		List<Cliente> clientes = new ArrayList<Cliente>();
		
		for(EntidadeDominio e : resultado.getEntidades()) {
			clientes.add((Cliente) e);
		}

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
