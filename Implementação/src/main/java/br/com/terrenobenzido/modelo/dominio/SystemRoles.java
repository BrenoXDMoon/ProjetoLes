package br.com.terrenobenzido.modelo.dominio;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SystemRoles {

	@Id
	private String name;
	
	@Deprecated
	public SystemRoles() {}
	
	public SystemRoles(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
