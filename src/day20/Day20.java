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

import org.junit.jupiter.params.shadow.com.univocity.parsers.fixed.FixedWidthFieldLengths;

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
				move_smaller_zero(length, number_to_move, place);
			} else {
				if (number_to_move + place > length) {
					move_left(length, number_to_move, place);
				} else {
					move(length, number_to_move, place);
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

	private void move_smaller_zero(int length, int number_to_move, int place) {
		int new_place = (place + number_to_move);
		if (new_place <= 0) {
			new_place = (place + number_to_move) % (length - 1) + length - 1;
		}
		int original_position_of_current_number = place;
		for (int l = 0; l < length; l++) {
			if (where_is_it.get(l) == place) {
				original_position_of_current_number = l;
				break;
			}
		}
		for (int k = place; k < new_place; k++) {
			coordinates[k] = coordinates[k + 1];
			for (int l = 0; l < length; l++) {
				if (where_is_it.get(l) == k + 1) {
					where_is_it.put(l, k);
					break;
				}
			}
		}
		coordinates[new_place] = number_to_move;
		where_is_it.put(original_position_of_current_number, new_place);
	}

	private void move_left(int length, int number_to_move, int place) {
		int new_place = calc_new_place(place + number_to_move + 1, length);
		int temp_place = place;
		for (int l = 0; l < length; l++) {
			if (where_is_it.get(l) == place) {
				temp_place = l;
				break;
			}
		}
		for (int k = place; k > new_place; k--) {
			coordinates[k] = coordinates[k - 1];
			for (int l = 0; l < length; l++) {
				if (where_is_it.get(l) == k - 1) {
					where_is_it.put(l, k);
					break;
				}
			}
		}
		coordinates[new_place] = number_to_move;
		where_is_it.put(temp_place, new_place);
	}

	private void move(int length, int number_to_move, int place) {
		int new_place = calc_new_place(place + number_to_move, length);
		int temp_place = place;
		for (int l = 0; l < length ; l++) {
			if (where_is_it.get(l) == place) {
				temp_place = l;
				break;
			}
		}
		for (int k = place; k < new_place; k++) {
			coordinates[k] = coordinates[k + 1];
			for (int l = 0; l < length ; l++) {
				if (where_is_it.get(l) == k + 1) {
					where_is_it.put(l, k);
					break;
				}
			}
		}
		coordinates[new_place] = number_to_move;
		where_is_it.put(temp_place, new_place);
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