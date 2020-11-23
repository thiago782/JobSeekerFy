package com.thiago;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Vaga {

	int idCategoria = 0;
	String descricao = "";
	float salario;
	ConexaoVaga connVaga = new ConexaoVaga();
	ConexaoCategoria contCat = new ConexaoCategoria();

	Scanner ler = new Scanner(System.in);

	public void cadastraVaga(long idRetorno) {
		contCat.listarCategorias();
		System.out.println("Digite a Categoria da Vaga: ");
		idCategoria = ler.nextInt();

		System.out.println("Digite a Descrição da Vaga: ");
		descricao = ler.next();

		System.out.println("Digite o Salário da Vaga: ");
		salario = ler.nextFloat();

		connVaga.inserirVaga(idCategoria, descricao, salario, idRetorno);

		System.out.println("Vaga cadastrada com sucesso! Aguarde Inscrições!");
	}

	public void VerificarCandidatosVaga(long idPessoa) {
		ConexaoCategoria conexao = new ConexaoCategoria();
		Connection conn = conexao.conectar();
		try {
			String consulta = "SELECT b.idVaga, b.descricao, c.descricao, b.salario, d.email FROM \r\n"
					+ "candidatoVaga AS a\r\n"
					+ "INNER JOIN vaga AS b \r\n"
					+ "ON a.idVagaFK = b.idVaga\r\n"
					+ "inner JOIN categoria AS c ON b.idCategoriaFK = c.idCategoria\r\n"
					+ "inner JOIN usuario AS d ON a.idUsuarioFK = d.idUsuario\r\n"
					+ "inner JOIN empregador AS e \r\n"
					+ "ON b.idEmpregadorFK = e.idEmpregador";
			String filtro = " WHERE b.idEmpregadorFK =" + idPessoa;
			consulta = consulta + filtro;

			Statement stm = conn.createStatement();
			ResultSet resultado = stm.executeQuery(consulta);
			System.out.println("------------------------------------------------------------------");
			while (resultado.next()) {
				System.out.print("ID VAGA:" + resultado.getLong("b.idVaga") + "\n");
				System.out.print("EMAIL CANDIDATO: " + resultado.getString("d.email") + "\n");
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
}
