package br.com.newstation.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.newstation.dominio.CartaoCredito;

@FacesConverter("CartaoCreditoConverter")
public class CartaoCreditoConverter implements Converter {

	@Override
	public CartaoCredito  getAsObject(FacesContext context, UIComponent component, String cartao) {

		if (cartao == null || cartao.trim().isEmpty()) {
			return null;
		}

		CartaoCredito end = new CartaoCredito();

		end.setId(Integer.valueOf(cartao));

		return end;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object cartao) {

		if (cartao == null) {
			return null;
		}

		CartaoCredito end = (CartaoCredito) cartao;

		return end.toString();
	}

}
