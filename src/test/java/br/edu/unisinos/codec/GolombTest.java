package br.edu.unisinos.codec;

import java.io.IOException;
import org.junit.Test;
import br.edu.unisinos.codec.algorithm.GolombAlgorithm;

public class GolombTest extends AlgorithmTest {
	
	public GolombTest() {
		this.alg = new GolombAlgorithm();
	}
	
	//@Test
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
