package com.thiago;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import com.thiago.CadastrarItem;

public class App {

	public static void main(String[] args) throws IOException, SQLException {
		int opcao = 0;

		CadastrarItem cad = new CadastrarItem();
	
		Registro reg = new Registro();
		ConexaoUsuario conn = new ConexaoUsuario();
		MenuSistema menu = new MenuSistema();

		do {
			System.out.println("Bem Vindo a TrampoFy, selecione uma Opção: ");
			System.out.println("\n                  ===================================");
			System.out.println("                  |     1 - LOGIN                     |");
			System.out.println("                  |     2 - REGISTRO                  |");
			System.out.println("                  |     0 - SAIR                      |");
			System.out.println("                  ===================================\n");

			Scanner ler = new Scanner(System.in);
			System.out.println("Insira uma opção: ");
			opcao = ler.nextInt();
			switch (opcao) {
			case 1:
				System.out.println("Faremos o login...!");
				System.out.println("Digite o Email: ");
				String email = ler.next();

				System.out.println("Digite a Senha: ");
				String senha = ler.next();

				System.out.println("1 - Empregador: ");
				System.out.println("2 - Usuário: ");
				int tipo = ler.nextInt();
				long idRetorno = conn.login(email, senha, tipo);
				if (idRetorno > 0) {
					if (tipo == 1) {
						menu.MenuEmpregador(idRetorno);
					} else if (tipo == 2) {
						menu.MenuUsuario(idRetorno);
					}
				} else {
					System.out.println("Não consegui logar!");
				}

				break;

			case 2:
				System.out.println("1 - Sou Empregador!");
				System.out.println("2 - Estou buscando EM-PRE-GO!!");
				opcao = ler.nextInt();
				if (opcao == 1) {
					reg.RegistrarEmpregador();
				} else if (opcao == 2) {
					reg.RegistrarUsuario();
				} else {
					System.out.println("Não entendi...");
				}
				break;

			case 0:
				System.out.println("Saindo...");
				break;
			default:
				System.out.println("Opção Inválida!");
				break;
			}
		} while (opcao != 0);
		System.out.println("Até a próxima!");
	}
}
