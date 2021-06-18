package br.com.newstation.daos;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class AbstractDao implements IDao{

	protected EntityManagerFactory factory = Persistence.createEntityManagerFactory("newstation");
	protected EntityManager manager;
	
	void abrirConexao(){
		if(!this.factory.isOpen()) {
			factory = Persistence.createEntityManagerFactory("newstation");			
		}
		this.manager = this.factory.createEntityManager();
	}
	
	void fechaConexao() {
		this.manager.close();
		this.factory.close();
	}
	
	
}
