package br.com.terrenobenzido.controle.fachada;

import br.com.terrenobenzido.modelo.dominio.EntidadeDominio;
import br.com.terrenobenzido.modelo.dominio.Resultado;

public interface IFachada {

	public Resultado salvar(EntidadeDominio ent);
	public Resultado listar(EntidadeDominio ent);
	public Resultado editar(EntidadeDominio ent);
	public Resultado excluir(EntidadeDominio ent);
	public Resultado visualizar(EntidadeDominio entidade);
	public Resultado login(EntidadeDominio entidade);
}
