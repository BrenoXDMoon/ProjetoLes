package br.com.newstation.daos;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class AbstractDao implements IDao{

	protected EntityManagerFactory factory = Persistence.createEntityManagerFactory("newstation");
	protected EntityManager manager;
	
	void abrirConexao(){
		this.manager = this.factory.createEntityManager();
	}
}
