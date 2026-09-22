package util;

import java.util.Set;

import jakarta.validation.ConstraintViolation;
import model.Aluno;

public class TesteValidacao {

	public static void main(String[] args) {

		Aluno aluno = new Aluno();

		aluno.setNome("");
		aluno.setMatricula("");
		aluno.setEmail("email-invalido");

		Set<ConstraintViolation<Aluno>> erros = Validador.validar(aluno);

		for (ConstraintViolation<Aluno> erro : erros) {

			System.out.println(erro.getPropertyPath() + ": " + erro.getMessage());
		}
	}

}
