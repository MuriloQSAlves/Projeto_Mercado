package View;

import Model.Produto;

public class Teste {
	public static void main(String[] args) {
		Produto ps4 = new Produto("Playstation p4", 1789.44);
		Produto xbox = new Produto("Xbox 360", 1699.0);
		System.out.println(ps4);
		System.out.println(xbox);
	}

}