package br.com.newstation.vh;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;

import br.com.newstation.dominio.Cliente;
import br.com.newstation.dominio.EntidadeDominio;

@Model
public class ClienteListarBean {

	private List<Cliente> clientes = new ArrayList<Cliente>();
	
	public List<Cliente> listar(){
		
		Cliente cliente = new Cliente();
		
		ListarCommand cmd = new ListarCommand();
		
		converteLista(cmd.executar(cliente).getEntidades());
		
		return clientes;
	}

	public List<Cliente> getClientes() {
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
	
}
