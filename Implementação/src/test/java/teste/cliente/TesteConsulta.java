package teste.cliente;

import java.util.List;

import br.com.newstation.dominio.Cliente;
import br.com.newstation.dominio.EntidadeDominio;
import br.com.newstation.vh.ListarCommand;

public class TesteConsulta {

	
	public static void main(String[] args) {
		ListarCommand cmd = new ListarCommand();
		
		List<EntidadeDominio> lista = cmd.executar(new Cliente()).getEntidades();
	}
}
