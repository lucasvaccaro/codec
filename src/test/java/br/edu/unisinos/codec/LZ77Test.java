package br.edu.unisinos.codec;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

import org.junit.Test;

import br.edu.unisinos.codec.algorithm.Algorithm;
import br.edu.unisinos.codec.algorithm.LZ77Algorithm;

public class LZ77Test {
	
	private String fileNameSrc = "C:\\Users\\Lucas\\Downloads\\alice29.txt";
	private String fileNameDestComp = "C:\\Users\\Lucas\\Downloads\\alice29.glr";
	private String fileNameDestDecomp = "C:\\Users\\Lucas\\Downloads\\alice29.glr.txt";

	@Test
	public void testLZ77() {
		String strInput = new String("`Found WHAT?' said the Duck.`Found IT,' the Mouse replied rather crossly: `of course you");
		System.out.println(strInput);
		
		Algorithm lz = new LZ77Algorithm();
		lz.setInput(strInput.getBytes());
		lz.compress();
		
		byte[] output = lz.getOutput();
		int outputLength = output.length;
		for (int i = 0; i < outputLength;) {
			int distance = output[i++];
			int length = 0;
			if (distance > 0) {
				length = output[i++];
			}
			byte[] characterArr = {output[i]};
			byte character = output[i];
			System.out.println(distance + "," + length + "," + character + "," + new String(characterArr).toString());
			i++;
		}
		
		Algorithm lz2 = new LZ77Algorithm();
		lz2.setInput(output);
		lz2.decompress();
		
		byte[] output2 = lz2.getOutput();
		String strOutput2 = new String(output2);
		System.out.println(strOutput2);
		
		assert strInput.equals(strOutput2);
	}
	
	@Test
	public void testCompressFile() throws IOException {
		
	    File file = new File(this.fileNameSrc);

	    byte [] fileBytes = Files.readAllBytes(file.toPath());
		
	    Algorithm alg = new LZ77Algorithm();
	    alg.setInput(fileBytes);
	    alg.compress();
		
		byte[] output = alg.getOutput();
		
		/*int outputLength = output.length;
		for (int i = 0; i < outputLength;) {
			int distance = output[i++] & 0xFF;
			int length = 0;
			if (distance > 0) {
				length = output[i++] & 0xFF;
			}
			byte character = output[i++];
			byte[] characterArr = {character};
			System.out.println(distance + "," + length + "," + character + "," + new String(characterArr).toString());
		}*/
		
		FileOutputStream stream = null;
		try {
			stream = new FileOutputStream(this.fileNameDestComp);
		    stream.write(output);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		    stream.close();
		}
	}
	
	@Test
	public void testDecompressFile() throws IOException {
		
	    File file = new File(this.fileNameDestComp);

	    byte [] fileBytes = Files.readAllBytes(file.toPath());
		
	    Algorithm alg = new LZ77Algorithm();
	    alg.setInput(fileBytes);
	    alg.decompress();
		
		byte[] output = alg.getOutput();
		
		FileOutputStream stream = null;
		try {
			stream = new FileOutputStream(this.fileNameDestDecomp);
		    stream.write(output);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		    stream.close();
		}
	}

}
