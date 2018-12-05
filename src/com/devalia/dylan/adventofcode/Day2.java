package com.devalia.dylan.adventofcode;

import java.util.HashMap;
import java.util.Map.Entry;

class Day2 extends AbstractDay {
	
	Day2() {
		super(2);
	}
	
	
	/**
	 * Runs through the values from the input and checks how many have two or three repeated
	 * characters
	 *
	 * @param input Input file '/inputs/2.txt'
	 * @return The multiplication of number of values that have two repeated characters and number
	 * of values that have three repeated characters
	 */
	String run_1(String[] input) {
		int two = 0, three = 0;
		
		for (String line : input) {
			HashMap<Character, Integer> map = new HashMap<>();
			for (int i = 0; i < line.length(); i++) {
				char c = line.charAt(i);
				map.put(c, map.getOrDefault(c, 0) + 1);
			}
			
			boolean btwo = false, bthree = false;
			for (Object o : map.entrySet()) {
				Entry pair = (Entry) o;
				
				if (!btwo && ((int) pair.getValue()) == 2) {
					two++;
					btwo = true;
				}
				
				if (!bthree && ((int) pair.getValue() == 3)) {
					three++;
					bthree = true;
				}
			}
		}
		
		return Integer.toString(two * three);
	}
	
	/**
	 * Finds the two values that only differ by one character
	 *
	 * @param input Input file '/inputs/2.txt'
	 * @return The characters which the pair have in common
	 */
	String run_2(String[] input) {
		for (int i = 0; i < input.length - 1; i++) {
			for (int j = i + 1; j < input.length; j++) {
				
				boolean failed = false, found = true;
				int failedChar = 0;
				for (
					int k = 0;
					k < Math.min(input[i].length(), input[j].length());
					k++
				) {
					if (input[i].charAt(k) != input[j].charAt(k)) {
						if (!failed) {
							failed = true;
							failedChar = k;
						} else {
							// failed twice so not a match
							found = false;
							break;
						}
					}
				}
				
				if (found) {
					return input[i].substring(0, failedChar)
						.concat(input[i].substring(failedChar + 1));
				}
			}
		}
		return null;
	}
}
