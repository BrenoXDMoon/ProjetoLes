package br.com.newstation.fachada;

import java.util.ArrayList;
import java.util.List;

import br.com.newstation.daos.CartaoCreditoDao;
import br.com.newstation.daos.ClienteDao;
import br.com.newstation.dominio.EntidadeDominio;
import br.com.newstation.dominio.Resultado;
import br.com.newstation.strategies.IStrategy;
import br.com.newstation.strategies.ValidaCPF;
import br.com.newstation.strategies.ValidaExistenciaClientePorCPF;
import br.com.newstation.strategies.ValidaExistenciaPorEmail;
import br.com.newstation.strategies.ValidacaoEntidadeNula;

public class FachadaCartaoCredito implements IFachada {

	private CartaoCreditoDao dao = new CartaoCreditoDao();
	private StringBuilder sb = new StringBuilder();

	@Override
	public Resultado salvar(EntidadeDominio ent) {
		System.out.println("Entrou na fachada cartao salvar");

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
		System.out.println("Entrou na fachada cartao editar");
		Resultado resultado = new Resultado();

		if (sb.length() == 0) {
			resultado = dao.editar(ent);
		} else {
			resultado.setMensagem((sb.toString()));
		}

		return resultado;
	}

	@Override
	public Resultado excluir(EntidadeDominio ent) {
		System.out.println("Entrou na fachada cartao excluir");	
		Resultado resultado = new Resultado();
		resultado = dao.excluir(ent);
		return resultado;
	}

	@Override
	public Resultado listar(EntidadeDominio ent) {
		System.out.println("Entrou na fachada cartao Listar");	
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
