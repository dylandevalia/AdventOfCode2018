package com.devalia.dylan.adventofcode;

class Day5 extends AbstractDay {
	
	Day5() {
		super(5);
	}
	
	/**
	 * Runs through the input and removes any lowercase and uppercase characters which are adjacent
	 * to each other
	 *
	 * @param input Input file '/inputs/5.txt'
	 * @return The input with no adjacent lower/uppercase characters
	 */
	String run_1(String[] input) {
		String line = input[0];
		
		return Integer.toString(reaction(line).length());
	}
	
	/**
	 * Finds the smallest output length after removing one letter (both upper and lowercase)
	 * entirely then performing the reaction (part one)
	 *
	 * @param input Input file '/inputs/5.txt'
	 * @return The length of the shortest output
	 */
	String run_2(String[] input) {
		int lowestValue = -1;
		
		// from A to Z
		for (int c = 65; c < 91; c++) {
			String line = input[0];
			
			for (int i = 0; i < line.length(); i++) {
				if (line.charAt(i) == c || line.charAt(i) == c + 32) {
					line = line.substring(0, i).concat(line.substring(i + 1));
					i--;
				}
			}
			
			String output = reaction(line);
			
			if (c == 65 || output.length() < lowestValue) {
				lowestValue = output.length();
			}
		}
		
		return Integer.toString(lowestValue);
	}
	
	/**
	 * Runs through the input and removes any lowercase and uppercase characters which are adjacent
	 * to each other
	 *
	 * @param input The input string
	 * @return The input with no adjacent lower/uppercase characters
	 */
	private String reaction(String input) {
		boolean complete;
		do {
			complete = true;
			
			for (int i = 0; i < input.length() - 1; i++) {
				int a = (int) input.charAt(i);
				int b = (int) input.charAt(i + 1);
				if (Math.abs(a - b) == 32) {
					input = input.substring(0, i).concat(input.substring(i + 2));
					// i = Math.max(0, i - 2);
					complete = false;
				}
			}
		} while (!complete);
		
		return input;
	}
	
}
