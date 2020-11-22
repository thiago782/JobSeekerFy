package com.thiago;
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

		System.out.println("Digite a Descri��o da Vaga: ");
		descricao = ler.next();

		System.out.println("Digite o Sal�rio da Vaga: ");
		salario = ler.nextFloat();

		connVaga.inserirVaga(idCategoria, descricao, salario, idRetorno);

		System.out.println("Vaga cadastrada com sucesso! Aguarde Inscri��es!");
	}

	public void VerificarCandidatosVaga() {

		System.out.println("Usuario cadastrado com sucesso! Voc� ser� redirecionado para tela Inicial.");

	}
}
