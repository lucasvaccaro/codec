package br.edu.unisinos.codec;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

import org.junit.Test;

import br.edu.unisinos.codec.pipeline.Pipeline;
import br.edu.unisinos.codec.pipeline.TextPipeline;

public class CodecTest {
	
	private String fileNameSrc = "C:\\Users\\Lucas\\Downloads\\alice29.txt";
	private String fileNameDestComp = "C:\\Users\\Lucas\\Downloads\\alice29.glr";
	private String fileNameDestDecomp = "C:\\Users\\Lucas\\Downloads\\alice29.glr.txt";
	
	@Test
	public void pipelineTest() {
		String strInput = new String("`Found WHAT?' said the Duck.`Found IT,' the Mouse replied rather crossly: `of course you");
		
		Pipeline pipe = new TextPipeline();
		pipe.setInput(strInput.getBytes());
		pipe.compress();
		
		byte[] output = pipe.getOutput();
		for (int i = 0; i < output.length; i++) {
			System.out.print(output[i] + " ");
		}
	}
	
	@Test
	public void pipelineCompressFileTest() throws IOException {
	    File file = new File(this.fileNameSrc);

	    byte [] fileBytes = Files.readAllBytes(file.toPath());
		
		Pipeline pipe = new TextPipeline();
		pipe.setInput(fileBytes);
		pipe.compress();
		
		byte[] output = pipe.getOutput();
		
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
	public void pipelineDecompressFileTest() throws IOException {
	    File file = new File(this.fileNameDestComp);

	    byte [] fileBytes = Files.readAllBytes(file.toPath());
		
		Pipeline pipe = new TextPipeline();
		pipe.setInput(fileBytes);
		pipe.decompress();
	    
		byte[] output = pipe.getOutput();
		
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
