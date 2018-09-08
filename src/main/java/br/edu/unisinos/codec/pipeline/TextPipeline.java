package br.edu.unisinos.codec.pipeline;

import br.edu.unisinos.codec.algorithm.GolombAlgorithm;

public class TextPipeline extends Pipeline {

	public TextPipeline() {
		super();
		this.list.add(new GolombAlgorithm());
	}

}
