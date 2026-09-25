package serviceimpl;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import dao.AlunoDAO;
import exception.RegraNegocioException;
import exception.ValidacaoException;
import jakarta.validation.ConstraintViolation;
import model.Aluno;
import service.AlunoService;
import util.Validador;

public class AlunoServiceImpl implements AlunoService {

	private final AlunoDAO alunoDAO;

	public AlunoServiceImpl(AlunoDAO alunoDAO) {

		this.alunoDAO = alunoDAO;
	}

	@Override
	public void cadastrar(Aluno aluno) {

		validarAluno(aluno);

		try {
			if (alunoDAO.buscarPorMatricula(aluno.getMatricula()) != null) {

				throw new RegraNegocioException("A matrícula já está cadastrada.");
			}

			alunoDAO.salvar(aluno);

		} catch (SQLException e) {
			throw new RuntimeException("Erro ao cadastrar aluno.", e);
		}
	}

	@Override
	public List<Aluno> listarTodos() {
		try {
			return alunoDAO.listarTodos();

		} catch (SQLException e) {
			throw new RuntimeException("Erro ao listar alunos.", e);
		}
	}

	@Override
	public Aluno buscarPorId(Long id) {

		validarId(id);

		try {
			Aluno aluno = alunoDAO.buscarPorId(id);

			if (aluno == null) {
				throw new RegraNegocioException("Aluno não encontrado.");
			}

			return aluno;

		} catch (SQLException e) {
			throw new RuntimeException("Erro ao consultar aluno.", e);
		}
	}

	@Override
	public void atualizar(Aluno aluno) {

		validarAluno(aluno);
		validarId(aluno.getId());

		try {
			Aluno alunoExistente = alunoDAO.buscarPorId(aluno.getId());

			if (alunoExistente == null) {
				throw new RegraNegocioException("Aluno não encontrado.");
			}

			Aluno alunoComMatricula = alunoDAO.buscarPorMatricula(aluno.getMatricula());

			if (alunoComMatricula != null && !alunoComMatricula.getId().equals(aluno.getId())) {

				throw new RegraNegocioException("A matrícula já está cadastrada.");
			}

			alunoDAO.atualizar(aluno);

		} catch (SQLException e) {
			throw new RuntimeException("Erro ao atualizar aluno.", e);
		}
	}

	@Override
	public void excluir(Long id) {

		validarId(id);

		try {

			Aluno aluno = alunoDAO.buscarPorId(id);

			if (aluno == null) {

				throw new RegraNegocioException("Aluno não encontrado");
			}

			alunoDAO.excluir(id);

		} catch (SQLException e) {

			throw new RuntimeException("Erro ao excluir aluno. Verifique se existem empréstimos ou multas vinculados.",
					e);
		}

	}

	private void validarAluno(Aluno aluno) {

		if (aluno == null) {
			throw new RegraNegocioException("Os dados do aluno são obrigatórios.");
		}

		Set<ConstraintViolation<Aluno>> erros = Validador.validar(aluno);

		if (!erros.isEmpty()) {
			throw new ValidacaoException(erros);
		}
	}

	private void validarId(Long id) {

		if (id == null || id <= 0) {
			throw new RegraNegocioException("Informe um ID de aluno válido.");
		}
	}

}
