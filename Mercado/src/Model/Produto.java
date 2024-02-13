package Model;

import Helper.Utils;

public class Produto {
	private static int contador = 1;

	private int codigo;
	private String nome;
	private Double preco;

	public Produto(String nome, Double preco) {
		this.codigo = Produto.contador;
		this.nome = nome;
		this.preco = preco;
		Produto.contador += 1;
	}

	// gets

	public int getCodigo() {
		return this.codigo;
	}

	public String getNome() {
		return nome;
	}

	public Double getPreco() {
		return preco;
	}

	// sets

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public String toString() {
		return "Código: " + this.getCodigo() + "\nNome: " + this.getNome() + "\nPreco: "
				+ Utils.doubleParaString(this.getPreco());
	}

}
