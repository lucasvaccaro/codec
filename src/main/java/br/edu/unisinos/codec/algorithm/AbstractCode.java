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
import java.util.HashMap;

/**
 * Created by I852780 on 22/09/2016.
 */
public abstract class AbstractCode implements CodeMethod {

    protected String code;
    protected HashMap<Character, Integer> frequencyHashmap;
    protected HashMap<Character, Integer> symbolNumberHashmap;

    public AbstractCode() {
        frequencyHashmap = new HashMap<>();
        symbolNumberHashmap = new HashMap<>();
        code = "";
    }

    @Override
    public void setStringToMap(String string) {
        for (char aChar : string.toCharArray()) putCharToMap(aChar);
    }

    private void putCharToMap(char aChar) {
        if (frequencyHashmap.containsKey(aChar))
            updateExistingChar(aChar);
        else
            frequencyHashmap.put(aChar, 1);
    }

    private void updateExistingChar(char aChar) {
        int numberOfTimesAppearingInText = frequencyHashmap.get(aChar);
        numberOfTimesAppearingInText = numberOfTimesAppearingInText + 1;
        frequencyHashmap.put(aChar, numberOfTimesAppearingInText);
    }

    @Override
    public void generateSymbolNumberHashmap() {
        char frequentKey = 'a';
        int highestFrequency = 0;
        int initialSize = frequencyHashmap.size();
        for (int i = 0; i < initialSize; i++) {
            for (char key : frequencyHashmap.keySet()) {
                //Chars with more frequency get less n# value
                if (frequencyHashmap.get(key) > highestFrequency) {
                    highestFrequency = frequencyHashmap.get(key);
                    frequentKey = key;
                }
            }
            symbolNumberHashmap.put(frequentKey, i + 1);
            frequencyHashmap.remove(frequentKey);
            highestFrequency = 0;
        }
    }

    public String getCode() {
        return code;
    }

    @Override
    public byte[] getCodeLikeByteArray(boolean flagIsLastLine) {
        double codeLength = code.length() / 8.0;
        int byteArrayLength = ((int) Math.ceil(codeLength));
        byte[] bytesInCode;//Size of Code/8 (bits of byte)
        if(!flagIsLastLine)
            byteArrayLength--;
        bytesInCode = new byte[byteArrayLength];
        for (int nextByte = 0; nextByte < bytesInCode.length; nextByte++)
            putBytesInArray(bytesInCode, nextByte, flagIsLastLine);
        if(!flagIsLastLine)
            code = code.substring(byteArrayLength*8, code.length());
        return bytesInCode;
    }

    private void putBytesInArray(byte[] bytesInCode, int nextByte, boolean flagIsLastLine) {
        if (flagIsLastLine && nextByte == bytesInCode.length - 1)
            bytesInCode[nextByte] = getNextNBitsOfCodeThenAddZeros(nextByte, code.length() - nextByte * 8);
        else
            bytesInCode[nextByte] = getNext8BitsOfCode(nextByte);
    }

    private byte getNextNBitsOfCodeThenAddZeros(int nextByte, int numberOfCharsLeft) {
        String partialCode = code.substring(nextByte * 8, nextByte * 8 + numberOfCharsLeft);
        partialCode += createStringWithNZeros(8 - numberOfCharsLeft);
        return (byte) Integer.parseInt(partialCode, 2);
    }

    private byte getNext8BitsOfCode(int nextByte) {
        return (byte) Integer.parseInt(code.substring(nextByte * 8, nextByte * 8 + 8), 2);
    }


    protected String createStringWithNZeros(int numberOfZeros) {
        String filledWithZeros = "";
        for (int i = 0; i < numberOfZeros; i++) {
            filledWithZeros = filledWithZeros + "0";
        }
        return filledWithZeros;
    }

    @Override
    public void recoverHashmap(String string) {
        int fakeFrequency = string.length();
        for (int i = 0; i < string.length(); i++) {
            frequencyHashmap.put(string.charAt(i), fakeFrequency);
            fakeFrequency--;
        }
        generateSymbolNumberHashmap();
    }

    @Override
    public HashMap<Character, Integer> getHashMap() {
        return symbolNumberHashmap;
    }

    protected char getKeyInHashmap(int keyNumber) {
        for (char key : symbolNumberHashmap.keySet())
            if (symbolNumberHashmap.get(key) == keyNumber) {
                return key;
            }
        return ' ';
    }

    @Override
    public void cleanCode(){
        code = "";
    }

    public abstract String decodeBytes(String string);
    public abstract void codeAString(String ab);
}
