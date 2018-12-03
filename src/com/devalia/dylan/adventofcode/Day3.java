package com.devalia.dylan.adventofcode;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

class Day3 {
	
	private static int[][] claims = new int[1001][1001];
	private static boolean hasFirstRun = false;
	
	/**
	 * From the input file '/inputs/3.txt' marks out the areas specified and calculates
	 * how many squares are used more than once
	 * Prints the number of squares used two or more times
	 */
	static void run_1() {
		try {
			InputStream in = Day3.class.getResourceAsStream("/inputs/3.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			String line;
			int claimed = 0;
			
			while ((line = br.readLine()) != null) {
				int at = 0, comma = 0, colon = 0, times = 0;
				for (int i = 0; i < line.length(); i++) {
					switch (line.charAt(i)) {
						case '@':
							at = i;
							break;
						case ',':
							comma = i;
							break;
						case ':':
							colon = i;
							break;
						case 'x':
							times = i;
							break;
						default:
							break;
					}
				}
				
				int left = Integer.parseInt(line.substring(at + 2, comma));
				int top = Integer.parseInt(line.substring(comma + 1, colon));
				int width = Integer.parseInt(line.substring(colon + 2, times));
				int height = Integer.parseInt(line.substring(times + 1));
				
				for (int w = 0; w < width; w++) {
					for (int h = 0; h < height; h++) {
						if (++claims[left + w][top + h] == 2) {
							claimed++;
						}
					}
				}
			}
			
			hasFirstRun = true;
			System.out.println("3-1: " + claimed);
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
	/**
	 * Finds the area that does not overlap with any other any other regions
	 */
	static void run_2() {
		if (!hasFirstRun) {
			System.err.println("3-2: Please run Day3.run_1() first.");
		}
		
		try {
			InputStream in = Day3.class.getResourceAsStream("/inputs/3.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			String line;
			
			while ((line = br.readLine()) != null) {
				int at = 0, comma = 0, colon = 0, times = 0;
				for (int i = 0; i < line.length(); i++) {
					switch (line.charAt(i)) {
						case '@':
							at = i;
							break;
						case ',':
							comma = i;
							break;
						case ':':
							colon = i;
							break;
						case 'x':
							times = i;
							break;
						default:
							break;
					}
				}
				
				int id = Integer.parseInt(line.substring(1, at - 1));
				int left = Integer.parseInt(line.substring(at + 2, comma));
				int top = Integer.parseInt(line.substring(comma + 1, colon));
				int width = Integer.parseInt(line.substring(colon + 2, times));
				int height = Integer.parseInt(line.substring(times + 1));
				
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
					System.out.println("3-2: " + id);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
