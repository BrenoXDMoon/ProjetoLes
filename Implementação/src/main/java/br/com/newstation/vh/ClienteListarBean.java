package br.com.newstation.vh;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;

import br.com.newstation.command.ListarCommand;

import br.com.newstation.dominio.Cliente;
import br.com.newstation.dominio.Documento;
import br.com.newstation.dominio.EntidadeDominio;

@Model
public class ClienteListarBean {

	private List<Cliente> clientes = new ArrayList<Cliente>();

	private Documento doc;
	
	public List<Cliente> getClientes() {
		Cliente cliente = new Cliente();
		
		ListarCommand cmd = new ListarCommand();
		
		converteLista(cmd.executar(cliente).getEntidades());
		cliente = clientes.get(0);
		doc =  (Documento) cliente.getDocumentos().toArray()[0];
		
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	
	private void converteLista(List<EntidadeDominio> lista){
		
		for(EntidadeDominio e : lista) {
			Cliente c = (Cliente) e;
			
			this.clientes.add(c);
			
		}
	}

	public Documento getDoc() {
		return doc;
	}

	public void setDoc(Documento doc) {
		this.doc = doc;
	}
	
}
