package com.biblioteca.model;

public enum Edicao {
	
	CM("Comum"),ES("Especial");
	
	private String descricao;
	
	Edicao(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
