package com.biblioteca.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import com.biblioteca.dao.ProfessorDAO;
import com.biblioteca.model.Professor;
import com.biblioteca.util.Mensagem;

@ManagedBean
@ViewScoped
public class ProfessorBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Professor prof = new Professor();
	private Professor profSelecionado;
	private List<Professor> profs = new ArrayList<Professor>();
	private Mensagem msg = new Mensagem();
	
	

	public List<Professor> getProfs() {
		ProfessorDAO dao = new ProfessorDAO();
		this.profs = dao.consultar();
		return this.profs;
	}

	public Professor getProf() {
		return prof;
	}

	public void setProf(Professor prof) {
		this.prof = prof;
	}
	
	public Professor getProfSelecionado() {
		return profSelecionado;
	}

	public void setProfSelecionado(Professor profSelecionado) {
		System.out.println(profSelecionado);	
		this.profSelecionado = profSelecionado;
	}
	
	public void salvar(){
		ProfessorDAO dao = new ProfessorDAO();
		dao.salvar(this.prof);
		msg.info("Professor salvo com sucesso!");
		this.profs = dao.consultar();
		RequestContext context = RequestContext.getCurrentInstance();
		context.update("frm:principal");
	}
	
	public void novoProfessor(){
		this.prof = new Professor();
	}
	
	public void remover(){
		ProfessorDAO dao = new ProfessorDAO();
		dao.remover(this.profSelecionado);
		profSelecionado=null;
		consultar();
		msg.info("Professor removido com sucesso!");
		RequestContext context = RequestContext.getCurrentInstance();
		context.update("frm:principal");
	}
	
	public void consultar(){
		ProfessorDAO dao = new ProfessorDAO();
		this.profs.clear();
		this.profs = dao.consultar();	
	}
}
