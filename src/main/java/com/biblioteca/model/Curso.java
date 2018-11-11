package com.biblioteca.model;

public enum Curso {

	ADS("ADS"),BD("BD");
	
	private String descricao;
	
	Curso(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
