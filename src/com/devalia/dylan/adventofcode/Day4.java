package com.devalia.dylan.adventofcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;

class Day4 extends AbstractDay {
	
	Day4() {
		super(4);
	}
	
	private static HashMap<Integer, Integer[]> minTracking = new HashMap<>();
	
	/**
	 * Sorts the input and then finds the guard that has slept the most and which minute of time
	 * they slept the most
	 *
	 * @param input Input file '/inputs/4.txt'
	 * @return The guard that has slept the most's id multiplied by the minute of the hour they
	 * slept the most
	 */
	String run_1(String[] input) {
		input = sortInput(input);
		
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
					return null;
			}
		}
		
		int bestMin = 0;
		Integer[] arr = minTracking.get(longestSleeperId);
		for (int i = 1; i < arr.length; i++) {
			bestMin = arr[i] > arr[bestMin] ? i : bestMin;
		}
		return Integer.toString(longestSleeperId * bestMin);
	}
	
	/**
	 * Finds the minute that the guards are most often asleep
	 *
	 * @param input Input file '/inputs/4.txt'
	 * @return The id of the guard that slept the most in a min multiplied by the minute
	 */
	String run_2(String[] input) {
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
		
		return Integer.toString(bestId * bestMin);
	}
	
	/**
	 * Sorts the input into descending time
	 *
	 * @param input The input file
	 * @return The sorted input
	 */
	private String[] sortInput(String[] input) {
		
		ArrayList<String> sorted = new ArrayList<>(Arrays.asList(input));
		
		Collections.sort(sorted);
		
		return Arrays.copyOf(sorted.toArray(), sorted.size(), String[].class);
	}
}
