package br.edu.unisinos.codec.algorithm;
import java.util.*;

public class GolombAlgorithm extends Algorithm {
    
    public int[] positiveDecimals;
    String zeros; 
    
    public GolombAlgorithm(){
        super();
    }

   Integer divisor = 4;

    public int getDivisor() {
        return divisor == null ? 4 : divisor;
    }

    public void setDivisor(Integer b) {
        this.divisor = b;
    }
    
    public static String unary(Integer n) {
		String un = "";
		for (int i = 0; i < n; i++) {
			un = un + "0";
		}
		return un;
	}
    
    public static String toBinary(int decimal) {
		String result = "";
		for (int i = 0; i < 8; i++) {
			if (decimal % 2 == 0)
				result = "0" + result;
			else
				result = "1" + result;
			decimal /= 2;
		}
		return result;
	}
    
    @Override
    
	public void compress() {
	
            positiveDecimals = new int[this.input.length];
            getPositiveDecimals();
            ArrayList<Byte> bytes = new ArrayList<Byte>();
            
            for(int k=0; k<positiveDecimals.length; k++){
            
                Integer q = positiveDecimals[k] / divisor;
		Integer r = positiveDecimals[k] % divisor;

		//String i = Integer.toBinaryString(r);
		String str = unary(q);

		// stopBit
		str = str + "1";
                
                if(r == 0){
                    str = str+"00";
                } else if (r == 1){
                    str = str+"01";
                } else if (r == 2){
                    str = str+"10";
                } else if (r == 3){
                    str = str+"11";
                }
                
                bytes.add(Byte.parseByte(str,2));
            }
               
                
		// Format output
		int bytesSize = bytes.size();
		this.output = new byte[bytesSize];
		for (int j = 0; j < bytesSize; j++) {
                    System.out.println(bytes.get(j).byteValue());
		    this.output[j] = bytes.get(j).byteValue();
		}
            
            }
            
	

	@Override
	public void decompress() {
		ArrayList<Byte> bytes = new ArrayList<Byte>();
                int temp = 0;
                String temp_resto = "";
                String input_bits = "";
                
		int inputLength = this.input.length;
                
		for (int i = 0; i < inputLength; i++) {
                
                    input_bits = input_bits + Integer.toBinaryString(input[i]);
                    
                }
                
                for(int i = 0; i < input_bits.length(); i++){
                    
                    if(input_bits.charAt(i) == '0'){
                        temp++;
                    } else {
                        temp = temp * 4;
                        temp_resto = ""+input_bits.charAt(i+1)+input_bits.charAt(i+2);
                        
                        if (temp_resto.equals("11")){
                            temp = temp+3;
                        } else if (temp_resto.equals("10")){
                            temp = temp+2;
                        } else if (temp_resto.equals("01")){
                            temp = temp+1;
                        }
                        
                        bytes.add((byte)temp);
                        
                        i = i+2;
                        
                        temp = 0;
                        temp_resto = "";
                        
                    }
                    i++;
                }
                    
                    // Format output
                    int bytesSize = bytes.size();
                    this.output = new byte[bytesSize];
                    for (int i = 0; i < bytesSize; i++) {
                        System.out.println(bytes.get(i).byteValue());
                        this.output[i] = bytes.get(i).byteValue();
                    }
                        
                }
    
    private void getPositiveDecimals(){
        for(int i = 0; i < this.input.length; i++){
            if(this.input[i] < 0){ 
                String binary_32bit = Integer.toBinaryString(this.input[i]);
                String binary_8bit = binary_32bit.substring(24);
                
                int p = 0;
                positiveDecimals[i] = 0;
                for(int j = binary_8bit.length() - 1; j >= 0; j--){
                   int parsed_value = Integer.parseInt(String.valueOf(binary_8bit.charAt(i)));
                   int power = (int) Math.pow(2.0, p);
                    positiveDecimals[i] += parsed_value*power;
                    p++;
                }                
            }else{                 
                String binary_value = Integer.toBinaryString(this.input[i]);
                String bin = formBinary(binary_value);
                positiveDecimals[i] = Integer.parseInt(bin, 2);            
            }
        }        
    }
    
    private String formBinary (String binary){
        String result = new String("");
        if(binary.length() < 8){
            int dif = 8 - binary.length();
            //add zeros to form 8 bits
            for(int i = 0; i < dif; i++){
                result = result.concat("0");
            }
            result = result.concat(binary);
        }        
        return result;
    }
    
}
