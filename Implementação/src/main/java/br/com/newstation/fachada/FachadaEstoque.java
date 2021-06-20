package br.com.newstation.fachada;

import java.util.ArrayList;
import java.util.List;

import br.com.newstation.daos.EstoqueDao;
import br.com.newstation.dominio.EntidadeDominio;
import br.com.newstation.dominio.Resultado;
import br.com.newstation.strategies.IStrategy;
import br.com.newstation.strategies.ValidacaoEntidadeNula;

public class FachadaEstoque implements IFachada {

	private EstoqueDao dao = new EstoqueDao();
	private StringBuilder sb = new StringBuilder();

	@Override
	public Resultado salvar(EntidadeDominio ent) {
		System.out.println("Entrou na fachada Estoque salvar");

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
		System.out.println("Entrou na fachada Estoque editar");
		Resultado resultado = new Resultado();

		List<IStrategy> regras = new ArrayList<IStrategy>();
		regras.add(new ValidacaoEntidadeNula());

		executarRegras(ent, regras);
		
		if (sb.length() == 0) {
			resultado = dao.editar(ent);
		} else {
			resultado.setMensagem((sb.toString()));
		}

		return resultado;
	}

	@Override
	public Resultado excluir(EntidadeDominio ent) {
		System.out.println("Entrou na fachada Estoque excluir");	
		Resultado resultado = new Resultado();
		resultado = dao.excluir(ent);
		return resultado;
	}

	@Override
	@Deprecated
	public Resultado listar(EntidadeDominio ent) {
		// TODO Auto-generated method stub
				return null;
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
