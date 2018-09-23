package br.edu.unisinos.codec;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

import br.edu.unisinos.codec.algorithm.Algorithm;

public abstract class AlgorithmTest {
	
	protected Algorithm alg;
	
	protected String strInput;
	protected byte byteInput[];

	protected String fileNameSrc = "C:\\Users\\Lucas\\Downloads\\alice29.txt";
	protected String fileNameDestComp = "C:\\Users\\Lucas\\Downloads\\alice29.txt.glr";
	protected String fileNameDestDecomp = "C:\\Users\\Lucas\\Downloads\\alice29-d.txt";
	
	protected void testCompressString() {
		System.out.println(this.strInput);
		
		this.alg.setInput(this.strInput.getBytes());
		this.alg.compress();
		
		byte[] output = this.alg.getOutput();
		
		/*int outputLength = output.length;
		for (int i = 0; i < outputLength;) {
			int distance = output[i++];
			int length = 0;
			if (distance > 0) {
				length = output[i++];
			}
			if (i < outputLength) {
				byte[] characterArr = {output[i]};
				byte character = output[i];
				System.out.println(distance + "," + length + "," + character + "," + new String(characterArr).toString());
				i++;
			}
		}*/
		
		this.alg.setInput(output);
		this.alg.decompress();
		
		byte[] output2 = this.alg.getOutput();
		String strOutput2 = new String(output2);
		System.out.println(strOutput2);
		
		assert this.strInput.equals(strOutput2);
	}
	
	protected void testCompressBytes() {
		for (int i = 0; i < this.byteInput.length; i++) {
			System.out.print(this.byteInput[i] + " ");
		}
		System.out.print("\n");
		
		this.alg.setInput(this.byteInput);
		this.alg.compress();
		
		byte[] output = this.alg.getOutput();
		for (int i = 0; i < output.length; i++) {
			System.out.print(output[i] + " ");
		}
		System.out.print("\n");
		
		this.alg.setInput(output);
		this.alg.decompress();
		
		byte[] output2 = this.alg.getOutput();
		for (int i = 0; i < output2.length; i++) {
			System.out.print(output2[i] + " ");
			assert this.byteInput[i] == output2[i];
		}
	}
	
	protected void testCompressFile() throws IOException {
	    File file = new File(this.fileNameSrc);
	    byte [] fileBytes = Files.readAllBytes(file.toPath());
		
	    this.alg.setInput(fileBytes);
	    this.alg.compress();
		
		byte[] output = this.alg.getOutput();
		
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
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		    stream.close();
		}
	}
	
	protected void testDecompressFile() throws IOException {
	    File file = new File(this.fileNameDestComp);
	    byte [] fileBytes = Files.readAllBytes(file.toPath());
		
	    this.alg.setInput(fileBytes);
	    this.alg.decompress();
		
		byte[] output = this.alg.getOutput();
		
		FileOutputStream stream = null;
		try {
			stream = new FileOutputStream(this.fileNameDestDecomp);
		    stream.write(output);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		    stream.close();
		}
	}

}
