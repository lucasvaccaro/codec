package br.edu.unisinos.codec.pipeline;

import br.edu.unisinos.codec.algorithm.DeltaAlgorithm;
import br.edu.unisinos.codec.algorithm.LZ77Algorithm;

public class Pipeline1 extends Pipeline {

	public Pipeline1() {
		super();
		this.list.add(new LZ77Algorithm());
		this.list.add(new DeltaAlgorithm());
	}

}
