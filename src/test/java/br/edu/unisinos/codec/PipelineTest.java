package br.edu.unisinos.codec;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import br.edu.unisinos.codec.pipeline.Pipeline;

public abstract class PipelineTest {
	
	protected Pipeline pipe;
	
	protected String strInput;
	protected byte byteInput[];
	
	protected String fileNameSrc = "C:\\Users\\Lucas\\Downloads\\alice29.txt";
	protected String fileNameDestComp = "C:\\Users\\Lucas\\Downloads\\alice29.glr";
	protected String fileNameDestDecomp = "C:\\Users\\Lucas\\Downloads\\alice29.glr.txt";
	
	protected void testCompressString() {
		System.out.println(this.strInput);
		
		this.pipe.setInput(strInput.getBytes());
		this.pipe.compress();
		
		byte[] output = this.pipe.getOutput();
		
		this.pipe.setInput(output);
		this.pipe.decompress();
		
		byte[] output2 = this.pipe.getOutput();
		
		String strOutput2 = new String(output2);
		System.out.println(strOutput2);
		
		assert strInput.equals(strOutput2);
	}
	
	protected void testCompressBytes() {
		for (int i = 0; i < this.byteInput.length; i++) {
			System.out.print(this.byteInput[i] + " ");
		}
		System.out.print("\n");
		
		this.pipe.setInput(this.byteInput);
		this.pipe.compress();
		
		byte[] output = this.pipe.getOutput();
		for (int i = 0; i < output.length; i++) {
			System.out.print(output[i] + " ");
		}
		System.out.print("\n");
		
		this.pipe.setInput(output);
		this.pipe.decompress();
		
		byte[] output2 = this.pipe.getOutput();
		for (int i = 0; i < output2.length; i++) {
			System.out.print(output2[i] + " ");
			assert this.byteInput[i] == output2[i];
		}
	}
	
	protected void testCompressFile() throws IOException {
	    File file = new File(this.fileNameSrc);

	    byte [] fileBytes = Files.readAllBytes(file.toPath());
		
		this.pipe.setInput(fileBytes);
		this.pipe.compress();
		
		byte[] output = this.pipe.getOutput();
		
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
	
	protected void testDecompressFile() throws IOException {
	    File file = new File(this.fileNameDestComp);

	    byte [] fileBytes = Files.readAllBytes(file.toPath());
		
		this.pipe.setInput(fileBytes);
		this.pipe.decompress();
	    
		byte[] output = this.pipe.getOutput();
		
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
