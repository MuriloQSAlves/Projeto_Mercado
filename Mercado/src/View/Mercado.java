package View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import Helper.Utils;
import Model.Produto;

public class Mercado {

	private static Scanner leitor = new Scanner(System.in);
	private static ArrayList<Produto> produtos;
	private static Map<Produto, Integer> carrinho;

	public static void main(String[] args) {

		produtos = new ArrayList<Produto>();
		carrinho = new HashMap<Produto, Integer>();

		Mercado.menu();
	}

	private static void menu() {
		System.out.println("===============================");
		System.out.println("======== Bem-vindo(a) =========");
		System.out.println("========= GEEK SHOP ===========");
		System.out.println("===============================");

		System.out.println("Selecione um opção abaixo: ");
		System.out.println("");
		System.out.println("1 - Cadastrar produto");
		System.out.println("2 - Listar produtos");
		System.out.println("3 - Comprar produto");
		System.out.println("4 - Visualizar carrinho");
		System.out.println("5 - Sair do sistema");

		int opcao = 0;

		try {
			opcao = Integer.parseInt(Mercado.leitor.nextLine());
		} catch (InputMismatchException e) {
			Mercado.menu();
		} catch (NumberFormatException f) {
			Mercado.menu();
		}

		switch (opcao) {
		case 1:
			Mercado.cadastrarProduto();
			break;

		case 2:
			Mercado.listarProdutos();
			break;

		case 3:
			Mercado.comprarProduto();
			break;

		case 4:
			Mercado.visualizarCarrinho();
			break;

		case 5:
			System.out.println("Volte sempre!");
			Utils.pausar(2);
			System.exit(0);

		default:
			System.out.println("Opção inválida.");
			Utils.pausar(2);
			Mercado.menu();
			break;
		}

	}

	private static void cadastrarProduto() {
		System.out.println("Cadastro de  Produtos");
		System.out.println("=====================");
		System.out.println("Informe o nome do produto: ");
		String nome = Mercado.leitor.nextLine();

		System.out.println("Informe o preço do produto: ");
		Double preco = Mercado.leitor.nextDouble();

		Produto produto = new Produto(nome, preco);

		Mercado.produtos.add(produto);

		System.out.println("O produto " + produto.getNome() + " foi cadastrado com sucesso!");

		Utils.pausar(2);
		Mercado.menu();
	}

	private static void listarProdutos() {
		if (Mercado.produtos.size() > 0) {
			System.out.println("Listagem de produtos");
			System.out.println();

			for (Produto p : Mercado.produtos) {
				System.out.println(p);
				System.out.println("================");
			}

		} else {
			System.out.println("Ainda não há produtos cadastrados.");
		}
		Utils.pausar(2);
		Mercado.menu();

	}

	private static void comprarProduto() {

		if (Mercado.produtos.size() > 0) {
			System.out.println("Informe o código do produto: ");
			System.out.println("==========");

			System.out.println("============= Produtos dispóníveis =============");

			for (Produto p : Mercado.produtos) {
				System.out.println(p);
				System.out.println("------------------");
			}
			int codigo = Integer.parseInt(Mercado.leitor.nextLine());
			boolean tem = false;

			for (Produto p : Mercado.produtos) {
				if (p.getCodigo() == codigo) {
					int quant = 0;
					try {
						quant = Mercado.carrinho.get(p);
						// já tem este produto no carrinho, atualiza quantidade
						Mercado.carrinho.put(p, quant + 1);
					} catch (NullPointerException e) {
						// primeiro produto no carrinho
						Mercado.carrinho.put(p, 1);
					}

					System.out.println("O produto " + p.getNome() + " foi adicionado ao carrinho.");
					tem = true;
				}
				if (tem) {
					System.out.println("Deseja adicionar outro produto ao carrinho?");
					System.out.println("Informe 1 para sim ou 0 para não: ");
					int op = Integer.parseInt(Mercado.leitor.nextLine());

					if (op == 1) {
						Mercado.comprarProduto();
					} else {
						System.out.println("Por favor aguarde enquanto fechamos seu pedido...");
						Utils.pausar(2);
						Mercado.fecharPedido();
					}
				} else {
					System.out.println("Não foi encontrado o produto.");
					Utils.pausar(2);
					Mercado.menu();
				}

			}

		} else {
			System.out.println("Não há produto cadastrado.");
			Utils.pausar(2);
			Mercado.menu();
		}
	}

	private static void visualizarCarrinho() {
		if (Mercado.carrinho.size() > 0) {
			System.out.println("Produtos no carrinho: ");

			for (Produto p : Mercado.carrinho.keySet()) {
				System.out.println("Produto: " + p + "\nQuantidade: " + Mercado.carrinho.get(p));
			}
		} else {
			System.out.println("Seu carrinho está vázio.");
		}
		Utils.pausar(2);
		Mercado.menu();

	}

	private static void fecharPedido() {
		Double valorTotal = 0.0;
		System.out.println("Produtos do carrinho");
		System.out.println("==============");
		for (Produto p : Mercado.carrinho.keySet()) {
			int quant = Mercado.carrinho.get(p);
			valorTotal += p.getPreco() * quant;
			System.out.println(p);
			System.out.println("Quantidade " + quant);
			System.out.println("--------------------");
		}
		System.out.println("Sua fatura é: " + Utils.doubleParaString(valorTotal));
		Mercado.carrinho.clear();
		System.out.println("Obrigado pela preferência.");
		Utils.pausar(5);
		Mercado.menu();

	}

}
