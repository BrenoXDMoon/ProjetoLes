package br.com.newstation.fachada;

import java.util.ArrayList;
import java.util.List;

import br.com.newstation.daos.CartaDao;
import br.com.newstation.daos.PedidoDao;
import br.com.newstation.dominio.EntidadeDominio;
import br.com.newstation.dominio.Resultado;
import br.com.newstation.strategies.IStrategy;
import br.com.newstation.strategies.ValidaCupomDescontoPedido;
import br.com.newstation.strategies.ValidaEstoque;
import br.com.newstation.strategies.ValidaQuantidadeProdutos;
import br.com.newstation.strategies.ValidacaoEntidadeNula;

public class FachadaPedido  implements IFachada {

	private PedidoDao dao = new PedidoDao();
	private StringBuilder sb = new StringBuilder();
	
	@Override
	public Resultado salvar(EntidadeDominio ent) {
		System.out.println("Entrou na fachada Pedido salvar");

		Resultado resultado = new Resultado();

		List<IStrategy> regras = new ArrayList<IStrategy>();
		regras.add(new ValidacaoEntidadeNula());
		regras.add(new ValidaQuantidadeProdutos());

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
		System.out.println("Entrou na fachada Pedido editar");
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
		System.out.println("Entrou na fachada Pedido excluir");	
		Resultado resultado = new Resultado();
		resultado = dao.excluir(ent);
		return resultado;
	}

	@Override
	public Resultado listar(EntidadeDominio ent) {
		System.out.println("Entrou na fachada Pedido Listar");	
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
