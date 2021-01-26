package br.com.terrenobenzido.controle.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.terrenobenzido.modelo.dominio.Carta;

@FacesConverter(forClass = Carta.class)
public class CartaConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String id) {
		
		if(id == null || id.trim().isEmpty()) {
			return null;
		}

		Carta carta = new Carta(); 
		
		carta.setId(Integer.valueOf(id));
		
		return carta;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object cartaObject) {
		
		
		if(cartaObject == null) {
			return null;
		}
		
		Carta carta = (Carta) cartaObject;
		
		
		return carta.getId().toString();
	}

}
