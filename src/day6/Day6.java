package day6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day6 {

	public static void main(String[] args) {
		Day6 instanceOfTheDay = new Day6();
		try {
			instanceOfTheDay.solve("C:\\Users\\boehm\\eclipse-workspace\\AdventOfCode2022\\src\\day6\\input.txt");
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

	boolean differentChars(char[] arrayOfChars) {
		int numberOfChars = arrayOfChars.length;
		for (int j = 0; j < numberOfChars; j++) {
			for (int i = j + 1; i < numberOfChars; i++) {
				if (arrayOfChars[j] == arrayOfChars[i]) {
					return false;
				}
			}
		}
		return true;
	}

	int solvePart1(List<String> dataList) {
		String line = dataList.get(0); // all in one line
		char[] arrayOfFourChars = new char[4];
		for (int i = 0; i < line.length() - 3; i++) {
			arrayOfFourChars[0] = line.charAt(i);
			arrayOfFourChars[1] = line.charAt(i + 1);
			arrayOfFourChars[2] = line.charAt(i + 2);
			arrayOfFourChars[3] = line.charAt(i + 3);
			if (differentChars(arrayOfFourChars)) {
				return i + 4;
			}
		}
		return 0;
	}

	int solvePart2(List<String> dataList) {
		String line = dataList.get(0); // all in one line
		char[] arrayOfFourteenChars = new char[14];
		for (int i = 0; i < line.length() - 13; i++) {
			for (int j = 0; j < 14; j++) {
				arrayOfFourteenChars[j] = line.charAt(i + j);
			}
			if (differentChars(arrayOfFourteenChars)) {
				return i + 14;
			}
		}
		return 0;
	}
}