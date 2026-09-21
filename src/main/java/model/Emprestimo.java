package model;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public class Emprestimo {

	private Long id;

	@NotNull(message = "O aluno é obrigatório.")
	private Long alunoId;

	@NotNull(message = "O exemplar é obrigatório.")
	private Long exemplarId;

	@NotNull(message = "A data do empréstimo é obrigatória.")
	private LocalDate dataEmprestimo;

	@NotNull(message = "A data prevista para devolução é obrigatória.")
	private LocalDate dataPrevistaDevolucao;

	@NotNull(message = "O status é obrigatório.")
	private String status = "ABERTO";

	public Emprestimo() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAlunoId() {
		return alunoId;
	}

	public void setAlunoId(Long alunoId) {
		this.alunoId = alunoId;
	}

	public Long getExemplarId() {
		return exemplarId;
	}

	public void setExemplarId(Long exemplarId) {
		this.exemplarId = exemplarId;
	}

	public LocalDate getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(LocalDate dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	public LocalDate getDataPrevistaDevolucao() {
		return dataPrevistaDevolucao;
	}

	public void setDataPrevistaDevolucao(LocalDate dataPrevistaDevolucao) {
		this.dataPrevistaDevolucao = dataPrevistaDevolucao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
