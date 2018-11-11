package com.biblioteca.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.biblioteca.model.Item;

public class ItemDAO {
	
	EntityManager manager;
	private static EntityManagerFactory factory;
	
	static {
		factory = Persistence.createEntityManagerFactory("biblioteca");
	}
	public EntityManager getEntityManager(){
		return factory.createEntityManager();
	}
	
	public void salvar(Item item){
		manager = getEntityManager();
		manager.getTransaction().begin();
		manager.merge(item);
		manager.getTransaction().commit();
	
	}
	
	public void remover(Item item){
		manager = getEntityManager();
		manager.getTransaction().begin();
		manager.remove(manager.getReference(Item.class,item.getCodigo()));
		manager.getTransaction().commit();
	}
	
	public List<Item> consultar(){
		manager = getEntityManager();
		Query query = manager.createQuery("SELECT i FROM Item as i WHERE i.status = 'Disponível'");
		return query.getResultList();
	}
	
}
