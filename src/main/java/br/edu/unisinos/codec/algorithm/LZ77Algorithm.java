package br.edu.unisinos.codec.algorithm;

import java.util.ArrayList;
import java.util.Arrays;

public class LZ77Algorithm extends Algorithm {
	
	private static int LOOK_AHEAD_BUFFER_SIZE = 31;
	private static int WINDOW_SIZE = 255;

	public LZ77Algorithm() {
		super();
	}

	@Override
	public void compress() {
		ArrayList<Byte> bytes = new ArrayList<Byte>();
		
		int length = this.input.length;
		for (int i = 0; i < length;) {
			Match match = findMatchInSlidingWindow(this.input, i);
			
			if (match == null) {
				// No match: add 0 distance and length, and itself
				bytes.add((byte)0);
				bytes.add(this.input[i]);
			} else {
				// Match!: add distance, length and next character
				bytes.add((byte)match.getDistance());
				bytes.add((byte)match.getLength());
				i += match.getLength();
				if (i < length) {
					bytes.add(this.input[i]);
				}
			}
			
			i++;
		}

		// Format output
		int bytesSize = bytes.size();
		this.output = new byte[bytesSize];
		for (int i = 0; i < bytesSize; i++) {
		    this.output[i] = bytes.get(i).byteValue();
		}
	}

	@Override
	public void decompress() {
		ArrayList<Byte> bytes = new ArrayList<Byte>();
		
		int inputLength = this.input.length;
		for (int i = 0; i < inputLength;) {
			int distance = this.input[i++] & 0xFF;
			if (distance > 0) {
				int length = this.input[i++] & 0xFF;
				int start = bytes.size() - distance;
				int end = start + length;
				for (int j = start; j < end; j++) {
					bytes.add(bytes.get(j).byteValue());
				}
			}
			
			if (i < inputLength) {
				bytes.add(this.input[i++]);
			}
		}
		
		// Format output
		int bytesSize = bytes.size();
		this.output = new byte[bytesSize];
		for (int i = 0; i < bytesSize; i++) {
		    this.output[i] = bytes.get(i).byteValue();
		}
	}
	
	/**
	 * Finds a match of the current char + buffer in the sliding window
	 * @param data
	 * @param currentIndex
	 * @return Match OR null
	 */
	private Match findMatchInSlidingWindow(byte[] data, int currentIndex) {
		Match match = new Match();
		
		int end = Math.min(currentIndex + LOOK_AHEAD_BUFFER_SIZE, data.length + 1);
		
		for (int j = currentIndex + 1; j < end; j++) {
			int startIndex = Math.max(0, currentIndex - WINDOW_SIZE);
			byte[] bytesToMatch = Arrays.copyOfRange(data, currentIndex, j);
			
			for (int i = startIndex; i < currentIndex; i++) {
				int repeat = bytesToMatch.length / (currentIndex - i);
				int remaining = bytesToMatch.length % (currentIndex - i);

				byte[] tempArray = new byte[(currentIndex - i) * repeat + (i + remaining - i)];
				int m = 0;
				for (; m < repeat; m++) {
					int destPos = m * (currentIndex - i);
					System.arraycopy(data, i, tempArray, destPos, currentIndex - i);
				}
				
				int destPos = m * (currentIndex - i);
				System.arraycopy(data, i, tempArray, destPos, remaining);
				
				if (Arrays.equals(tempArray, bytesToMatch) && bytesToMatch.length > match.getLength()) {
					match.setLength(bytesToMatch.length);
					match.setDistance(currentIndex - i);
				}
			}
		}
		
		if (match.getLength() > 0 && match.getDistance() > 0) {
			return match;
		}
		
		return null;
	}
	
	class Match {
		private int length;
		private int distance;

		public Match() {
			this(-1, -1);
		}

		public Match(int matchLength, int matDistance) {
			this.length = matchLength;
			this.distance = matDistance;
		}

		public int getLength() {
			return length;
		}

		public void setLength(int matchLength) {
			this.length = matchLength;
		}

		public int getDistance() {
			return distance;
		}

		public void setDistance(int matDistance) {
			this.distance = matDistance;
		}

	}

}
