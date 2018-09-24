/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unisinos.codec;

import br.edu.unisinos.codec.pipeline.Pipeline1;
import br.edu.unisinos.codec.stream.CodecStream;
import java.io.File;

/**
 *
 * @author I853212
 */
public class testeGolomb {
    
     public static void main(String[] args) {
            
            String fileNameSrc = "C:\\Users\\i853212\\Downloads\\alice29.txt";
            String fileNameDestComp = "C:\\Users\\i853212\\Downloads\\alice29.txt.glr";
            String fileNameDestDecomp = "C:\\Users\\i853212\\Downloads\\alice29-d.txt";
            File input = new File(fileNameSrc);
            File output = new File("C:\\Users\\i853212\\Downloads\\resultadoalice");
            CodecStream teste = new CodecStream(input, output, new Pipeline1());
            
            teste.compress();
            
        }
    
}
