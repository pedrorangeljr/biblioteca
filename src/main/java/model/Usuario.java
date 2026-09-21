package model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Usuario {

	private Long id;

	@NotBlank(message = "O nome é obrigatório")
	@Size(max = 150, message = "O nome deve possuir no máximo 150 caracteres.")
	private String nome;

	@NotBlank(message = "O usuário é obrigatório")
	@Size(max = 80, message = "O usuário deve possuir no máximo 80 caracteres")
	private String usuario;

	@NotBlank(message = "A senha é obrigatória")
	@Size(min = 6, max = 255, message = "A senha deve possuir entre 6 a 255 caracteres.")
	private String senha;

	private boolean ativo = true;

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

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

}
