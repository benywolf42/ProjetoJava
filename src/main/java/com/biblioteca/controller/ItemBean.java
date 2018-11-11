package com.biblioteca.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import com.biblioteca.dao.ItemDAO;
import com.biblioteca.model.Edicao;
import com.biblioteca.model.Item;
import com.biblioteca.util.Mensagem;

@ManagedBean
@ViewScoped
public class ItemBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private Item item = new Item();
	private Item itemSelecionado;
	private List<Item> itens = new ArrayList<Item>();
	private Mensagem msg = new Mensagem();
	
	
	public List<Item> getItens() {
		ItemDAO dao = new ItemDAO();
		this.itens = dao.consultar();
		return this.itens;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
	
	public Item getItemSelecionado() {
		return itemSelecionado;
	}

	public void setItemSelecionado(Item itemfSelecionado) {
		System.out.println(itemfSelecionado);	
		this.itemSelecionado = itemfSelecionado;
	}
	
	public Edicao[] getEdicoes(){
		return Edicao.values();
	}
	
	public void salvar(){
		ItemDAO dao = new ItemDAO();
		dao.salvar(this.item);
		msg.info("Item salvo com sucesso!");
		this.itens = dao.consultar();
		RequestContext context = RequestContext.getCurrentInstance();
		context.update("frm:principal");
	}
	
	public void novoItem(){
		this.item = new Item();
	}
	
	public void remover(){
		ItemDAO dao = new ItemDAO();
		dao.remover(this.itemSelecionado);
		itemSelecionado=null;
		consultar();
		msg.info("Item removido com sucesso!");
		RequestContext context = RequestContext.getCurrentInstance();
		context.update("frm:principal");
	}
	
	public void consultar(){
		ItemDAO dao = new ItemDAO();
		this.itens.clear();
		this.itens = dao.consultar();	
	}
}
