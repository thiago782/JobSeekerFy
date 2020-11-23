package com.thiago.banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ConexaoVaga {

	public static void main(String[] args) {
		ConexaoVaga conexao = new ConexaoVaga();

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

	public void inserirVaga(int idCategoria, String descricao, float salario, long idPessoa) {
		ConexaoVaga conexao = new ConexaoVaga();
		Connection connv = conexao.conectar();

		String adicionar = "INSERT INTO contasbar.vaga (idEmpregadorFK, "
				+ "idCategoriaFK, descricao, salario) VALUES ";
		try {

			String dados = "(" + idPessoa + ", " + idCategoria + ", '" + descricao + "', " + salario + ")";
			adicionar = adicionar + dados;

			System.out.println(adicionar);
			Statement stm = connv.createStatement();
			stm.execute(adicionar);
			System.out.println("Adicionou a vaga: " + descricao + " no BD.");
		} catch (SQLException ex) {
			System.out.println("Não conseguiu adicionar a vaga no BD.");
		} finally {
			conexao.desconectar(connv);
		}
	}

	public void candidatarVaga(int idVaga, long idPessoa) {
		ConexaoVaga conexao = new ConexaoVaga();
		Connection connv = conexao.conectar();

		try {
			String consultaCandidatura = "SELECT * FROM candidatoVaga WHERE " + "idVagaFK = " + idVaga
					+ " AND idUsuarioFK = " + idPessoa;

			Statement stm = connv.createStatement();
			ResultSet resultado = stm.executeQuery(consultaCandidatura);

			// long idExiste = resultado.getInt("idVagaFK");
			if (resultado.isBeforeFirst()) {
				System.out.println("Você já se candidatou para essa vaga...");
			} else {
				System.out.println("Candidatando-se para vaga...");
				String criarCandidatura = "INSERT INTO contasbar.candidatoVaga (idVagaFK, " + "idUsuarioFK) VALUES ";
				String dados = "(" + idVaga + ", " + idPessoa + ")";
				criarCandidatura = criarCandidatura + dados;
				stm.execute(criarCandidatura);
				ResultSet res = stm.executeQuery(consultaCandidatura);
				System.out.println(criarCandidatura);
				System.out.println(res);
			}
		} catch (SQLException ex) {
			System.out.println("Não conseguiu Adicionar Candidatura!.");
		} finally {
			conexao.desconectar(connv);
		}
	}

	public void listarVagas(long idPessoa) {
		ConexaoCategoria conexao = new ConexaoCategoria();
		Connection conn = conexao.conectar();
		Scanner ler = new Scanner(System.in);
		try {
			String consulta = "SELECT a.idVaga, c.email, b.descricao, a.descricao, a.salario\r\n"
					+ "from vaga as a \r\n" + "inner JOIN \r\n" + "categoria as b \r\n"
					+ "ON a.idCategoriaFK = b.idCategoria\r\n" + "inner JOIN \r\n" + "empregador as c \r\n"
					+ "ON a.idEmpregadorFK = c.idEmpregador";

			Statement stm = conn.createStatement();
			ResultSet resultado = stm.executeQuery(consulta);
			System.out.println("------------------------------------------------------------------");
			while (resultado.next()) {
				System.out.print("ID VAGA:" + resultado.getLong("a.idVaga") + "\n");
				System.out.print("EMAIL EMPREGADOR: " + resultado.getString("c.email") + "\n");
				System.out.print(" CATEGORIA: " + resultado.getString("b.descricao") + "\n");
				System.out.print(" FUNÇÃO DA VAGA: " + resultado.getString("a.descricao") + "\n");
				System.out.print(" SALARIO DA VAGA: R$" + resultado.getString("a.salario") + "\n");
				System.out.println("------------------------------------------------------------------");
			}
		} catch (SQLException ex) {
			System.out.println("Não conseguiu consultar as vagas em aberto!.");
		} finally {
			conexao.desconectar(conn);
		}
		System.out.println("Deseja se candidatar para " + "alguma vaga? Se sim, digite a ID da vaga, se não digite '0'");
		int codVaga = ler.nextInt();
		if (codVaga != 0) {
			candidatarVaga(codVaga, idPessoa);
		} else {
			System.out.println("Voltando ao menu!");
		}

	}

	public void listarVagaCategoria(int idCategoria) {
		ConexaoCategoria conexao = new ConexaoCategoria();
		Connection conn = conexao.conectar();
		Scanner ler = new Scanner(System.in);
		try {
			String consulta = "SELECT a.idVaga, c.email, b.descricao, a.descricao, a.salario\r\n"
					+ "from vaga as a \r\n" + "inner JOIN \r\n" + "categoria as b \r\n"
					+ "ON a.idCategoriaFK = b.idCategoria\r\n" + "inner JOIN \r\n" + "empregador as c \r\n"
					+ "ON a.idEmpregadorFK = c.idEmpregador";

			String filtro = " WHERE b.idCategoria =" + idCategoria;
			consulta = consulta + filtro;

			Statement stm = conn.createStatement();
			ResultSet resultado = stm.executeQuery(consulta);
			if (resultado.isBeforeFirst()) {
			
			System.out.println("------------------------------------------------------------------");
			while (resultado.next()) {
				System.out.print("ID VAGA:" + resultado.getLong("a.idVaga") + "\n");
				System.out.print("EMAIL EMPREGADOR: " + resultado.getString("c.email") + "\n");
				System.out.print(" CATEGORIA: " + resultado.getString("b.descricao") + "\n");
				System.out.print(" FUNÇÃO DA VAGA: " + resultado.getString("a.descricao") + "\n");
				System.out.print(" SALARIO DA VAGA: R$" + resultado.getString("a.salario") + "\n");
				System.out.println("------------------------------------------------------------------");
			}
			}
			else {
				System.out.println("Nenhuma vaga encontrada para essa categoria!");
			}
		} catch (SQLException ex) {
			System.out.println("Não conseguiu consultar as vagas em aberto!.");
		} finally {
			conexao.desconectar(conn);
		}

	}

	public void listarVagasCandidatadas(long idPessoa) {
		ConexaoCategoria conexao = new ConexaoCategoria();
		Connection conn = conexao.conectar();
		try {
			String consulta = "SELECT b.idVaga, b.descricao, c.descricao, b.salario, e.email FROM \r\n"
					+ "candidatoVaga AS a\r\n" + "INNER JOIN vaga AS b\r\n" + "ON a.idVagaFK = b.idVaga\r\n"
					+ "inner JOIN categoria AS c\r\n" + "ON b.idCategoriaFK = c.idCategoria\r\n"
					+ "inner JOIN usuario as d\r\n" + "ON a.idUsuarioFK = d.idUsuario\r\n"
					+ "inner JOIN empregador as e\r\n" + "ON b.idEmpregadorFK = e.idEmpregador ";
			String filtro = "WHERE a.idUsuarioFK =" + idPessoa;
			consulta = consulta + filtro;

			Statement stm = conn.createStatement();
			ResultSet resultado = stm.executeQuery(consulta);
			System.out.println("------------------------------------------------------------------");
			while (resultado.next()) {
				System.out.print("ID VAGA:" + resultado.getLong("b.idVaga") + "\n");
				System.out.print("EMAIL EMPREGADOR: " + resultado.getString("e.email") + "\n");
				System.out.print(" CATEGORIA: " + resultado.getString("c.descricao") + "\n");
				System.out.print(" FUNÇÃO DA VAGA: " + resultado.getString("b.descricao") + "\n");
				System.out.print(" SALARIO DA VAGA: R$" + resultado.getString("b.salario") + "\n");
				System.out.println("------------------------------------------------------------------");
			}
		} catch (SQLException ex) {
			System.out.println("Não conseguiu consultar as vagas em aberto!.");
		} finally {
			conexao.desconectar(conn);
		}

	}

	public void atualizar() {
		ConexaoVaga conexao = new ConexaoVaga();
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