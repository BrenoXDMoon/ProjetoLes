package br.com.newstation.strategies;

import java.util.ArrayList;
import java.util.List;

import br.com.newstation.dominio.Cliente;
import br.com.newstation.dominio.Documento;
import br.com.newstation.dominio.EntidadeDominio;
import br.com.newstation.dominio.TIPO_DOCUMENTO;

public class ValidaExistenciaClientePorCPF implements IStrategy {

	@Override
	public String processar(EntidadeDominio ent) {

		Cliente cliente = (Cliente) ent;

		Boolean isCPF = false;

		Documento doc = new Documento();
		for (Documento d : cliente.getDocumentos()) {
			if (d.getTipoDocumento().equals(TIPO_DOCUMENTO.CPF)) {
				isCPF = true;
				doc = d;
			}
		}

		if (isCPF.equals(true)) {

			List<Cliente> ls = new ArrayList<Cliente>();

			if (ls.isEmpty()) {
				return null;
			} else {
				return "- Cliente já cadastrado!!";
			}

		} else {
			return null;
		}
	}
}
