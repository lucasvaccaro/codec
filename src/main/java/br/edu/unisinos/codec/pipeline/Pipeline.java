package br.edu.unisinos.codec.pipeline;

import java.util.LinkedList;

import br.edu.unisinos.codec.algorithm.Algorithm;

public abstract class Pipeline {
	
	protected Object input;
	protected Object output;
	protected LinkedList<Algorithm> list;
	
	public Pipeline() {
		this.list = new LinkedList<Algorithm>();
	}
	
	public void compress() {
		Object tempOutput = this.input;
		for (Algorithm alg : this.list) {
			alg.setInput(tempOutput);
			alg.compress();
			tempOutput = alg.getOutput();
		}
		this.output = tempOutput;
	}

	public void decompress() {
		Object tempOutput = this.input;
		for (Algorithm alg : this.list) {
			alg.setInput(tempOutput);
			alg.decompress();
			tempOutput = alg.getOutput();
		}
		this.output = tempOutput;
	}
	
	public void setInput(Object input) {
		this.input = input;
	}
	
	public Object getOutput() {
		return this.output;
	}

}
