package br.edu.unisinos.codec.stream;

import java.io.File;

import br.edu.unisinos.codec.pipeline.Pipeline;

public abstract class CodecStream {
	
	protected File inputFile;
	protected File outputFile;
	protected Pipeline pipe;
	
	public CodecStream(File inputFile, File outputFile) {
		this.inputFile = inputFile;
		this.outputFile = outputFile;
	}
	
	public abstract void compress();
	public abstract void decompress();
	
	protected abstract void readBlock();
	protected abstract void writeBlock();

}
