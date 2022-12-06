package day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Day4 {

	public static void main(String[] args) {
		Day4 instanceOfTheDay = new Day4();
		try {
			instanceOfTheDay.solve("C:\\Users\\boehm\\eclipse-workspace\\AdventOfCode2022\\src\\day4\\input.txt");
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
		int count = 0;
		for (String line : dataList) {
			String[] lineAsArray = line.split(",");
			String elve1 = lineAsArray[0];
			String elve2 = lineAsArray[1];
			String[] elve1AsArray = elve1.split("-");
			String[] elve2AsArray = elve2.split("-");
			if (Integer.parseInt(elve1AsArray[0]) == Integer.parseInt(elve2AsArray[0])
					|| Integer.parseInt(elve1AsArray[1]) == Integer.parseInt(elve2AsArray[1])) {
				count++;
			}
			if (Integer.parseInt(elve1AsArray[0]) < Integer.parseInt(elve2AsArray[0])) {
				if (Integer.parseInt(elve1AsArray[1]) > Integer.parseInt(elve2AsArray[1])) {
					count++;
				}
			}
			if (Integer.parseInt(elve2AsArray[0]) < Integer.parseInt(elve1AsArray[0])) {
				if (Integer.parseInt(elve2AsArray[1]) > Integer.parseInt(elve1AsArray[1])) {
					count++;
				}
			}
		}
		return count;
	}

	int solvePart2(List<String> dataList) {
		int count = 0;
		for (String line : dataList) {
			String[] lineAsArray = line.split(",");
			String elve1 = lineAsArray[0];
			String elve2 = lineAsArray[1];
			String[] elve1AsArray = elve1.split("-");
			String[] elve2AsArray = elve2.split("-");
			if (Integer.parseInt(elve1AsArray[1]) >= Integer.parseInt(elve2AsArray[0])) {
				if (Integer.parseInt(elve2AsArray[1]) >= Integer.parseInt(elve1AsArray[0])) {
					count++;
				}
			}
		}
		return count;
	}
}
