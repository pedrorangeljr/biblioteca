package model;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Devolucao {

	private Long id;

	@NotNull(message = "O empréstimo é obrigatório.")
	private Long emprestimoId;

	@NotNull(message = "A data da devolução é obrigatória.")
	private LocalDate dataDevolucao;

	@Size(max = 500, message = "A observação deve possuir no máximo 500 caracteres.")
	private String observacao;

	public Devolucao() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getEmprestimoId() {
		return emprestimoId;
	}

	public void setEmprestimoId(Long emprestimoId) {
		this.emprestimoId = emprestimoId;
	}

	public LocalDate getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(LocalDate dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

}
