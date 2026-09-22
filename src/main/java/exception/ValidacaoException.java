package exception;

import java.util.Set;

import jakarta.validation.ConstraintViolation;

public class ValidacaoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final Set<? extends ConstraintViolation<?>> erros;

	public ValidacaoException(Set<? extends ConstraintViolation<?>> erros) {

		super("Existem erros de validação.");

		this.erros = erros;
	}

	public Set<? extends ConstraintViolation<?>> getErros() {
		return erros;
	}
}
