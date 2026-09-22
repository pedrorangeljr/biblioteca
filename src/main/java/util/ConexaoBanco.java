package util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConexaoBanco {

	private static final HikariDataSource FONTE_DADOS;

	static {

		try {

			Properties propriedades = new Properties();
			propriedades.load(ConexaoBanco.class.getClassLoader().getResourceAsStream("banco.properties"));

			HikariConfig configuracao = new HikariConfig();

			configuracao.setJdbcUrl(propriedades.getProperty("banco.url"));
			configuracao.setUsername(propriedades.getProperty("banco.usuario"));
			configuracao.setPassword(propriedades.getProperty("banco.senha"));

			configuracao.setMaximumPoolSize(12);
			configuracao.setMinimumIdle(2);

			configuracao.setConnectionTimeout(10000);
			configuracao.setIdleTimeout(600000);

			configuracao.setPoolName("BibliotecaHikariPool");

			FONTE_DADOS = new HikariDataSource(configuracao);

		} catch (Exception e) {

			throw new ExceptionInInitializerError(
					"Não foi possível inicializar o pool " + "de conexões" + e.getMessage());
		}
	}

	public ConexaoBanco() {
	}

	/* Instancia o pool de conexão com banco */
	public static Connection obterConexao() throws SQLException {

		return FONTE_DADOS.getConnection();
	}

	/* Deixa o pool liberado para outra conexão */
	public static void fecharPoll() {

		if (FONTE_DADOS != null && !FONTE_DADOS.isClosed()) {

			FONTE_DADOS.close();
		}
	}
}
