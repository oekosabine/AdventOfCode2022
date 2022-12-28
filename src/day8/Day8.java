package day8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

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

	public class Tupel {
		int index_x;
		int index_y;

		public Tupel(int x, int y) {
			index_x = x;
			index_y = y;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + Objects.hash(index_x, index_y);
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Tupel other = (Tupel) obj;
			return index_x == other.index_x && index_y == other.index_y;
		}
	}

	int solvePart1(List<String> dataList) {
		HashMap<Tupel, Boolean> tree_is_visible = new HashMap<Tupel, Boolean>();
		Integer[][] tree_matrix;
		int size, length;
		length = dataList.get(0).length();
		size = dataList.size();
		tree_matrix = new Integer[size][length];
		for (int i = 0; i < size; i++) {
			String line = dataList.get(i);
			for (int j = 0; j < length; j++) {
				Integer char_as_integer = Integer.parseInt(String.valueOf(line.charAt(j)));
				tree_matrix[i][j] = char_as_integer;
			}
		}
		for (int i = 0; i < size; i++) { // initialize with true: all trees are visible.
			for (int j = 0; j < length; j++) {
				Tupel tree = new Tupel(i, j);
				tree_is_visible.put(tree, true);
			}
		}

		for (int i = 1; i < size - 1; i++) { // from 1 to size-2 as the outside is always visible.
			for (int j = 1; j < length - 1; j++) { // from 1 to length-2 as the outside is always visible.
				boolean invisible_i_smaller = false;
				boolean invisible_i_greater = false;
				boolean invisible_j_smaller = false;
				boolean invisible_j_greater = false;

				Tupel tree = new Tupel(i, j);
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
		for (Tupel item : tree_is_visible.keySet()) {
			if (tree_is_visible.get(item)) {
				sum_visible_trees++;
			}
		}
		return sum_visible_trees;
	}

	int solvePart2(List<String> dataList) {
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
		List<Tupel> all_trees = new ArrayList<Tupel>();
		HashMap<Tupel, Integer> number_of_visible_trees_list = new HashMap<Tupel, Integer>();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < length; j++) {
				Tupel tree = new Tupel(i, j);
				all_trees.add(tree);
			}
		}
		for (Tupel tree : all_trees) {
			int[] number_of_visible_trees = new int[4];
			number_of_visible_trees[0] = 0;
			number_of_visible_trees[1] = 0;
			number_of_visible_trees[2] = 0;
			number_of_visible_trees[3] = 0;

			for (int i_smaller = tree.index_x - 1; i_smaller >= 0; i_smaller--) {
				if (tree_matrix[i_smaller][tree.index_y] < tree_matrix[tree.index_x][tree.index_y]) {
					// tree is visible
					number_of_visible_trees[0]++;
				}
				if (tree_matrix[i_smaller][tree.index_y] >= tree_matrix[tree.index_x][tree.index_y]) {
					// tree is visible, but nothing behind it
					number_of_visible_trees[0]++;
					break;
				}
			}
			for (int i_greater = tree.index_x + 1; i_greater < size; i_greater++) {
				if (tree_matrix[i_greater][tree.index_y] < tree_matrix[tree.index_x][tree.index_y]) {
					// tree is visible
					number_of_visible_trees[1]++;
				}
				if (tree_matrix[i_greater][tree.index_y] >= tree_matrix[tree.index_x][tree.index_y]) {
					// tree is visible, but nothing behind it
					number_of_visible_trees[1]++;
					break;
				}
			}
			for (int j_smaller = tree.index_y - 1; j_smaller >= 0; j_smaller--) {
				if (tree_matrix[tree.index_x][j_smaller] < tree_matrix[tree.index_x][tree.index_y]) {
					// tree is visible
					number_of_visible_trees[2]++;
				}
				if (tree_matrix[tree.index_x][j_smaller] >= tree_matrix[tree.index_x][tree.index_y]) {
					// tree is visible, but nothing behind it
					number_of_visible_trees[2]++;
					break;
				}
			}
			for (int j_greater = tree.index_y + 1; j_greater < length; j_greater++) {
				if (tree_matrix[tree.index_x][j_greater] < tree_matrix[tree.index_x][tree.index_y]) {
					// tree is visible
					number_of_visible_trees[3]++;
				}
				if (tree_matrix[tree.index_x][j_greater] >= tree_matrix[tree.index_x][tree.index_y]) {
					// tree is visible, but nothing behind it
					number_of_visible_trees[3]++;
					break;
				}
			}
			int product = number_of_visible_trees[0] * number_of_visible_trees[1] * number_of_visible_trees[2]
					* number_of_visible_trees[3];
			number_of_visible_trees_list.put(tree, product);
		}
		int result = 0;
		for (int number : (number_of_visible_trees_list.values())) {
			if (number > result)
				result = number;
		}
		return result;
	}
}