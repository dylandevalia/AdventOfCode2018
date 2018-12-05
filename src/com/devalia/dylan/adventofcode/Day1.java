package com.devalia.dylan.adventofcode;

import java.util.TreeSet;

class Day1 extends AbstractDay {
	
	Day1() {
		super(1);
	}
	
	/**
	 * Calculates the cumulative value, starting from 0, after adding or subtracting the number
	 *
	 * @param input Input file '/inputs/1.txt'
	 * @return The cumulative sum of the input numbers starting from 0
	 */
	String run_1(String[] input) {
		int sum = 0;
		
		for (String line : input) {
			String stringNum = line.substring(1);
			int num = Integer.parseInt(stringNum);
			int sign = line.charAt(0) == '-' ? -1 : 1;
			sum += num * sign;
		}
		
		return Integer.toString(sum);
	}
	
	/**
	 * Loops through the input numbers until a sum that has been reached before
	 *
	 * @param input Input file '/inputs/1.txt'
	 * @return The repeated sum
	 */
	String run_2(String[] input) {
		int sum = 0;
		TreeSet<Integer> prevSums = new TreeSet<>();
		
		do {
			
			for (String line : input) {
				String strNum = line.substring(1);
				int num = Integer.parseInt(strNum);
				int sign = line.charAt(0) == '-' ? -1 : 1;
				sum += num * sign;
				
				if (!prevSums.contains(sum)) {
					prevSums.add(sum);
				} else {
					return Integer.toString(sum);
				}
			}
		} while (true);
	}
}
