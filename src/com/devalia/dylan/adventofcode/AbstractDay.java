package com.devalia.dylan.adventofcode;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

abstract class AbstractDay {
	
	/**
	 * Reads the input file and stores it in a {@code String[]}. Then runs the {@link
	 * #run_1(String[])} and {@link #run_2(String[])} methods which need to be implemented but the
	 * child classes. They correspond to the first and second part of each puzzle of the day
	 *
	 * @param day The numerical day that the child puzzle will complete
	 */
	AbstractDay(int day) {
		String[] input;
		try {
			InputStream in = getClass().getResourceAsStream("/inputs/" + day + ".txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			String line;
			ArrayList<String> list = new ArrayList<>();
			while ((line = br.readLine()) != null) {
				list.add(line);
			}
			
			input = Arrays.copyOf(list.toArray(), list.size(), String[].class);
			br.close();
			in.close();
		} catch (Exception e) {
			System.err.println(e);
			return;
		}
		
		String one = run_1(input);
		System.out.println(day + "-1: " + ((one == null) ? "null" : one));
		
		String two = run_2(input);
		System.out.println(day + "-2: " + ((two == null) ? "null" : two) + "\n");
	}
	
	/**
	 * Completes part one of the daily challenge
	 *
	 * @param input Input file '/inputs/_.txt'
	 * @return The answer to the challenge
	 */
	abstract String run_1(String[] input);
	
	/**
	 * Completes part two of the daily challenge
	 *
	 * @param input Input file '/inputs/_.txt'
	 * @return The answer to the challenge
	 */
	abstract String run_2(String[] input);
}