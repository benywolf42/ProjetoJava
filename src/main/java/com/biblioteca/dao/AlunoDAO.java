package com.biblioteca.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.biblioteca.model.Aluno;

public class AlunoDAO {
	
	EntityManager manager;
	private static EntityManagerFactory factory;
	
	static {
		factory = Persistence.createEntityManagerFactory("biblioteca");
	}
	public EntityManager getEntityManager(){
		return factory.createEntityManager();
	}
	
	public void salvar(Aluno aluno){
		manager = getEntityManager();
		manager.getTransaction().begin();
		manager.merge(aluno);
		manager.getTransaction().commit();
	
	}
	
	public void remover(Aluno aluno){
		manager = getEntityManager();
		manager.getTransaction().begin();
		manager.remove(manager.getReference(Aluno.class,aluno.getRa()));
		manager.getTransaction().commit();
	}
	
	public List<Aluno> consultar(){
		manager = getEntityManager();
		Query query = manager.createQuery("SELECT a FROM Aluno as a");
		return query.getResultList();
	}

}
