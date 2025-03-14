package com.blogigreja.model;

import java.util.Date;

public class Post {
	private int id;
	private String titulo;
	private String conteudo;
	private String autor;
	private Date dataCriacao;
	
	public Post(int id, String titulo, String conteudo, String autor, Date dataCriacao) {
		this.id = id;
		this.titulo = titulo;
		this.conteudo = conteudo;
		this.autor = autor;
		this.dataCriacao = dataCriacao;
	}

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	
}
