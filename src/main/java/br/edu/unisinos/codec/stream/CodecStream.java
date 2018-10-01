package br.edu.unisinos.codec.stream;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

import br.edu.unisinos.codec.pipeline.Pipeline;

public class CodecStream {
	
	protected File inputFile;
	protected File outputFile;
	protected Pipeline pipe;
	
	public CodecStream(File inputFile, File outputFile, Pipeline pipe) {
		this.inputFile = inputFile;
		this.outputFile = outputFile;
		this.pipe = pipe;
	}
	
	public void compress() {
		this.pipe.setInput(this.readBlock());
		this.pipe.compress();
		this.writeBlock(this.pipe.getOutput());
	}

	public void decompress() {
		this.pipe.setInput(this.readBlock());
		this.pipe.decompress();
		this.writeBlock(this.pipe.getOutput());
	}

	private byte[] readBlock() {
	    try {
			return Files.readAllBytes(this.inputFile.toPath());
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	private void writeBlock(byte[] bytes) {
		FileOutputStream stream = null;
		try {
			stream = new FileOutputStream(this.outputFile);
		    stream.write(bytes);
		    stream.close();
		} catch (IOException e) {
			e.printStackTrace();
			try {
				stream.close();
			} catch (IOException e2) {};
		}
	}

}
