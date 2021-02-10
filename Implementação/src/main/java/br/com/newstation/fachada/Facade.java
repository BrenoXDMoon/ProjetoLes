package br.com.newstation.fachada;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.newstation.daos.ClienteDao;
import br.com.newstation.daos.IDao;
import br.com.newstation.dominio.Cliente;
import br.com.newstation.dominio.EntidadeDominio;
import br.com.newstation.dominio.Resultado;
import br.com.newstation.strategies.IStrategy;
import br.com.newstation.strategies.ValidaCPF;
import br.com.newstation.strategies.ValidaExistenciaClientePorCPF;

public class Facade implements IFachada{

	Map<String, IDao> daos;
	Map<String, Map<String, List<IStrategy>>> rns;
	StringBuilder sb = new StringBuilder();
	IDao dao;
	
	
	public Facade(){
		
		daos = new HashMap<String, IDao>();
		
		
		//----------SETANDO OS DAOS--------//
		
		daos.put(Cliente.class.getName(), new ClienteDao());
		
		//-------------------------- HASH CLIENTE--------------------------------//
		Map<String, List<IStrategy>> regrasCliente = new HashMap<String,List<IStrategy>>();
		
		List<IStrategy> regrasSalvarCliente = new ArrayList<IStrategy>();
		
		regrasSalvarCliente.add(new ValidaCPF());
		regrasSalvarCliente.add(new ValidaExistenciaClientePorCPF());
		
		regrasCliente.put("SALVAR", regrasSalvarCliente);
		
		rns.put(Cliente.class.getName(), regrasCliente);
		
	}
	
	@Override
	public Resultado salvar(EntidadeDominio ent) {
		
		String nmClasse = ent.getClass().getName();
		
		Map<String, List<IStrategy>> mapaEntidade = rns.get(nmClasse);
		List<IStrategy> rnEnt = mapaEntidade.get("SALVAR");
		
		executarRegras(ent, rnEnt);
		
		if(sb.length() == 0) {
			dao = daos.get(ent.getClass());
			dao.salvar(ent);			
		}else {
			
		}
		
		return null;
	}

	@Override
	public Resultado editar(EntidadeDominio ent) {
		
		return null;
	}

	@Override
	public Resultado excluir(EntidadeDominio ent) {
		
		return null;
	}

	@Override
	public Resultado listar(EntidadeDominio ent) {
		
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
