package br.com.terrenobenzido.controle.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.terrenobenzido.modelo.dominio.Estoque;

@FacesConverter("EstoqueConverter")
public class EstoqueConverter implements Converter {

	@Override
	public Estoque getAsObject(FacesContext context, UIComponent component, String quantidade) {
		
		if(quantidade == null || quantidade.trim().isEmpty()) {
			return null;
		}

		Estoque estoque = new Estoque();
		
		estoque.setQuantidade(Integer.valueOf(quantidade));
		
		return estoque;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object EstoqueObject) {
		
		
		if(EstoqueObject == null) {
			return null;
		}
		
		Estoque estoque = (Estoque) EstoqueObject;
		
		
		return estoque.getQuantidade().toString();
	}

}
