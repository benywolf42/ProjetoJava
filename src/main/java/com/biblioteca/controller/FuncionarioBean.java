package com.biblioteca.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import com.biblioteca.dao.FuncionarioDAO;
import com.biblioteca.model.Funcionario;
import com.biblioteca.util.Mensagem;
import com.biblioteca.util.SessionContext;

@ManagedBean
@ViewScoped
public class FuncionarioBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private Funcionario func = new Funcionario();
	private Funcionario funcSelecionado;
	private List<Funcionario> funcs = new ArrayList<Funcionario>();
	private Mensagem msg = new Mensagem();
    SessionContext session = SessionContext.getInstance();
	
	

	public List<Funcionario> getFuncs() {
		FuncionarioDAO dao = new FuncionarioDAO();
		this.funcs = dao.consultar();
		return this.funcs;
	}

	public Funcionario getFunc() {
		return func;
	}

	public void setFunc(Funcionario func) {
		this.func = func;
	}
	
	public Funcionario getFuncSelecionado() {
		return funcSelecionado;
	}

	public void setFuncSelecionado(Funcionario funcSelecionado) {
		System.out.println(funcSelecionado);	
		this.funcSelecionado = funcSelecionado;
	}
	
	public void salvar(){
		FuncionarioDAO dao = new FuncionarioDAO();
		dao.salvar(this.func);
		msg.info("Funcionário salvo com sucesso!");
		this.funcs = dao.consultar();
		RequestContext context = RequestContext.getCurrentInstance();
		context.update("frm:principal");
	}
	
	public void novoFuncionario(){
		this.func = new Funcionario();
	}
	
	public void remover(){
		FuncionarioDAO dao = new FuncionarioDAO();
		dao.remover(this.funcSelecionado);
		funcSelecionado=null;
		consultar();
		msg.info("Funcionário removido com sucesso!");
		RequestContext context = RequestContext.getCurrentInstance();
		context.update("frm:principal");
	}
	
	public void consultar(){
		FuncionarioDAO dao = new FuncionarioDAO();
		this.funcs.clear();
		this.funcs = dao.consultar();	
	}
	
	public String[] getBotao(){
		String[] arrayAcesso = ((String) session.getAttribute("acesso")).split("");
		return arrayAcesso;
	}
}
