package br.edu.unisinos.codec;

import java.io.IOException;
import org.junit.Test;
import br.edu.unisinos.codec.algorithm.LZ77Algorithm;

public class LZ77Test extends AlgorithmTest {
	
	public LZ77Test() {
		this.alg = new LZ77Algorithm();
	}

	//@Test
	public void testCompressString() {
		this.strInput = new String("`Found WHAT?' said the Duck.`Found IT,' the Mouse replied rather crossly: `of course you");
		super.testCompressString();
	}
	
	@Test
	public void testCompressFile() throws IOException {
		super.testCompressFile();
	}
	
	//@Test
	public void testDecompressFile() throws IOException {
		super.testDecompressFile();
	}

}
