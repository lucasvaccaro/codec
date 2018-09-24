package br.edu.unisinos.codec;

import java.io.IOException;
import org.junit.Test;
import br.edu.unisinos.codec.algorithm.GolombAlgorithm;
import br.edu.unisinos.codec.pipeline.Pipeline1;
import br.edu.unisinos.codec.stream.CodecStream;
import java.io.File;

public class GolombTest extends AlgorithmTest {

        
    
        public static void main(String[] args) {
            
            String fileNameSrc = "C:\\Users\\i853212\\Downloads\\alice29.txt";
            String fileNameDestComp = "C:\\Users\\i853212\\Downloads\\alice29.txt.glr";
            String fileNameDestDecomp = "C:\\Users\\i853212\\Downloads\\alice29-d.txt";
            File input = new File(fileNameSrc);
            File output = new File("C:\\Users\\i853212\\Downloads\\resultadoalice");
            CodecStream teste = new CodecStream(input, output, new Pipeline1());
            
            teste.compress();
            
        }
		
    
	public GolombTest() {
		this.alg = new GolombAlgorithm();
	}
	
	@Test
	public void testCompressBytes() {
		this.byteInput = new byte[] {32, 72, 87, 100, 100, 110, 117};
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
