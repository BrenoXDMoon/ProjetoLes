package br.com.newstation.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.newstation.dominio.Endereco;

@FacesConverter("EndConverter")
public class EndConverter implements Converter {

	@Override
	public Endereco getAsObject(FacesContext context, UIComponent component, String endereco) {
		
		if(endereco == null || endereco.trim().isEmpty()) {
			return null;
		}
		

		Endereco end = new Endereco();
		
		end.setId(Integer.valueOf(endereco));
		
		System.out.println("obj End: "+end.getId());
		
		return end;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object enderecoObject) {
		
		
		if(enderecoObject == null) {
			return null;
		}
		
		Endereco end = (Endereco) enderecoObject;
		
		System.out.println("string End: "+end.toString());
		
		return end.toString();
	}

}
