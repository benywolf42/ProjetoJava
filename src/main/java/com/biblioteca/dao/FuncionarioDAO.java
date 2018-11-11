package com.biblioteca.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.biblioteca.model.Funcionario;
import com.biblioteca.model.Perfil;

public class FuncionarioDAO {

	EntityManager manager;
	private static EntityManagerFactory factory;
	
	static {
		factory = Persistence.createEntityManagerFactory("biblioteca");
	}
	public EntityManager getEntityManager(){
		return factory.createEntityManager();
	}
	
	public void salvar(Funcionario func){
		manager = getEntityManager();
		manager.getTransaction().begin();
		manager.merge(func);
		manager.getTransaction().commit();
	
	}
	
	public void remover(Funcionario func){
		manager = getEntityManager();
		manager.getTransaction().begin();
		manager.remove(manager.getReference(Funcionario.class,func.getId()));
		manager.getTransaction().commit();
	}
	
	public List<Funcionario> consultar(){
		manager = getEntityManager();
		Query query = manager.createQuery("SELECT f FROM Funcionario as f");
		return query.getResultList();
	}
	
	public Perfil validarLogin(Funcionario funcionario){
		manager = getEntityManager();
		Query query = manager.createQuery("SELECT p FROM Funcionario f,Perfil p where f.id=p.funcionario and f.nome = :nome and f.senha = :senha");
		query.setParameter("nome", funcionario.getNome());
		query.setParameter("senha", funcionario.getSenha());
		query.setMaxResults(1);
		try{
			
			return (Perfil) query.getSingleResult();
		}catch(NoResultException e){
			System.out.println(e.getMessage());
		}
		
		return null;
		
	}
}
