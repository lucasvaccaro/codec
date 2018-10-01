package br.edu.unisinos.codec.algorithm;

import java.util.ArrayList;

public class GolombAlgorithm extends Algorithm {
	
	private static int DIV = 16;
	
	private int[] positiveDecimals;
	private String compressed_string;

	public GolombAlgorithm(){
        super();
        
    }

	public void compress() {
                positiveDecimals = new int[this.input.length];
		this.getIntValues();
		this.compressed_string = new String("");
		
		// add divider
		this.compressed_string = this.compressed_string.concat("10000");

		int prefix;
		int sufix;

		for (int i = 0; i < this.positiveDecimals.length; i++) {
                    if(i == positiveDecimals.length -2){
                        int aaaaaa= 0;
                    }
			prefix = this.positiveDecimals[i] / DIV;
			sufix = this.positiveDecimals[i] % DIV;
			this.compressed_string = this.compressed_string.concat(getSequence(prefix, sufix, DIV));
		}
                
                //create output with the proper size
                int len = 0;
                if(compressed_string.length()%8 != 0){
                    len = compressed_string.length()/8 + 1;
                }else{
                    len = compressed_string.length()/8;
                }
                this.output = new byte[len];
                
                //convert int to byte for the output
                for(int i = 0; i < this.output.length; i++){
                    if(i == (this.output.length - 1)){
                        this.output[i] = (byte) Integer.parseInt(compressed_string.substring(0), 2);
                    }else{
                        this.output[i] = (byte) Integer.parseInt(compressed_string.substring(0, 8), 2);
                        compressed_string = compressed_string.substring(8);
                    }
                }
                
	}

	public void decompress() {
            
            treatDecompInput();          
            
	
                
		int div = getDivider(this.compressed_string);
		// take divider off the string to be analyzed
		this.compressed_string = this.compressed_string.substring(5);

		// calculate number
		ArrayList<Byte> bytes = new ArrayList<Byte>();
		while (this.compressed_string.length() > 0) {
                    if(this.compressed_string.length() <=8){
                        int aaaaa = 0;
                    }
			int prefix = getPrefix();
			int sufix = getSufix();
			int res = (prefix * div) + sufix;
			bytes.add((byte)res);
		}

		// Format output
		int bytesSize = bytes.size();
		this.output = new byte[bytesSize];
		for (int i = 0; i < bytesSize; i++) {
		    this.output[i] = bytes.get(i).byteValue();
		}
	}

	private String getSequence(int prefix, int sufix, int div) {
		String result = new String("");
		for (int i = 0; i < prefix; i++) {
			result = result.concat("0");
		}
		result = result.concat("1");
		String binary_xbit = Integer.toBinaryString(sufix);
		String binary_2bit = new String("");
		if (binary_xbit.length() > 4) {
			binary_2bit = binary_xbit.substring(binary_xbit.length() - 5);
		} else {
			binary_2bit = binary_xbit;
		}
		binary_2bit = formBinary4(binary_2bit);

		result = result.concat(binary_2bit);
		return result;
	}

	private int getDivider(String bin) {
		String sub = bin.substring(0, 5);
		int res = 0;
		int ind = 0;

		for (int i = sub.length() - 1; i >= 0; i--) {
			int parsed_value = Integer.parseInt(String.valueOf(sub.charAt(i)));
			int power = (int) Math.pow(2.0, ind);
			res += parsed_value * power;
			ind++;
		}

		return res;
	}

	private int getPrefix() {
		int prefix = 0;
		boolean flag = true;
		int index = 0;

		while (flag) {
			if (this.compressed_string.charAt(index) == '0') {
				prefix++;
				index++;
			} else {
				flag = false;
				this.compressed_string = this.compressed_string.substring(index + 1);
			}
		}

		return prefix;
	}

	private int getSufix() {
                String s = new String("");
                if(this.compressed_string.length() < 4){
                    s = formBinary4(this.compressed_string);
                    this.compressed_string = new String();
                }else{
                    s = this.compressed_string.substring(0, 4);
                    this.compressed_string = this.compressed_string.substring(4);
                }		
		int sufix = Integer.parseUnsignedInt(s, 2);
		return sufix;
	}

	private String formBinary(String binary) {
		String result = new String("");
		if (binary.length() < 8) {
			int dif = 8 - binary.length();
			// add zeros to form 2 bits
			for (int i = 0; i < dif; i++) {
				result = result.concat("0");
			}
			result = result.concat(binary);
		} else {
			result = binary;
		}

		return result;
	}
        
        private String formBinary4(String binary) {
		String result = new String("");
		if (binary.length() < 4) {
			int dif = 4 - binary.length();
			// add zeros to form 4 bits
			for (int i = 0; i < dif; i++) {
				result = result.concat("0");
			}
			result = result.concat(binary);
		} else {
			result = binary;
		}

		return result;
	}

	private void getPositiveDecimals() {
		this.positiveDecimals = new int[this.input.length];
		for (int i = 0; i < this.input.length; i++) {
			if (this.input[i] < 0) {
				String binary_32bit = Integer.toBinaryString(this.input[i]);
				String binary_8bit = binary_32bit.substring(24);

				int p = 0;
				positiveDecimals[i] = 0;
				for (int j = binary_8bit.length() - 1; j >= 0; j--) {
					int parsed_value = Integer.parseInt(String.valueOf(binary_8bit.charAt(i)));
					int power = (int) Math.pow(2.0, p);
					positiveDecimals[i] += parsed_value * power;
					p++;
				}
			} else {
				String binary_value = Integer.toBinaryString(this.input[i]);
				String bin = formBinary(binary_value);
				positiveDecimals[i] = Integer.parseInt(bin, 2);
			}
		}
	}
        
        private void getIntValues(){
            for(int i = 0; i < this.input.length; i++){
                positiveDecimals[i] = (int) Integer.parseInt(Integer.toString((int)this.input[i]));
            }
        }
        
        private void treatDecompInput(){
            compressed_string = new String("");
            for(int i = 0; i<this.input.length; i++){
                String bin = Integer.toBinaryString((int)this.input[i]);
                if(bin.length() > 8){
                    int dif = bin.length() - 8;
                    bin = bin.substring(dif);
                }else{
                    if(i != (this.input.length - 1))
                        bin = formBinary(bin);
                }
                compressed_string = compressed_string.concat(bin);
            }
        }

}
