package br.edu.unisinos.codec;

import java.io.IOException;
import org.junit.Test;
import br.edu.unisinos.codec.pipeline.Pipeline1;

public class Pipeline1Test extends PipelineTest {

	public Pipeline1Test() {
		this.pipe = new Pipeline1();
	}
	
	//@Test
	public void testCompressString() {
		this.strInput = new String("`Found WHAT?' said the Duck.`Found IT,' the Mouse replied rather crossly: `of course you");
		super.testCompressString();
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
	
	@Test
	public void testDecompressFile() throws IOException {
		super.testDecompressFile();
	}

}
