package com.devalia.dylan.adventofcode;

class Day3 extends AbstractDay {
	
	private class Indexes {
		
		int at = 0, comma = 0, colon = 0, times = 0;
	}
	
	public Day3() {
		super(3);
	}
	
	private static int[][] claims = new int[1001][1001];
	
	/**
	 * From the input file '/inputs/3.txt' marks out the areas specified and calculates how many
	 * squares are used more than once Prints the number of squares used two or more times
	 */
	String run_1(String[] input) {
		int claimed = 0;
		
		for (String line : input) {
			Indexes i = getIndexes(line);
			
			int left = Integer.parseInt(line.substring(i.at + 2, i.comma));
			int top = Integer.parseInt(line.substring(i.comma + 1, i.colon));
			int width = Integer.parseInt(line.substring(i.colon + 2, i.times));
			int height = Integer.parseInt(line.substring(i.times + 1));
			
			for (int w = 0; w < width; w++) {
				for (int h = 0; h < height; h++) {
					if (++claims[left + w][top + h] == 2) {
						claimed++;
					}
				}
			}
		}
		
		return Integer.toString(claimed);
	}
	
	/**
	 * Finds the area that does not overlap with any other any other regions
	 */
	String run_2(String[] input) {
		for (String line : input) {
			Indexes i = getIndexes(line);
			
			int id = Integer.parseInt(line.substring(1, i.at - 1));
			int left = Integer.parseInt(line.substring(i.at + 2, i.comma));
			int top = Integer.parseInt(line.substring(i.comma + 1, i.colon));
			int width = Integer.parseInt(line.substring(i.colon + 2, i.times));
			int height = Integer.parseInt(line.substring(i.times + 1));
			
			boolean found = true;
			for (int w = 0; w < width; w++) {
				for (int h = 0; h < height; h++) {
					if (claims[left + w][top + h] != 1) {
						found = false;
						break;
					}
				}
				if (!found) {
					break;
				}
			}
			
			if (found) {
				return Integer.toString(id);
			}
		}
		return null;
	}
	
	private Indexes getIndexes(String line) {
		Indexes indexes = new Indexes();
		for (int i = 0; i < line.length(); i++) {
			switch (line.charAt(i)) {
				case '@':
					indexes.at = i;
					break;
				case ',':
					indexes.comma = i;
					break;
				case ':':
					indexes.colon = i;
					break;
				case 'x':
					indexes.times = i;
					break;
				default:
					break;
			}
		}
		return indexes;
	}
}
