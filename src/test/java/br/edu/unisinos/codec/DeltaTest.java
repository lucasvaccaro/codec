package br.edu.unisinos.codec;

import java.io.IOException;
import org.junit.Test;
import br.edu.unisinos.codec.algorithm.DeltaAlgorithm;

public class DeltaTest extends AlgorithmTest {
	
	public DeltaTest() {
		this.alg = new DeltaAlgorithm();
	}
	
	@Test
	public void testCompressBytes() {
		this.byteInput = new byte[] {32, 72, 87, 100, 100, 110, 117};
		super.testCompressBytes();
	}
	
	//@Test
	public void testCompressFile() throws IOException {
		super.testCompressFile();
	}
	
	//@Test
	public void testDecompressFile() throws IOException {
		super.testDecompressFile();
	}

}
