package br.edu.unisinos.codec.algorithm;

public abstract class Algorithm {
	
	protected byte[] input;
	protected byte[] output;
	
	abstract public void compress();
	
	abstract public void decompress();
	
	public void setInput(byte[] input) {
		this.input = input;
	}
	
	public byte[] getOutput() {
		return this.output;
	}

}
