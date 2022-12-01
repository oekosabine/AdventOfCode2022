package day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Day1 {

	public static void main(String[] args) {
		Day1 instanceOfTheDay = new Day1();
		try {
			instanceOfTheDay.solve("C:\\Users\\boehm\\eclipse-workspace\\AdventOfCode2022\\src\\day1\\input.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void solve(String inputData) throws IOException {
		Path path = Paths.get(inputData);
		List<String> dataList = Files.readAllLines(path);
		int solution = solvePart1(dataList);
		System.out.println("Solution for part 1: " + solution);
		solution = solvePart2(dataList);
		System.out.println("Solution for part 2: " + solution);
	}

	int solvePart1(List<String> dataList) {
		int maxCaloryAmount = 0;
		int caloryAmount = 0;
		for (String line : dataList) {
			if (line.isBlank()) { // new elve
				if (caloryAmount > maxCaloryAmount) {
					maxCaloryAmount = caloryAmount;
				}
				caloryAmount = 0;
			} else {
				caloryAmount += Integer.parseInt(line);
			}
		}
		if (caloryAmount > maxCaloryAmount) { // Last elve ends with end of file.
			maxCaloryAmount = caloryAmount;
		}
		caloryAmount = 0;
		return maxCaloryAmount;
	}

	int solvePart2(List<String> dataList) {
		int maxCaloryAmount[] = { 0, 0, 0 };
		int caloryAmount = 0;
		for (String line : dataList) {
			if (line.isBlank()) { // new elve
				if (caloryAmount > maxCaloryAmount[0]) {
					maxCaloryAmount[2] = maxCaloryAmount[1];
					maxCaloryAmount[1] = maxCaloryAmount[0];
					maxCaloryAmount[0] = caloryAmount;
				} else if (caloryAmount > maxCaloryAmount[1]) {
					maxCaloryAmount[2] = maxCaloryAmount[1];
					maxCaloryAmount[1] = caloryAmount;
				} else if (caloryAmount > maxCaloryAmount[2]) {
					maxCaloryAmount[2] = caloryAmount;
				}
				caloryAmount = 0;
			} else {
				caloryAmount += Integer.parseInt(line);
			}
		}
		if (caloryAmount > maxCaloryAmount[0]) { // Last elve ends with end of file.
			maxCaloryAmount[2] = maxCaloryAmount[1];
			maxCaloryAmount[1] = maxCaloryAmount[0];
			maxCaloryAmount[0] = caloryAmount;
		} else if (caloryAmount > maxCaloryAmount[1]) {
			maxCaloryAmount[2] = maxCaloryAmount[1];
			maxCaloryAmount[1] = caloryAmount;
		} else if (caloryAmount > maxCaloryAmount[2]) {
			maxCaloryAmount[2] = caloryAmount;
		}
		caloryAmount = 0;
		return (maxCaloryAmount[0] + maxCaloryAmount[1] + maxCaloryAmount[2]);

	}
}
