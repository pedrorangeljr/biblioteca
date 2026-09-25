package service;

import java.util.List;

import model.Aluno;

public interface AlunoService {

	void cadastrar(Aluno aluno);

	List<Aluno> listarTodos();

	Aluno buscarPorId(Long id);

	void atualizar(Aluno aluno);

	void excluir(Long id);
}
