package com.devalia.dylan.adventofcode;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.TreeSet;

class Day1 {
	
	/**
	 * From the input file '/inputs/1.txt' calculates the cumulative
	 * value, starting from 0, after adding or subtracting the number
	 * Prints the final sum.
	 */
	static void run_1() {
		try {
			InputStream in = Day1.class.getResourceAsStream("/inputs/1.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			String line;
			int sum = 0;
			
			while ((line = br.readLine()) != null) {
				String strnum = line.substring(1);
				int num = Integer.parseInt(strnum);
				int sign = line.charAt(0) == '-' ? -1 : 1;
				sum += num * sign;
			}
			
			System.out.println("1-1: " + sum);
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
	/**
	 * From the input file '/inputs/1.txt' loops through the file calculating the
	 * cumulative value until the sum has been reached twice
	 * Prints the number that was first reached twice
	 */
	static void run_2() {
		try {
			String line;
			int sum = 0;
			TreeSet<Integer> prevSums = new TreeSet<>();
			
			do {
				InputStream in = Day1.class.getResourceAsStream("/inputs/1.txt");
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				
				while ((line = br.readLine()) != null) {
					String strNum = line.substring(1);
					int num = Integer.parseInt(strNum);
					int sign = line.charAt(0) == '-' ? -1 : 1;
					sum += num * sign;
					
					if (!prevSums.contains(sum)) {
						prevSums.add(sum);
					} else {
						System.out.println("1-2: " + sum + "\n");
						return;
					}
				}
			} while (true);
			
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
