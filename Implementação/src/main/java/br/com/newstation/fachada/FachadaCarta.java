package br.com.newstation.fachada;

import br.com.newstation.daos.CartaDao;
import br.com.newstation.dominio.EntidadeDominio;
import br.com.newstation.dominio.Resultado;
import br.com.newstation.strategies.ValidaEstoque;
import br.com.newstation.strategies.ValidacaoEntidadeNula;

public class FachadaCarta  implements IFachada {

	CartaDao dao;
	
	@Override
	public Resultado salvar(EntidadeDominio ent) {

		Resultado resultado = new Resultado();
		ValidacaoEntidadeNula validaNull = new ValidacaoEntidadeNula();
		
		if(validaNull.processar(ent) == null) {
			dao = new CartaDao();
			
			resultado =  dao.editar(ent);
			
			return resultado;
		}else {
			System.out.println("Erro de validação");
			resultado.setMensagem("Erro de validação");
			
			return resultado;			
		}
		
	}

	@Override
	public Resultado editar(EntidadeDominio ent) {
		ValidaEstoque validaEstoque = new ValidaEstoque();
		
		if(validaEstoque.processar(ent) == null) {
			dao = new CartaDao();
			return dao.editar(ent);
		}
		
		return null;
	}

	@Override
	public Resultado excluir(EntidadeDominio ent) {

		Resultado resultado = new Resultado();
		dao = new CartaDao();
		
		resultado = dao.excluir(ent);
		
		return resultado;
	}

	@Override
	public Resultado listar(EntidadeDominio ent) {
		
		System.out.println("FACHADA CARTA LIST");
		
		Resultado resultado = new Resultado();
		dao = new CartaDao();
		
		resultado = dao.listar(ent);
		
		return resultado;
	}

}
