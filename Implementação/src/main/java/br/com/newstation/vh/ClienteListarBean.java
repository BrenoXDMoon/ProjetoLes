package br.com.newstation.vh;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.transaction.Transactional;

import br.com.newstation.command.ListarCommand;
import br.com.newstation.daos.ClienteDao;
import br.com.newstation.dominio.Cliente;
import br.com.newstation.dominio.Documento;
import br.com.newstation.dominio.EntidadeDominio;

@Model
public class ClienteListarBean {

	private List<Cliente> clientes = new ArrayList<Cliente>();

	private Documento doc;
	
	public List<Cliente> getClientes() {
		
//		Cliente cliente = new Cliente();
//		
//		ListarCommand cmd = new ListarCommand();
//		
//		for(EntidadeDominio e : cmd.executar(cliente).getEntidades()) {
//			
//			clientes.add((Cliente) e);
//		}
//		
//		doc =  (Documento) clientes.get(0).getDocumentos().toArray()[0];
		
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
