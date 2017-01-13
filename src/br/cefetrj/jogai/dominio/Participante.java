package br.cefetrj.jogai.dominio;

import java.util.Date;

import javax.persistence.Entity;

/**
 * Classe Participante - Representa um participante (aluno) do jogo
 * Cada participante possui nome, gênero, uma data de nascimento, e um e-mail de contato
 * @author JOGAI
 *
 */
//Anotação para mostrar que essa classe é armazenada no banco de dados
@Entity
public class Participante extends EntidadeSatelite {

	private String nome;

	private String genero;

	private Date dataNascimento;

	private String email;
	
	//Construtores específicos para facilitar a vida durante o código em outras classes e beans
	public Participante() {
	}

	public Participante(String nome) {
		super();
		this.nome = nome;
	}
	
	//Getters e Setters
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
