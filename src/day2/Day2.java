package day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Day2 {

	public static void main(String[] args) {
		Day2 instanceOfTheDay = new Day2();
		try {
			instanceOfTheDay.solve("C:\\Users\\boehm\\eclipse-workspace\\AdventOfCode2022\\src\\day2\\input.txt");
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

	int solvePart1(List<String> dataList) { // 10816
		int score = 0;
		for (String line : dataList) {
			Character opponent = line.charAt(0); // opponent
			Character myChoice = line.charAt(2); // my choice
			if (opponent.equals('A')) { // rock
				if (myChoice.equals('X')) { // rock
					score += 4; // 3 for draw + 1 for rock
				} else if (myChoice.equals('Y')) { // paper
					score += 8; // 2 for paper + 6 for win
				} else if (myChoice.equals('Z')) { // scissors
					score += 3;// 3 for scissors + 0 for loose
				}
			}
			if (opponent.equals('B')) { // paper
				if (myChoice.equals('X')) { // rock
					score += 1; // 0 for loose + 1 for rock
				} else if (myChoice.equals('Y')) { // paper
					score += 5; // 2 for paper + 3 for draw
				} else if (myChoice.equals('Z')) { // scissors
					score += 9;// 3 for scissors + 6 for win
				}
			}
			if (opponent.equals('C')) { // scissors
				if (myChoice.equals('X')) { // rock
					score += 7; // 6 for win + 1 for rock
				} else if (myChoice.equals('Y')) { // paper
					score += 2; // 2 for paper + 0 for loose
				} else if (myChoice.equals('Z')) { // scissors
					score += 6;// 3 for scissors + 3 for draw
				}
			}

		}
		return score;
	}

	int solvePart2(List<String> dataList) {
		int score = 0;
		for (String line : dataList) {
			Character opponent = line.charAt(0); // opponent
			Character myChoice = line.charAt(2); // my choice
			if (opponent.equals('A')) { // rock
				if (myChoice.equals('X')) { // lose -> choose scissors
					score += 3; // 0 for loose + 3 for scissors
				} else if (myChoice.equals('Y')) { // draw -> rock
					score += 4; // 1 for rock + 3 for draw
				} else if (myChoice.equals('Z')) { // win -> paper
					score += 8;// 2 for paper + 6 for win
				}
			}
			if (opponent.equals('B')) { // paper
				if (myChoice.equals('X')) { // lose -> rock
					score += 1; // 0 for loose + 1 for rock
				} else if (myChoice.equals('Y')) { // draw -> paper
					score += 5; // 2 for paper + 3 for draw
				} else if (myChoice.equals('Z')) { // win -> scissors
					score += 9;// 3 for scissors + 6 for win
				}
			}
			if (opponent.equals('C')) { // scissors
				if (myChoice.equals('X')) { // lose -> paper
					score += 2; // 0 for loose + 2 for paper
				} else if (myChoice.equals('Y')) { // draw -> scissors
					score += 6; // 3 for scissors + 3 for draw
				} else if (myChoice.equals('Z')) { // win -> rock
					score += 7;// 1 for rock + 6 for win
				}
			}

		}
		return score;
	}
}
