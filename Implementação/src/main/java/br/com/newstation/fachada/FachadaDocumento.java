package br.com.newstation.fachada;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.newstation.daos.DocumentoDao;
import br.com.newstation.dominio.Cliente;
import br.com.newstation.dominio.Documento;
import br.com.newstation.dominio.EntidadeDominio;
import br.com.newstation.dominio.Resultado;
import br.com.newstation.strategies.IStrategy;
import br.com.newstation.strategies.ValidaCPF;
import br.com.newstation.strategies.ValidacaoEntidadeNula;

public class FachadaDocumento  implements IFachada {
	
	private DocumentoDao dao = new DocumentoDao();
	private StringBuilder sb = new StringBuilder();
	Cliente cli = new Cliente();
	Set<Documento> doc = new HashSet<Documento>();

	@Override
	public Resultado salvar(EntidadeDominio ent) {
		System.out.println("Entrou na fachada documento salvar");

		Resultado resultado = new Resultado();
		
		List<IStrategy> regras = new ArrayList<IStrategy>();
		regras.add(new ValidacaoEntidadeNula());

		executarRegras(ent, regras);

		if (sb.length() == 0) {
			resultado = dao.salvar(ent);
		} else {
			resultado.setMensagem((sb.toString()));
		}

		return resultado;
	}

	@Override
	public Resultado editar(EntidadeDominio ent) {
		System.out.println("Entrou na fachada documento editar");
		Resultado resultado = new Resultado();
		doc.add((Documento)ent);
		cli.setDocumentos(doc);
		
		List<IStrategy> regras = new ArrayList<IStrategy>();
		regras.add(new ValidaCPF());
		regras.add(new ValidacaoEntidadeNula());
		
		executarRegras(cli, regras);
		
		if (sb.length() == 0) {
			resultado = dao.editar(ent);
		} else {
			resultado.setMensagem((sb.toString()));
		}

		return resultado;
	}

	@Override
	public Resultado excluir(EntidadeDominio ent) {
		System.out.println("Entrou na fachada documento excluir");	
		Resultado resultado = new Resultado();
		resultado = dao.excluir(ent);
		return resultado;
	}

	@Override
	public Resultado listar(EntidadeDominio ent) {
		System.out.println("Entrou na fachada documento Listar");	
		Resultado resultado = new Resultado();
		resultado = dao.listar(ent);
		return resultado;
	}

	private void executarRegras(EntidadeDominio entidade, List<IStrategy> rnsEntidade) {
		for (IStrategy rn : rnsEntidade) {
			String msg = rn.processar(entidade);
			if (msg != null) {
				sb.append(msg);
			}
		}
	}

}
