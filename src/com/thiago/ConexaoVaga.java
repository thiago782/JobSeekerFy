package com.thiago;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
		String adicionar = "";

		adicionar = "INSERT INTO contasbar.vaga (idEmpregadorFK, "
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
	
	public void listarVagas() {
	    ConexaoCategoria conexao = new ConexaoCategoria();
	    Connection conn = conexao.conectar();
	    try {
	      String consulta = "SELECT a.idVaga, c.email, b.descricao, a.descricao, a.salario\r\n"
	      		+ "from vaga as a \r\n"
	      		+ "inner JOIN \r\n"
	      		+ "categoria as b \r\n"
	      		+ "ON a.idCategoriaFK = b.idCategoria\r\n"
	      		+ "inner JOIN \r\n"
	      		+ "empregador as c \r\n"
	      		+ "ON a.idEmpregadorFK = c.idEmpregador";

	      Statement stm = conn.createStatement();
	      ResultSet resultado = stm.executeQuery(consulta);
	      
	      while(resultado.next()) {
	        System.out.print("ID VAGA:" + resultado.getLong("a.idVaga") + "\n");
	        System.out.print("EMAIL EMPREGADOR: " + resultado.getString("c.email") + "\n");
	        System.out.print(" CATEGORIA: " + resultado.getString("b.descricao") + "\n");
	        System.out.print(" FUNÇÃO DA VAGA: " + resultado.getString("a.descricao") + "\n");
	        System.out.print(" SALARIO DA VAGA: " + resultado.getString("a.salario") + "\n");
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