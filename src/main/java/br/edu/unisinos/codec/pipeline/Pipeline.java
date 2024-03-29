package br.edu.unisinos.codec.pipeline;

import java.util.Iterator;
import java.util.LinkedList;

import br.edu.unisinos.codec.algorithm.Algorithm;

public abstract class Pipeline {
	
	protected byte[] input;
	protected byte[] output;
	protected LinkedList<Algorithm> list;
	
	public Pipeline() {
		this.list = new LinkedList<Algorithm>();
	}
	
	public void compress() {
		byte[] tempOutput = this.input;
		for (Algorithm alg : this.list) {
			alg.setInput(tempOutput);
			alg.compress();
			tempOutput = alg.getOutput();
		}
		this.output = tempOutput;
	}

	public void decompress() {
		byte[] tempOutput = this.input;
		Iterator<Algorithm> it = this.list.descendingIterator();
		while (it.hasNext()) {
			Algorithm alg = it.next();
			alg.setInput(tempOutput);
			alg.decompress();
			tempOutput = alg.getOutput();
		}
		this.output = tempOutput;
	}
	
	public void setInput(byte[] input) {
		this.input = input;
	}
	
	public byte[] getOutput() {
		return this.output;
	}

}
