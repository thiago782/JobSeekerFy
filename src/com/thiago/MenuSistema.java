package com.thiago;

import java.util.Scanner;

public class MenuSistema {
	Vaga vg = new Vaga();
	public static void main(String[] args) {

	}

	public void MenuUsuario() {
		int opcao = 0;

		
		ConexaoUsuario conn = new ConexaoUsuario();
		ConexaoVaga connVaga = new ConexaoVaga();

		
		
		do {
			System.out.println("Bem Vindo Usu�rio!, selecione uma Op��o: ");
			System.out.println("\n                  ===================================");
			System.out.println("                  |     1 - VISUALIZAR VAGAS CATEGORIA|");
			System.out.println("                  |     2 - LISTAR TODAS AS VAGAS     |");
			System.out.println("                  |     3 - VAGAS QUE ME CANDIDATEI   |");
			System.out.println("                  |     0 - SAIR                      |");
			System.out.println("                  ===================================\n");

			Scanner ler = new Scanner(System.in);
			System.out.println("Insira uma op��o: ");
			opcao = ler.nextInt();
			switch (opcao) {
			case 1:
				System.out.println("Visualizando Vagas...!");
				System.out.println(conn.idPessoa);
				break;

			case 2:
				System.out.println("Listando todas as vagas...");
				connVaga.listarVagas();
				break;

			case 3:
				System.out.println("Listando vagas que voc� se candidatou...");
				break;

			case 0:
				System.out.println("Saindo...");
				break;
			default:
				System.out.println("Op��o Inv�lida!");
				break;
			}
		} while (opcao != 0);
		System.out.println("At� a pr�xima Usuario!");
	}
	
	public void MenuEmpregador(long idRetorno) {
		int opcao = 0;
		Registro reg = new Registro();
		ConexaoUsuario conn = new ConexaoUsuario();

		do {
			System.out.println("Bem Vindo Empregador!, selecione uma Op��o: ");
			System.out.println("\n                  ===================================");
			System.out.println("                  |     1 - CADASTRAR NOVA VAGA       |");
			System.out.println("                  |     2 - VERIFICAR CANDIDATOS VAGAS|");
			System.out.println("                  |     0 - SAIR                      |");
			System.out.println("                  ===================================\n");

			Scanner ler = new Scanner(System.in);
			System.out.println("Insira uma op��o: ");
			opcao = ler.nextInt();
			switch (opcao) {
			case 1:
				System.out.println("Iniciando cadastro de vaga..!");
				vg.cadastraVaga(idRetorno);
				break;

			case 2:
				System.out.println("1 - Sou Empregador!");

				break;

			case 0:
				System.out.println("Saindo...");
				break;
			default:
				System.out.println("Op��o Inv�lida!");
				break;
			}
		} while (opcao != 0);
		System.out.println("At� a pr�xima Empregador!");
	}
}
