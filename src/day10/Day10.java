package day10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;

import day8.Day8.Tupel;

public class Day10 {

	public static void main(String[] args) {
		Day10 instanceOfTheDay = new Day10();
		try {
			instanceOfTheDay.solve("C:\\Users\\selig\\Documents\\GitHub\\AdventOfCode2022\\src\\day10\\input.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<String> readData(String inputData) throws IOException {
		Path path = Paths.get(inputData);
		List<String> dataList = Files.readAllLines(path);
		return dataList;
	}

	private void solve(String inputData) throws IOException {
		List<String> dataList = readData(inputData);
		int solution = solvePart1(dataList);
		System.out.println("Solution for part 1: " + solution);
		solution = solvePart2(dataList);
		System.out.println("Solution for part 2: " + solution);
	}
	
	public int solvePart1(List<String> dataList) {
		HashMap<Integer, Integer> cycle_and_value = new HashMap<Integer, Integer>();
		int register_cycle = 0;
		int register_value = 1;
		cycle_and_value.put(register_cycle, register_value);
		int signal_strength = 0;
		for (String line : dataList) {
		if (line.equals("noop")) {
			register_cycle++;
			cycle_and_value.put(register_cycle, register_value);
		}
		else {
			String[] lineSplittet = line.split(" ");
			int valueToAdd = Integer.parseInt(lineSplittet[1]);
			register_cycle++;
			cycle_and_value.put(register_cycle, register_value);
			register_cycle++;
			cycle_and_value.put(register_cycle, register_value);
			register_value += valueToAdd;
		}
		}
		for (int i=20 ; i < 221; i+=40) {
			signal_strength += cycle_and_value.get(i) * i; 
		}
		return signal_strength;
	}

	public int solvePart2(List<String> dataList) {
		return 0;
	}
}