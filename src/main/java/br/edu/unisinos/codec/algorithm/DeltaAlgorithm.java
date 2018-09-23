package br.edu.unisinos.codec.algorithm;

public class DeltaAlgorithm extends Algorithm {

	public DeltaAlgorithm() {
		super();
	}

	@Override
	public void compress() {
		int length = this.input.length;
		this.output = new byte[length];
		this.output[0] = this.input[0];
		for (int i = 1; i < length; i++) {
			this.output[i] = (byte)(this.input[i] - this.input[i-1]);
		}
	}

	@Override
	public void decompress() {
		int length = this.input.length;
		this.output = new byte[length];
		this.output[0] = this.input[0];
		for (int i = 1; i < length; i++) {
			this.output[i] = (byte)(this.input[i] + this.output[i-1]);
		}
	}

}
