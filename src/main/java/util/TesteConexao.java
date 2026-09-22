package util;

import java.sql.Connection;

public class TesteConexao {

	public static void main(String[] args) {

		try (Connection conexao = ConexaoBanco.obterConexao()) {

			System.out.println("Conexão realizada com sucesso!");

			System.out.println("Banco: " + conexao.getCatalog());

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

}
