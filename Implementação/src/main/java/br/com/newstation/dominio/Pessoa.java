package br.com.newstation.dominio;

import java.util.List;

public class Pessoa extends EntidadeDominio {

	private List<Documento> documentos;

	public List<Documento> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<Documento> documentos) {
		this.documentos = documentos;
	}
}
