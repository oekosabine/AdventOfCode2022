package day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Day3 {

	public static void main(String[] args) {
		Day3 instanceOfTheDay = new Day3();
		try {
			instanceOfTheDay.solve("C:\\Users\\boehm\\eclipse-workspace\\AdventOfCode2022\\src\\day3\\input.txt");
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

	int solvePart1(List<String> dataList) {
		int priority = 0;
		int sum_priority = 0;
		for (String line : dataList) {
			boolean not_stopped = true;
			int size_compartment = (line.length()) / 2;
			for (int i = 0; i < size_compartment && not_stopped; i++) {
				for (int j = size_compartment; j < line.length() && not_stopped; j++) {
					Character item_compartment1 = line.charAt(i);
					Character item_compartment2 = line.charAt(j);
					if (item_compartment1.equals(item_compartment2)) {
						int ascii = item_compartment1;
						if (ascii > 96) {
							priority = ascii - 96;
						} else {
							priority = ascii - 38;
						}
						sum_priority += priority;
						not_stopped = false;
					}

				}
			}
		}
		return sum_priority;
	}

	int solvePart2(List<String> dataList) {
		int priority = 0;
		int sum_priority = 0;
		for (int k = 0; k < dataList.size(); k = k + 3) {
			boolean not_stopped = true;
			String[] lineArray = { dataList.get(k), dataList.get(k + 1), dataList.get(k + 2) };
			for (int i = 0; i < lineArray[0].length() && not_stopped; i++) {
				for (int j = 0; j < lineArray[1].length() && not_stopped; j++) {
					for (int l = 0; l < lineArray[2].length() && not_stopped; l++) {
						Character item_line0 = lineArray[0].charAt(i);
						Character item_line1 = lineArray[1].charAt(j);
						Character item_line2 = lineArray[2].charAt(l);
						if (item_line0.equals(item_line1)) {
							if (item_line0.equals(item_line2)) {
								int ascii = item_line0;
								if (ascii > 96) {
									priority = ascii - 96;
								} else {
									priority = ascii - 38;
								}
								sum_priority += priority;
								not_stopped = false;
							}
						}

					}
				}
			}
		}
		return sum_priority;
	}
}
