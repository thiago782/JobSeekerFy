package com.thiago;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexaoUsuario {
	long idPessoa;

	public static void main(String[] args) {
		boolean achou;


	}

	public Connection conectar() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://mysql741.umbler.com:41890/contasbar", "contasbar_admin",
					"1q2w3e4r");
			System.out.println("Conectou no banco de dados.");
		} catch (SQLException ex) {
			System.out.println("Erro: Não conseguiu conectar no BD.");
		} catch (ClassNotFoundException ex) {
			System.out.println("Erro: Não encontrou o driver do BD.");
		}

		return conn;
	}

	public void desconectar(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
				System.out.println("Desconectou do banco de dados.");
			}
		} catch (SQLException ex) {
			System.out.println("Não conseguiu desconectar do BD.");
		}
	}

	public void consulta() {
		ConexaoUsuario conexao = new ConexaoUsuario();
		Connection conn = conexao.conectar();
		try {
			String consulta = "SELECT * FROM usuario WHERE email like 'thiago@fkn.com.br'";

			Statement stm = conn.createStatement();
			ResultSet resultado = stm.executeQuery(consulta);

			while (resultado.next()) {
				System.out.print(resultado.getLong("idUsuario"));
				System.out.print(" - " + resultado.getString("email"));
				System.out.print(" - " + resultado.getInt("senha") + "\n");
			}
		} catch (SQLException ex) {
			System.out.println("Não conseguiu consultar os dados do Usuario.");
		} finally {
			conexao.desconectar(conn);
		}
	}

	public long login(String email, String senha, int tipo) {
		ConexaoUsuario conexao = new ConexaoUsuario();
		Connection conn = conexao.conectar();
		String tabela = "";
		String id = "idUsuario";
		if (tipo == 1) {
			tabela = "empregador";
			id = "idEmpregador";
			
		} else if (tipo == 2) {
			tabela = "usuario";
			id = "idUsuario";
		}
		try {
			String consulta = "SELECT * FROM contasbar." + tabela + " WHERE email = '" + email + "'" + " AND senha=" + "'" + senha
					+ "'";
			System.out.println(consulta);
			Statement stm = conn.createStatement();
			ResultSet resultado = stm.executeQuery(consulta);
			
			while (resultado.next()) {
				System.out.print(resultado.getLong(id));
				System.out.print(" - " + resultado.getString("email"));
				System.out.print(" - " + resultado.getInt("senha") + "\n");
				idPessoa = resultado.getLong(id);
			}
			if (idPessoa > 0) {
				System.out.println(idPessoa);
				System.out.println("Achei um!");
				Boolean achou = true;
			} else {
				System.out.println("Achei nada...");
				boolean achou = false;
			}

		} catch (SQLException ex) {
			System.out.println("Não conseguiu consultar os dados do Usuario.");
		} finally {
			conexao.desconectar(conn);
		}

		return idPessoa;

	}

	public void inserirPessoa(String email, String senha, String cpf, int tipo) {
		ConexaoUsuario conexao = new ConexaoUsuario();
		Connection conn = conexao.conectar();
		String adicionar = "";

		if (tipo == 1) {
			adicionar = "INSERT INTO empregador (email, senha, cnpj) VALUES";
		} else if (tipo == 2) {
			adicionar = "INSERT INTO usuario (email, senha, cpf) VALUES";
		}
		try {

			String dados = "('" + email + "'," + "'" + senha + "'," + "'" + cpf + "')";
			adicionar = adicionar + dados;
			System.out.println(adicionar);
			Statement stm = conn.createStatement();
			stm.execute(adicionar);
			System.out.println("Adicionou a pessoa: " + email + " no BD.");
		} catch (SQLException ex) {
			System.out.println("Não conseguiu adicionar uma pessoa no BD.");
		} finally {
			conexao.desconectar(conn);
		}
	}

	public void atualizar() {
		ConexaoUsuario conexao = new ConexaoUsuario();
		Connection conn = conexao.conectar();
		try {
			String atualizar = "UPDATE PESSOA SET NOME = 'Cristiano' WHERE NOME = 'Rafael'";

			Statement stm = conn.createStatement();
			stm.executeUpdate(atualizar);
			System.out.println("Atualizou o nome de Rafael para Cristiano.");
		} catch (SQLException ex) {
			System.out.println("Não conseguiu atualizar uma pessoa no BD.");
		} finally {
			conexao.desconectar(conn);
		}
	}
}