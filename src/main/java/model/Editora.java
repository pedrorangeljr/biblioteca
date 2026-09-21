package model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Editora {

	private Long id;

	@NotBlank(message = "O nome da editora é obrigatório.")
	@Size(max = 150, message = "O nome deve possuir no máximo 150 caracteres.")
	private String nome;

	@Size(max = 100, message = "A cidade deve possuir no máximo 100 caracteres.")
	private String cidade;

	@Size(min = 2, max = 2, message = "O estado deve possuir 2 caracteres.")
	private String estado;

	@Email(message = "Informe um e-mail válido.")
	@Size(max = 150, message = "O e-mail deve possuir no máximo 150 caracteres.")
	private String email;

	@Size(max = 30, message = "O telefone deve possuir no máximo 30 caracteres.")
	private String telefone;

	public Editora() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}
