package br.edu.unisinos.codec.algorithm;

public abstract class Algorithm {
	
	protected Object input;
	protected Object output;
	
	abstract public void compress();
	
	abstract public void decompress();
	
	public void setInput(Object input) {
		this.input = input;
	}
	
	public Object getOutput() {
		return this.output;
	}

}
