package br.com.newstation.dominio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.OneToMany;

public class Pessoa extends EntidadeDominio{

	@OneToMany
	protected List<Documento> documentos = new ArrayList<Documento>();

	public List<Documento> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<Documento> documentos) {
		this.documentos = documentos;
	}
	
}
