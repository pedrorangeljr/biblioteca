package model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Periodico {

	private Long id;

	@NotBlank(message = "O título é obrigatório.")
	@Size(max = 200, message = "O título deve possuir no máximo 200 caracteres.")
	private String titulo;

	@NotBlank(message = "O ISSN é obrigatório.")
	@Size(max = 20, message = "O ISSN deve possuir no máximo 20 caracteres.")
	private String issn;

	@Size(max = 150, message = "A editora deve possuir no máximo 150 caracteres.")
	private String editora;

	@Size(max = 50, message = "A periodicidade deve possuir no máximo 50 caracteres.")
	private String periodicidade;

	public Periodico() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getIssn() {
		return issn;
	}

	public void setIssn(String issn) {
		this.issn = issn;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public String getPeriodicidade() {
		return periodicidade;
	}

	public void setPeriodicidade(String periodicidade) {
		this.periodicidade = periodicidade;
	}

}
