package com.devalia.dylan.adventofcode;

import java.util.TreeSet;

class Day1 extends AbstractDay {
	
	Day1() {
		super(1);
	}
	
	/**
	 * From the input file '/inputs/1.txt' calculates the cumulative value, starting from 0, after
	 * adding or subtracting the number Prints the final sum.
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
	 * From the input file '/inputs/1.txt' loops through the file calculating the cumulative value
	 * until the sum has been reached twice Prints the number that was first reached twice
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
