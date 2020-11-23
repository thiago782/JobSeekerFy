package com.thiago;

import java.util.Scanner;

import com.thiago.banco.ConexaoCategoria;
import com.thiago.banco.ConexaoUsuario;
import com.thiago.banco.ConexaoVaga;

public class MenuSistema {
	Vaga vg = new Vaga();
	public static void main(String[] args) {

	}

	public void MenuUsuario(long idPessoa) {
		int opcao = 0;

		
		ConexaoUsuario conn = new ConexaoUsuario();
		ConexaoVaga connVaga = new ConexaoVaga();
		ConexaoCategoria contCat = new ConexaoCategoria();
		
		
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
				contCat.listarCategorias();
				System.out.println("Informe o c�digo da Categoria que deseja Visualizar!");
				int codCat = ler.nextInt();
				if (codCat > 0) {
					connVaga.listarVagaCategoria(codCat);
				}
				else {
					System.out.println("Por favor, informe um n�mero!");
				}
				
				break;

			case 2:
				System.out.println("Listando todas as vagas...");
				connVaga.listarVagas(idPessoa);
				break;

			case 3:
				System.out.println("Listando vagas que voc� se candidatou...");
				connVaga.listarVagasCandidatadas(idPessoa);
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
				vg.VerificarCandidatosVaga(idRetorno);
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
