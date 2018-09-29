/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unisinos.codec.algorithm;

/**
 *
 * @author I853212
 */
public class Golomb extends AbstractCode {

    private int m;

    public Golomb(){
        super();
        m = 4;
    }

    @Override
    public String decodeBytes(String byteString) {
        code += byteString;
        int oneCount = 0;
        String textResult = "";
        String binaryResult = "";
        char key;
        textResult = loopCode(oneCount, textResult, binaryResult);
        return textResult;
    }

    private String loopCode(int oneCount, String textResult, String binaryResult) {
        char key;
        for (int i = 0; i < code.length(); i++)
            if (code.charAt(i) == '1')
                oneCount++;
            else {
                for (int j = 0; j < m / 2; j++) {
                    i++;
                    if(!(i < code.length()))
                        return textResult;
                    binaryResult += code.charAt(i);

                }
                //Quotient * 4 + Remainder + 1 (Last one to find on the hashmap)
                key = getKeyInHashmap(oneCount * 4 + Integer.parseInt(binaryResult, 2) + 1);
                textResult += key;
                binaryResult = "";
                oneCount = 0;
            }
        return textResult;
    }

    private String codeAChar(char symbol) {
        int symbolNumber = symbolNumberHashmap.get(symbol)-1;
        int quotient = symbolNumber/m;
        int remainder = symbolNumber % m;
        String codeAux = codeQuotient(quotient);
        codeAux = codeRemainder(remainder, codeAux);
        return codeAux;
    }

    private String codeRemainder(int remainder, String codeAux) {
        String binaryAux = Integer.toBinaryString(remainder);
        if(binaryAux.length() == m/2)
            codeAux += binaryAux;
        else
            codeAux += createStringWithNZeros(m/2 - binaryAux.length()) + binaryAux;
        return codeAux;
    }

    private String codeQuotient(int quotient) {
        String codeAux = "";
        //Write a q-length string of 1 bits
        for(int i = quotient; i > 0; i--){
            codeAux += "1";
        }
        //Write a 0 bit
        codeAux += "0";
        //If M is power of 2, code remainder as binary format.
        return codeAux;
    }

    @Override
    public void codeAString(String ab) {
        for (int i = 0; i < ab.length(); i++) {
            code += codeAChar(ab.charAt(i));
        }
    }

    public String toString(){
        return "golomb_";
    }
}
