package com.biblioteca.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import com.biblioteca.dao.AlunoDAO;
import com.biblioteca.model.Aluno;
import com.biblioteca.model.Curso;
import com.biblioteca.util.Mensagem;

@ManagedBean
@ViewScoped
public class AlunoBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Aluno aluno = new Aluno();
	private Aluno alunoSelecionado;
	private List<Aluno> alunos = new ArrayList<Aluno>();
	private Mensagem msg = new Mensagem();
	
	

	public List<Aluno> getAlunos() {
		AlunoDAO dao = new AlunoDAO();
		this.alunos = dao.consultar();
		return this.alunos;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Curso[] getCursos() {
		return Curso.values();
	}

	public Aluno getAlunoSelecionado() {
		return alunoSelecionado;
	}

	public void setAlunoSelecionado(Aluno alunoSelecionado) {
		System.out.println(alunoSelecionado);	
		this.alunoSelecionado = alunoSelecionado;
	}
	
	public void salvar(){
		AlunoDAO dao = new AlunoDAO();
		dao.salvar(this.aluno);
		msg.info("Aluno salvo com sucesso!");
		this.alunos = dao.consultar();
		RequestContext context = RequestContext.getCurrentInstance();
		context.update("frm:principal");
	}
	
	public void novoAluno(){
		this.aluno = new Aluno();
	}
	
	public void remover(){
		AlunoDAO dao = new AlunoDAO();
		dao.remover(this.alunoSelecionado);
		alunoSelecionado=null;
		consultar();
		msg.info("Aluno removido com sucesso!");
		RequestContext context = RequestContext.getCurrentInstance();
		context.update("frm:principal");
	}
	
	public void consultar(){
		AlunoDAO dao = new AlunoDAO();
		this.alunos.clear();
		this.alunos = dao.consultar();	
	}
	

}
