package util;

import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public final class Validador {

	private static final Validator VALIDADOR;

	static {

		ValidatorFactory fabrica = Validation.buildDefaultValidatorFactory();

		VALIDADOR = fabrica.getValidator();
	}

	public Validador() {
	}

	public static <T> Set<ConstraintViolation<T>> validar(
            T objeto) {

        return VALIDADOR.validate(objeto);
    }
}
