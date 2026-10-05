package com.podora.cinema;

public class Filme {
	private final String titulo;
	private String categoria = "Ação", sinopse = "Não informada";
	private int duracao, classificacaoEtaria;
	
	//Cadastro de filme
	public Filme(String titulo, int duracao, String categoria, int classificacaoEtaria) {
		this.titulo = titulo;
		this.categoria = categoria;
		this.duracao = duracao;
		this.classificacaoEtaria = classificacaoEtaria;
		if (duracao < 0) {
			this.duracao = 90;
		}
		if(classificacaoEtaria < 0) {
			this.classificacaoEtaria = 0;
		}
	}
	
	public Filme(String titulo, int duracao) {
		this.titulo = titulo;
		this.duracao = duracao;
		if (duracao < 0) {
			this.duracao = 90;
		}
		this.classificacaoEtaria = 0;
	}
	
	
	//Getters e setters
	public String getTitulo() {
		return titulo;
	}
	
	public int getDuracao() {
		return duracao;
	}
	
	public int getClassificacaoEtaria() {
		return classificacaoEtaria;
	}
	
	public String getCategoria() {
		return categoria;
	}
	
	
	public boolean setCategoria(String categoria) {
		categoria = categoria.toUpperCase();
		if (categoria == "AÇÃO" || categoria == "AVENTURA" || categoria == "COMÉDIA" || categoria == "DRAMA" || categoria == "TERROR") {
			this.categoria = categoria;
			return true;
		}
		return false;
	}
	
	public String getSinopse() {
		return sinopse;
	}

	public void setSinopse(String sinopse) {
		if(sinopse != null) {
			this.sinopse = sinopse;
		}
	}
	
	public boolean permissaoFilme(int idade) {
		if(idade<classificacaoEtaria) {
			return false;
		}
		return true;
	}
	
}
