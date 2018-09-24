package br.edu.unisinos.codec.algorithm;
import java.math.BigInteger;
import java.util.ArrayList;

public class GolombAlgorithm extends Algorithm {
    
    public int[] positiveDecimals;
    private final int divisor;
     
    public GolombAlgorithm(){
        super();
        positiveDecimals = new int[this.input.length];
        this.divisor = 4;
    }

    public int getDivisor() {
            return divisor;
    }
    
    @Override
    
	public void compress() {
	
            ArrayList<Byte> bytes = new ArrayList<Byte>();
            
            for(int i=0; i<positiveDecimals.length; i++){
            
                int q = positiveDecimals[i] / divisor;
		int r = positiveDecimals[i] - q * divisor;
		
                bytes.add((byte)q);
                bytes.add((byte)r);
                
		// Format output
		int bytesSize = bytes.size();
		this.output = new byte[bytesSize];
		for (int j = 0; j < bytesSize; j++) {
		    this.output[j] = bytes.get(j).byteValue();
		}
            
            }
            
		
	}

	@Override
	public void decompress() {
		// TODO Auto-generated method stub
		
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
