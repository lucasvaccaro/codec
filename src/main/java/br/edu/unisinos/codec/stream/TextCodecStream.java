package br.edu.unisinos.codec.stream;

import java.io.File;

import br.edu.unisinos.codec.pipeline.TextPipeline;

public class TextCodecStream extends CodecStream {

	public TextCodecStream(File inputFile, File outputFile) {
		super(inputFile, outputFile);
		this.pipe = new TextPipeline();
		// TODO open file
	}
	
	@Override
	public void compress() {
		// TODO read block
		// compress
		// write block
	}

	@Override
	public void decompress() {
		// TODO read block
		// decompress
		// write block
	}

	@Override
	protected void readBlock() {
		// TODO

	}

	@Override
	protected void writeBlock() {
		// TODO

	}

}
