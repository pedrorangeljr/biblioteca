package model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Livro {

	private Long id;

	@NotBlank(message = "O título é obrigatório.")
	@Size(max = 200, message = "O título deve possuir no máximo 200 caracteres.")
	private String titulo;

	@NotBlank(message = "O autor é obrigatório.")
	@Size(max = 150, message = "O autor deve possuir no máximo 150 caracteres.")
	private String autor;

	@NotBlank(message = "O ISBN é obrigatório.")
	@Size(max = 20, message = "O ISBN deve possuir no máximo 20 caracteres.")
	private String isbn;

	@Size(max = 150, message = "O assunto deve possuir no máximo 150 caracteres.")
	private String assunto;

	@NotNull(message = "A editora é obrigatória.")
	private Long editoraId;

	public Livro() {
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

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public Long getEditoraId() {
		return editoraId;
	}

	public void setEditoraId(Long editoraId) {
		this.editoraId = editoraId;
	}

}
