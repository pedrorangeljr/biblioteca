package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Aluno;
import util.ConexaoBanco;

public class AlunoDAO {

	public void salvar(Aluno aluno) throws SQLException {

		String sql = """
				INSERT INTO alunos
				    (nome, matricula, email, telefone, ativo)
				VALUES
				    (?, ?, ?, ?, ?)
				""";
		try (Connection conexao = ConexaoBanco.obterConexao();
				PreparedStatement insert = conexao.prepareStatement(sql)) {

			insert.setString(1, aluno.getNome());
			insert.setString(2, aluno.getMatricula());
			insert.setString(3, aluno.getEmail());
			insert.setString(4, aluno.getTelefone());
			insert.setBoolean(5, aluno.isAtivo());
			insert.execute();

		}
	}

	public List<Aluno> listarTodos() throws SQLException {

		String sql = """
				SELECT id, nome, matricula, email, telefone, ativo
				FROM alunos
				ORDER BY nome
				""";
		List<Aluno> alunos = new ArrayList<>();

		try (Connection conexao = ConexaoBanco.obterConexao();
				PreparedStatement select = conexao.prepareStatement(sql);
				ResultSet resultado = select.executeQuery()) {

			while (resultado.next()) {

				alunos.add(mapearAluno(resultado));
			}
		}

		return alunos;
	}

	public Aluno buscarPorId(Long id) throws SQLException {

		String sql = """
				SELECT id, nome, matricula, email, telefone, ativo
				FROM alunos
				WHERE id = ?
				""";

		try (Connection conexao = ConexaoBanco.obterConexao();
				PreparedStatement select = conexao.prepareStatement(sql)) {

			select.setLong(1, id);

			try (ResultSet resultado = select.executeQuery()) {

				if (resultado.next()) {

					return mapearAluno(resultado);
				}
			}
		}

		return null;
	}

	public Aluno buscarPorMatricula(String matricula) throws SQLException {

		String sql = """
				SELECT id, nome, matricula, email, telefone, ativo
				FROM alunos
				WHERE matricula = ?
				""";
		try (Connection conexao = ConexaoBanco.obterConexao();
				PreparedStatement select = conexao.prepareStatement(sql)) {

			select.setString(1, matricula);

			try (ResultSet resultado = select.executeQuery()) {

				if (resultado.next()) {

					return mapearAluno(resultado);
				}
			}
		}

		return null;
	}

	public void atualizar(Aluno aluno) throws SQLException {

		String sql = """
				UPDATE alunos
				SET nome = ?,
				    matricula = ?,
				    email = ?,
				    telefone = ?,
				    ativo = ?,
				    atualizado_em = CURRENT_TIMESTAMP
				WHERE id = ?
				""";

		try (Connection conexao = ConexaoBanco.obterConexao();
				PreparedStatement update = conexao.prepareStatement(sql)) {

			update.setString(1, aluno.getEmail());
			update.setString(2, aluno.getMatricula());
			update.setString(3, aluno.getEmail());
			update.setString(4, aluno.getTelefone());
			update.setBoolean(5, aluno.isAtivo());
			update.setLong(6, aluno.getId());

			update.executeUpdate();
		}
	}

	public void excluir(Long id) throws SQLException {

		String sql = "DELETE FROM alunos WHERE id = ? ";

		try (Connection conexao = ConexaoBanco.obterConexao();
				PreparedStatement delete = conexao.prepareStatement(sql)) {

			delete.setLong(1, id);
			delete.executeUpdate();
		}
	}

	/* O método mapearAluno() transforma cada registro em um objeto Java. */
	private Aluno mapearAluno(ResultSet resultado) throws SQLException {

		Aluno aluno = new Aluno();

		aluno.setId(resultado.getLong("id"));
		aluno.setNome(resultado.getString("nome"));
		aluno.setMatricula(resultado.getString("matricula"));
		aluno.setEmail(resultado.getString("email"));
		aluno.setTelefone(resultado.getString("telefone"));
		aluno.setAtivo(resultado.getBoolean("ativo"));

		return aluno;
	}
}
