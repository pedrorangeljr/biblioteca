package model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class Multa {

	private Long id;

	@NotNull(message = "O aluno é obrigatório.")
	private Long alunoId;

	@NotNull(message = "O empréstimo é obrigatório.")
	private Long emprestimoId;

	@NotNull(message = "O valor da multa é obrigatório.")
	@DecimalMin(value = "0.00", inclusive = true, message = "O valor da multa não pode ser negativo.")
	private BigDecimal valor;

	@NotNull(message = "A data de geração é obrigatória.")
	private LocalDate dataGeracao;

	private boolean paga = false;

	private LocalDate dataPagamento;

	public Multa() {
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

	public Long getEmprestimoId() {
		return emprestimoId;
	}

	public void setEmprestimoId(Long emprestimoId) {
		this.emprestimoId = emprestimoId;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public LocalDate getDataGeracao() {
		return dataGeracao;
	}

	public void setDataGeracao(LocalDate dataGeracao) {
		this.dataGeracao = dataGeracao;
	}

	public boolean isPaga() {
		return paga;
	}

	public void setPaga(boolean paga) {
		this.paga = paga;
	}

	public LocalDate getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDate dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

}
