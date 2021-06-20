package br.com.newstation.fachada;

import java.util.ArrayList;
import java.util.List;

import br.com.newstation.daos.ClienteDao;
import br.com.newstation.daos.IDao;
import br.com.newstation.dominio.EntidadeDominio;
import br.com.newstation.dominio.Resultado;
import br.com.newstation.strategies.IStrategy;
import br.com.newstation.strategies.ValidaCPF;
import br.com.newstation.strategies.ValidaExistenciaClientePorCPF;
import br.com.newstation.strategies.ValidaExistenciaPorEmail;

public class FachadaCliente implements IFachada {
	
	private ClienteDao dao;
	private StringBuilder sb = new StringBuilder();
	
	
	@Override
	public Resultado salvar(EntidadeDominio ent) {
		
		System.out.println("Entrou na fachada cliente salvar");
		
		Resultado resultado = new Resultado();
		
		dao = new ClienteDao();
		List<IStrategy> regras = new ArrayList<IStrategy>();
		regras.add(new ValidaCPF());
		regras.add(new ValidaExistenciaClientePorCPF());
		regras.add(new ValidaExistenciaPorEmail());
		
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
		
		System.out.println("Entrou na fachada cliente editar");	
		Resultado resultado = new Resultado();
		dao = new ClienteDao();
		
		List<IStrategy> regras = new ArrayList<IStrategy>();
		regras.add(new ValidaCPF());
		
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
		System.out.println("Entrou na fachada cliente excluir");	
		Resultado resultado = new Resultado();
		dao = new ClienteDao();
		
//		List<IStrategy> regras = new ArrayList<IStrategy>();
//		regras.add(new ValidaCPF());
//		
//		executarRegras(ent, regras);
		
//		if (sb.length() == 0) {
		resultado = dao.excluir(ent);
//		} else {
//			resultado.add(ent);
//			resultado.setMensagem((sb.toString()));
//		}
		
		return resultado;
	}

	@Override
	public Resultado listar(EntidadeDominio ent) {
		System.out.println("Entrou na fachada cliente Listar");	
		Resultado resultado = new Resultado();
		dao = new ClienteDao();
		
//		List<IStrategy> regras = new ArrayList<IStrategy>();
//		regras.add(new ValidaCPF());
//		
//		executarRegras(ent, regras);
		
//		if (sb.length() == 0) {
		resultado = dao.listar(ent);
//		} else {
//			resultado.add(ent);
//			resultado.setMensagem((sb.toString()));
//		}
		
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
