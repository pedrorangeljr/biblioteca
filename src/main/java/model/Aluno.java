package model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Aluno {

	private Long id;

	@NotBlank(message = "O nome é obrigatório")
	@Size(max = 150, message = "O nome dev possuir no máximo 150 caracteres.")
	private String nome;

	@NotBlank(message = "A matricula é obrigatória.")
	@Size(max = 50, message = "A matricula deve possuir no máximo 50 caracteres")
	private String matricula;

	@Email(message = "Informe um e-mail válido")
	@Size(max = 150, message = "O e-mail, deve possuir no máximo 150 caracteres.")
	private String email;

	@Size(max = 30, message = "O telefone deve possuir no máximo 30 caracteres.")
	private String telefone;

	private boolean ativo = true;

	public Aluno() {

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

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
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

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

}
