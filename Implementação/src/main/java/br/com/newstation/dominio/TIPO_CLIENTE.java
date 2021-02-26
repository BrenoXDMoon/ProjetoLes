package br.com.newstation.dominio;

import javax.persistence.Embeddable;

@Embeddable
public enum TIPO_CLIENTE {
	
	
	
	Básico,
	Médio,
	Avançado;
	
	private TIPO_CLIENTE() {
		// TODO Auto-generated constructor stub
	}
}
