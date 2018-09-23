package br.edu.unisinos.codec;

public class Main {

	public static void main(String[] args) {
		if (args.length < 3) {
			System.out.println("Parâmetros necessários: <arquivo_de_origem> <-c|-d> <arquivo_de_destino>");
			return;
		}
		
		String inputFile = args[0];
		String op = args[1];
		String outputFile = args[2];
		
		Codec c = new Codec(inputFile, outputFile);
		if (op.equals("-c")) {
			c.compress();
		} else if (op.equals("-d")) {
			c.decompress();
		} else {
			System.out.println("Operação desconhecida (informe '-c' ou '-d')");
			return;
		}
		
		System.out.println("Sucesso");
	}

}
