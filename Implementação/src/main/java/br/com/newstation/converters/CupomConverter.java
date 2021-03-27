package br.com.newstation.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.newstation.dominio.Cupom;

@FacesConverter("CupomConverter")
public class CupomConverter implements Converter {

	@Override
	public Cupom getAsObject(FacesContext context, UIComponent component, String cupom) {

		if (cupom == null || cupom.trim().isEmpty()) {
			return null;
		}

		Cupom end = new Cupom();

		end.setId(Integer.valueOf(cupom));

		return end;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object cupom) {

		if (cupom == null) {
			return null;
		}

		Cupom end = (Cupom) cupom;

		return end.toString();
	}

}
