package day20;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

public class Day20 {

	public static void main(String[] args) {
		Day20 instanceOfTheDay = new Day20();
		try {
			instanceOfTheDay.solve("C:\\Users\\boehm\\eclipse-workspace\\AdventOfCode2022\\src\\day20\\input.txt");
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

	int[] coordinates;
	int[] coordinates_copy;
	HashMap<Integer, Integer> where_is_it = new HashMap<>();

	public int solvePart1(List<String> dataList) {
		coordinates = new int[dataList.size()];
		int i = 0;
		for (String line : dataList) {
			Integer number = Integer.parseInt(line);
			coordinates[i] = number;
			i++;
		}
		int length = coordinates.length;
		coordinates_copy = new int[length];
		for (int j = 0; j < length; j++) {
			coordinates_copy[j] = coordinates[j];
		}
		for (int j = 0; j < length; j++) {
			where_is_it.put(j, j);
		}
		for (int j = 0; j < length; j++) {
			int number_to_move = coordinates_copy[j];
			int place = where_is_it.get(j);
			if (number_to_move < 0) {
				move(j, length, length + number_to_move - 1, place);
			} else {
				if (number_to_move + place > length) {
					move_left(j, length, number_to_move, place);
				} else {
					move(j, length, number_to_move, place);
				}
			}
		}
		int place_of_zero = 0;
		for (int j = 0; j < length; j++) {
			if (coordinates[j] == 0) {
				place_of_zero = j;
			}
		}
		int place_onethousand = (place_of_zero + 1000) % length;
		int place_twothousand = (place_of_zero + 2000) % length;
		int place_threethousand = (place_of_zero + 3000) % length;
		return coordinates[place_onethousand] + coordinates[place_twothousand] + coordinates[place_threethousand];
	}

	private void move_left(int j, int length, int number_to_move, int place) {
		for (int k = 0; k < length - number_to_move - 1; k++) {
			int other_place = calc_new_place(place - k, length);
			int temp = coordinates[other_place];
			int new_place = calc_new_place(place - k - 1, length);
			coordinates[other_place] = coordinates[new_place];
			coordinates[new_place] = temp;
			where_is_it(new_place, other_place);
		}
	}

	private void move(int j, int length, int number_to_move, int place) {
		for (int k = 0; k < number_to_move; k++) {
			int other_place = calc_new_place(place + k, length);
			int temp = coordinates[other_place];
			int new_place = calc_new_place(place + k + 1, length);
			coordinates[other_place] = coordinates[new_place];
			coordinates[new_place] = temp;
			where_is_it(new_place, other_place);
		}

	}

	private void where_is_it(int new_place, int other_place) {
		int where_is_new = 0;
		int where_is_other = 0;
		boolean new_is_new = false;
		boolean other_is_new = false;
		for (int l = 0; (l < where_is_it.size()); l++) {
			if (where_is_it.get(l) == new_place) {
				where_is_new = l;
				new_is_new = true;
			}
			if (where_is_it.get(l) == other_place) {
				where_is_other = l;
				other_is_new = true;
			}
			if (new_is_new && other_is_new)
				break;
		}
		where_is_it.put(where_is_new, other_place);
		where_is_it.put(where_is_other, new_place);
	}

	private int calc_new_place(int place, int length) {
		if (place >= length) {
			return place % length;
		}
		while (place < 0) {
			place += length;
		}
		return place;
	}

	public int solvePart2(List<String> dataList) {
		return 0;
	}

}