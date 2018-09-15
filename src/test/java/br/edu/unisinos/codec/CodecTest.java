package br.edu.unisinos.codec;

import org.junit.Test;

import br.edu.unisinos.codec.algorithm.Algorithm;
import br.edu.unisinos.codec.algorithm.LZ77Algorithm;

public class CodecTest {
	
	@Test
	public void lz77Test() {
		String s = new String("`Found WHAT?' said the Duck.`Found IT,' the Mouse replied rather crossly: `of course you");
		Algorithm lz = new LZ77Algorithm();
		lz.setInput(s.getBytes());
		lz.compress();
		
		byte[] output = lz.getOutput();
		int outputLength = output.length;
		for (int i = 0; i < outputLength;) {
			int distance = output[i++];
			int length = output[i++];
			byte character = output[i++];
			System.out.println(distance + "," + length + "," + character);
		}
		
		Algorithm lz2 = new LZ77Algorithm();
		lz2.setInput(output);
		lz2.decompress();
		
		byte[] output2 = lz2.getOutput();
		System.out.println(new String(output2).toString());
	}
	
	@Test
	public void compressText() {
		
	}
	
	@Test
	public void decompressText() {
		// TODO Auto-generated constructor stub
	}
	
	@Test
	public void compressBinary() {
		// TODO Auto-generated constructor stub
	}

	@Test
	public void decompressBinary() {
		// TODO Auto-generated constructor stub
	}
}
