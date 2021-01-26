package br.com.terrenobenzido.modelo.dao;

import br.com.terrenobenzido.modelo.dominio.EntidadeDominio;
import br.com.terrenobenzido.modelo.dominio.Resultado;

public interface IDAO {

	Resultado salvar(EntidadeDominio ent);
	Resultado editar(EntidadeDominio ent);
	Resultado excluir(EntidadeDominio ent);
	Resultado listar(EntidadeDominio ent);
	Resultado visualizar(EntidadeDominio ent);
}
