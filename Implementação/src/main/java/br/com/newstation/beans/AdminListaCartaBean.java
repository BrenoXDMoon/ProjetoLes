package br.com.newstation.beans;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;

import br.com.newstation.command.ListarCommand;
import br.com.newstation.dominio.Carta;
import br.com.newstation.dominio.EntidadeDominio;
import br.com.newstation.dominio.RARIDADE;
import br.com.newstation.dominio.Resultado;

@Model
public class AdminListaCartaBean {

	ListarCommand cmd = new ListarCommand();

	public List<Carta> getCartasAdmin() {
		return converteLista(cmd.executar(new Carta()));
	}

	private List<Carta> converteLista(Resultado listar) {

		List<Carta> lista = new ArrayList<Carta>();

		for (EntidadeDominio e : listar.getEntidades()) {
			lista.add((Carta) e);
		}

		return lista;
	}

	public RARIDADE[] getRaridade() {
		return RARIDADE.values();
	}
}
