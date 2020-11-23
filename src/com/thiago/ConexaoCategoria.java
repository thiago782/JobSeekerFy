package com.thiago;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexaoCategoria {

	public static void main(String[] args) {
		ConexaoCategoria conexao = new ConexaoCategoria();

	}

	public Connection conectar() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://mysql741.umbler.com:41890/contasbar", "contasbar_admin",
					"1q2w3e4r");
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
			}	
		} catch (SQLException ex) {
			System.out.println("Não conseguiu desconectar do BD.");
		}
	}

	public void listarCategorias() {
	    ConexaoCategoria conexao = new ConexaoCategoria();
	    Connection conn = conexao.conectar();
	    try {
	      String consulta = "SELECT * FROM contasbar.categoria";

	      Statement stm = conn.createStatement();
	      //executa query no banco de dadorno execute query
	      ResultSet resultado = stm.executeQuery(consulta);
	      
	      while(resultado.next()) {
	        System.out.print(resultado.getLong("idCategoria"));
	        System.out.print(" | " + resultado.getString("descricao") + "\n");
	      }
	    } catch (SQLException ex) {
	      System.out.println("Não conseguiu consultar os dados de categorias!.");
	    } finally {
	      conexao.desconectar(conn);
	    }
	  }
}