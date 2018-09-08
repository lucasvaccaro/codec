package br.edu.unisinos.codec;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.edu.unisinos.codec.stream.CodecStream;
import br.edu.unisinos.codec.stream.BinaryCodecStream;
import br.edu.unisinos.codec.stream.TextCodecStream;

public class Codec {
	private static final Logger LOGGER = Logger.getLogger(Codec.class.getName());
	
	public static final String EXTENSION = "glr";
	
	protected File inputFile;
	protected File outputFile;
	protected String mimeType;
	protected CodecStream codecStream;

	public Codec(String filePath) {
		this.inputFile = new File(filePath);
		
		if (!this.inputFile.canRead()) {
			LOGGER.log(Level.SEVERE, "Arquivo não existe ou não pode ser lido");
			return;
		}
		
		try {
			this.mimeType = Files.probeContentType(this.inputFile.toPath());
		} catch (IOException e) {
			this.mimeType = null;
		}
		if (this.mimeType == null) {
			LOGGER.log(Level.INFO, "Mime-type definido como null");
		}
		
		this.outputFile = new File(filePath + "." + Codec.EXTENSION);
		
		this.defineCodecStreamType();
	}
	
	public void compress() {
		this.codecStream.compress();
	}
	
	public void decompress() {
		this.codecStream.decompress();
	}
	
	private void defineCodecStreamType() {
		switch (this.mimeType) {
			case "text/plain":
				this.codecStream = new TextCodecStream(this.inputFile, this.outputFile);
				return;
			default:
				this.codecStream = new BinaryCodecStream(this.inputFile, this.outputFile);
		}
	}

}
