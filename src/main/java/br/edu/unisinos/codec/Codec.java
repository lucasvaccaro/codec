package br.edu.unisinos.codec;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.edu.unisinos.codec.stream.CodecStream;
import br.edu.unisinos.codec.pipeline.Pipeline1;

public class Codec {
	private static final Logger LOGGER = Logger.getLogger(Codec.class.getName());
	
	public static final String EXTENSION = "glr";
	protected CodecStream codecStream;

	public Codec(String inputFilePath, String outputFilePath) {
		File inputFile = new File(inputFilePath);
		if (!inputFile.canRead()) {
			LOGGER.log(Level.SEVERE, "Arquivo não existe ou não pode ser lido");
			return;
		}
		
		if (outputFilePath == null || outputFilePath.isEmpty()) {
			LOGGER.log(Level.SEVERE, "Arquivo de destino inválido");
			return;
		}
		
		File outputFile = new File(outputFilePath);

		this.codecStream = new CodecStream(inputFile, outputFile, new Pipeline1());
	}
	
	public void compress() {
		this.codecStream.compress();
	}
	
	public void decompress() {
		this.codecStream.decompress();
	}

}
