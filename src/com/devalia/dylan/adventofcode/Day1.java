package com.devalia.dylan.adventofcode;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.TreeSet;

class Day1 {
	
	static void run_1(String inputFile) {
		try {
			InputStream in = Day1.class.getResourceAsStream(inputFile);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			String line;
			int sum = 0;
			
			while ((line = br.readLine()) != null) {
				String strnum = line.substring(1);
				int num = Integer.parseInt(strnum);
				int sign = line.charAt(0) == '-' ? -1 : 1;
				sum += num * sign;
			}
			
			System.out.println(sum);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	static void run_2(String inputFile) {
		try {
			String line;
			int sum = 0;
			TreeSet<Integer> prevSums = new TreeSet<>();
			
			do {
				InputStream in = Day1.class.getResourceAsStream(inputFile);
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				
				while ((line = br.readLine()) != null) {
					String strNum = line.substring(1);
					int num = Integer.parseInt(strNum);
					int sign = line.charAt(0) == '-' ? -1 : 1;
					sum += num * sign;
					
					if (!prevSums.contains(sum)) {
						prevSums.add(sum);
					} else {
						System.out.println(sum);
						return;
					}
				}
			} while (true);
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
