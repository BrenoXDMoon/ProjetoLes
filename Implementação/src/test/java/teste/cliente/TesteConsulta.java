package teste.cliente;

import java.util.List;

import br.com.newstation.command.ListarCommand;
import br.com.newstation.dominio.Cliente;
import br.com.newstation.dominio.EntidadeDominio;

public class TesteConsulta {

	
	public static void main(String[] args) {
		ListarCommand cmd = new ListarCommand();
		
		List<EntidadeDominio> lista = cmd.executar(new Cliente()).getEntidades();
	}
}
