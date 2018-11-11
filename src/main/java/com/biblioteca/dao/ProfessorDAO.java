package com.biblioteca.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.biblioteca.model.Professor;

public class ProfessorDAO {
	
	EntityManager manager;
	private static EntityManagerFactory factory;
	
	static {
		factory = Persistence.createEntityManagerFactory("biblioteca");
	}
	public EntityManager getEntityManager(){
		return factory.createEntityManager();
	}
	
	public void salvar(Professor prof){
		manager = getEntityManager();
		manager.getTransaction().begin();
		manager.merge(prof);
		manager.getTransaction().commit();
	
	}
	
	public void remover(Professor prof){
		manager = getEntityManager();
		manager.getTransaction().begin();
		manager.remove(manager.getReference(Professor.class,prof.getRp()));
		manager.getTransaction().commit();
	}
	
	public List<Professor> consultar(){
		manager = getEntityManager();
		Query query = manager.createQuery("SELECT p FROM Professor as p");
		return query.getResultList();
	}
}
