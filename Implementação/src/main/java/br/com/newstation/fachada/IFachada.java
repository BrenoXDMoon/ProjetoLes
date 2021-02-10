package br.com.newstation.fachada;

import br.com.newstation.dominio.EntidadeDominio;
import br.com.newstation.dominio.Resultado;

public interface IFachada {

	Resultado salvar(EntidadeDominio ent);
	Resultado editar(EntidadeDominio ent);
	Resultado excluir(EntidadeDominio ent);
	Resultado listar(EntidadeDominio ent);
}
