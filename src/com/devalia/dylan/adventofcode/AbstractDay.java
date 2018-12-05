package com.devalia.dylan.adventofcode;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

abstract class AbstractDay {
	
	
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
	
	abstract String run_1(String[] input);
	
	abstract String run_2(String[] input);
}