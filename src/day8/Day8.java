package day8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class Day8 {

	public static void main(String[] args) {
		Day8 instanceOfTheDay = new Day8();
		try {
			instanceOfTheDay.solve("C:\\Users\\boehm\\eclipse-workspace\\AdventOfCode2022\\src\\day8\\input.txt");
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
		int length = dataList.get(0).length();
		int size = dataList.size();
		Integer[][] tree_matrix = new Integer[size][length];
		for (int i = 0; i < size; i++) {
			String line = dataList.get(i);
			for (int j = 0; j < length; j++) {
				Integer char_as_integer = Integer.parseInt(String.valueOf(line.charAt(j)));
				tree_matrix[i][j] = char_as_integer;
			}
		}
		HashMap<String, Boolean> tree_is_visible = new HashMap<String, Boolean>();
		for (int i = 0; i < size; i++) { // initialize with true: all trees are visible.
			for (int j = 0; j < length; j++) {
				String tree = "(" + i + "," + j + ")";
				tree_is_visible.put(tree, true);
			}
		}

		for (int i = 1; i < size - 1; i++) { // from 1 to size-2 as the outside is always visible.
			for (int j = 1; j < length - 1; j++) { // from 1 to length-2 as the outside is always visible.
				boolean invisible_i_smaller = false;
				boolean invisible_i_greater = false;
				boolean invisible_j_smaller = false;
				boolean invisible_j_greater = false;

				String tree = "(" + i + "," + j + ")";
				for (int i_smaller = 0; i_smaller < i; i_smaller++) {
					if (tree_matrix[i_smaller][j] >= tree_matrix[i][j]) {
						invisible_i_smaller = true; // not visible for smaller i
					}
				}
				for (int i_greater = i + 1; i_greater < size; i_greater++) {
					if (tree_matrix[i_greater][j] >= tree_matrix[i][j]) {
						invisible_i_greater = true; // not visible for greater i
					}
				}
				for (int j_smaller = 0; j_smaller < j; j_smaller++) {
					if (tree_matrix[i][j_smaller] >= tree_matrix[i][j]) {
						invisible_j_smaller = true; // not visible for smaller j
					}
				}
				for (int j_greater = j + 1; j_greater < length; j_greater++) {
					if (tree_matrix[i][j_greater] >= tree_matrix[i][j]) {
						invisible_j_greater = true; // not visible for greater j
					}
				}
				if (invisible_i_smaller && invisible_i_greater && invisible_j_smaller && invisible_j_greater)
					// not visible from all sides
					tree_is_visible.put(tree, false);
			}
		}

		int sum_visible_trees = 0;
		for (String item : tree_is_visible.keySet()) {
			if (tree_is_visible.get(item)) {
				sum_visible_trees++;
			}
		}
		return sum_visible_trees;
	}

	int solvePart2(List<String> dataList) {
		return 0;
	}
}