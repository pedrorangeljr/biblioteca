package model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Exemplar {

	private Long id;

	@NotBlank(message = "O código do exemplar é obrigatório.")
	@Size(max = 50, message = "O código deve possuir no máximo 50 caracteres.")
	private String codigo;

	@NotNull(message = "O livro é obrigatório.")
	private Long livroId;

	@NotBlank(message = "A situação do exemplar é obrigatória.")
	private String situacao = "DISPONIVEL";

	public Exemplar() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Long getLivroId() {
		return livroId;
	}

	public void setLivroId(Long livroId) {
		this.livroId = livroId;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

}
