package br.edu.unisinos.codec.pipeline;

import br.edu.unisinos.codec.algorithm.LZ77Algorithm;

public class TextPipeline extends Pipeline {

	public TextPipeline() {
		super();
		this.list.add(new LZ77Algorithm());
	}

}
