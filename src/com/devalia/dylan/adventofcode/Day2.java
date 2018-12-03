package com.devalia.dylan.adventofcode;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

class Day2 {
	
	/**
	 * From the input file '/inputs/2.txt' runs through the list of values and checks how many
	 * have two or three repeated characters. Prints the multiplication of number of values that
	 * have two repeated characters and number of values that have three repeated characters
	 */
	static void run_1() {
		try {
			InputStream in = Day2.class.getResourceAsStream("/inputs/2.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			String line;
			int two = 0, three = 0;
			
			while ((line = br.readLine()) != null) {
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
			
			System.out.println("2-1: " + two * three);
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
	/**
	 * From the input file '/inputs/2.txt' finds the two values which are the same bar one
	 * letter Prints the characters that the values have in common (in order)
	 */
	static void run_2() {
		
		try {
			InputStream in = Day2.class.getResourceAsStream("/inputs/2.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			ArrayList<String> strings = new ArrayList<>();
			
			String line;
			while ((line = br.readLine()) != null) {
				strings.add(line);
			}
			
			for (int i = 0; i < strings.size() - 1; i++) {
				for (int j = i + 1; j < strings.size(); j++) {
					
					boolean failed = false, found = true;
					int failedChar = 0;
					for (
						int k = 0;
						k < Math.min(strings.get(i).length(), strings.get(j).length());
						k++
					) {
						if (strings.get(i).charAt(k) != strings.get(j).charAt(k)) {
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
						String answer =
							strings.get(i).substring(0, failedChar)
								.concat(strings.get(i).substring(failedChar + 1));
						System.out.println("2-2: " + answer + "\n");
						return;
					}
				}
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
