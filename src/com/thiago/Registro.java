package com.thiago;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Registro {
	String email = "";
	String senha = "";
	String cnpj = "";
	String cpf = "";

    ConexaoUsuario conn = new ConexaoUsuario();

    
	Scanner ler = new Scanner(System.in);

	public void RegistrarEmpregador() {
		System.out.println("Iniciando cadastro do Empregador...");
		System.out.println("Digite o Email do Empregador: ");
		email = ler.next();

		System.out.println("Digite a Senha do Empregador: ");
		senha = ler.next();

		System.out.println("Digite o CNPJ do Empregador: ");
		cnpj = ler.next();
		
		conn.inserirPessoa(email, senha, cnpj, 1);

		System.out.println("Empregador cadastrado com sucesso! Você será redirecionado para tela Inicial.");

	}

	public void RegistrarUsuario() {
		System.out.println("Iniciando cadastro do Usuario...");
		System.out.println("Digite o Email do Usuario: ");
		email = ler.next();

		System.out.println("Digite a Senha do Usuario: ");
		senha = ler.next();

		System.out.println("Digite o CPF do Usuario: ");
		cpf = ler.next();
		
		conn.inserirPessoa(email, senha, cpf, 2);
		
		System.out.println("Usuario cadastrado com sucesso! Você será redirecionado para tela Inicial.");

	}
}
