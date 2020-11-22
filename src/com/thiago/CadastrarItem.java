package com.thiago;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CadastrarItem {
	String nome = "";
	float preco = 0;
	Scanner ler = new Scanner(System.in);

	public void CadastrarPrato() {
		System.out.println("Iniciando cadastro de novo prato...");
		System.out.println("Digite o Nome do novo prato: ");
		nome = ler.next();
		System.out.println("Digite o Preço do novo prato: ");
		preco = ler.nextFloat();

		String path = "C:\\Users\\Airton\\eclipse-workspace\\EatFyPlus\\src\\data\\pratos.csv";
		try (FileWriter fw = new FileWriter(path, true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {
			out.println(nome + ";" + preco);
		} catch (IOException e) {
			System.out.println("Não consegui gravar!");
		}
		System.out.println("Prato cadastrado com sucesso! Execute a listagem para visualizar o novo prato.");

	}

	public void CadastrarBebida() {
		System.out.println("Iniciando cadastro de nova bebida...");
		System.out.println("Digite o Nome da nova Bebida: ");
		nome = ler.next();
		System.out.println("Digite o Preço da nova Bebida: ");
		preco = ler.nextFloat();

		String path2 = "C:\\Users\\Airton\\eclipse-workspace\\EatFyPlus\\src\\data\\bebidas-tabuladas.txt";
		try (FileWriter fw = new FileWriter(path2, true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {
			out.println(preco + "	" + nome);
		} catch (IOException e) {
			System.out.println("Não consegui gravar!");
		}
		System.out.println("Bebida cadastrada com sucesso! Execute a listagem para visualizar a nova Bebida.");

	}

	public void CadastrarVinho() {
		System.out.println("Iniciando cadastro de novo vinho...");
		System.out.println("Digite o Nome do novo vinho: ");
		nome = ler.next();
		System.out.println("Digite o Preço da novo vinho: ");
		preco = ler.nextFloat();

		String path3 = "C:\\Users\\Airton\\eclipse-workspace\\EatFyPlus\\src\\data\\vinhos-tabulados.txt";
		try (FileWriter fw = new FileWriter(path3, true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {
			out.println(preco + "	" + nome);
		} catch (IOException e) {
			System.out.println("Não consegui gravar!");
		}
		System.out.println("Vinho cadastrado com sucesso! Execute a listagem para visualizar o novo Vinho.");

	}

}
