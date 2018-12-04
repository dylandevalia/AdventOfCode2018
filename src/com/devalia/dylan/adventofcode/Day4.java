package com.devalia.dylan.adventofcode;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

class Day4 {
	
	private static boolean hasFirstRun = false;
	private static HashMap<Integer, Integer[]> minTracking = new HashMap<>();
	
	static void run_1() {
		String[] input = sortInput();
		if (input == null) {
			System.err.println("input is null");
			return;
		}
		
		HashMap<Integer, Integer> timeSleeping = new HashMap<>();
		int longestSleeperId = -1;
		int id = 0;
		int fellAsleep = 0;
		
		for (String s : input) {
			switch (s.charAt(19)) {
				case 'G':   // New guard
					// All ids start from char 26
					StringBuilder stringId = new StringBuilder();
					for (int i = 26; i < s.length(); i++) {
						if (s.charAt(i) == ' ') {
							break;
						}
						stringId.append(s.charAt(i));
					}
					id = Integer.parseInt(stringId.toString());
					
					if (!minTracking.containsKey(id)) {
						Integer[] m = new Integer[60];
						for (int i = 0; i < m.length; i++) {
							m[i] = 0;
						}
						minTracking.put(id, m);
					}
					break;
				case 'f':   // falls asleep
					fellAsleep = Integer.parseInt(s.substring(15, 17));
					break;
				case 'w':   // wakes up
					int wakesUp = Integer.parseInt(s.substring(15, 17));
					int time = wakesUp - fellAsleep;
					timeSleeping.put(id, timeSleeping.getOrDefault(id, 0) + time);
					
					if (
						longestSleeperId < 0
							|| timeSleeping.get(id) > timeSleeping.get(longestSleeperId)
					) {
						longestSleeperId = id;
					}
					
					Integer[] m = minTracking.get(id);
					for (int i = fellAsleep; i < wakesUp; i++) {
						m[i]++;
					}
					minTracking.put(id, m);
					
					break;
				default:
					System.err.println("Unknown statement: " + s);
					return;
			}
		}
		
		int bestMin = 0;
		Integer[] arr = minTracking.get(longestSleeperId);
		for (int i = 1; i < arr.length; i++) {
			bestMin = arr[i] > arr[bestMin] ? i : bestMin;
		}
		System.out.println("4-1: " + (longestSleeperId * bestMin));
		hasFirstRun = true;
	}
	
	static void run_2() {
		if (!hasFirstRun) {
			System.err.println("3-2: Please run Day3.run_1() first.");
			return;
		}
		
		int bestId = -1, bestMin = -1;
		for (Object o : minTracking.entrySet()) {
			Entry pair = (Entry) o;
			
			Integer[] value = (Integer[]) pair.getValue();
			for (int i = 0; i < value.length; i++) {
				if (value[i] > bestMin) {
					bestId = (int) pair.getKey();
					bestMin = i;
				}
			}
		}
		
		System.out.println("4-2: " + bestId * bestMin + "\n");
	}
	
	private static String[] sortInput() {
		try {
			InputStream in = Day2.class.getResourceAsStream("/inputs/4.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			String line;
			ArrayList<String> sorted = new ArrayList<>();
			
			while ((line = br.readLine()) != null) {
				sorted.add(line);
			}
			
			Collections.sort(sorted);
			
			// for (String s : sorted) {
			// 	System.out.println(s);
			// }
			
			return Arrays.copyOf(sorted.toArray(), sorted.size(), String[].class);
		} catch (Exception e) {
			System.err.println(e);
		}
		return null;
	}
}
