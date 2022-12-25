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
		long solution = solvePart1(dataList);
		System.out.println("Solution for part 1: " + solution);
		solution = solvePart2(dataList);
		System.out.println("Solution for part 2: " + solution);
	}

	private class Number {
		long value; // value of the number
		int current_position; // current position of the number
	}

	Number coordinates_as_struct[]; // index in the array equals original position.

	public int solvePart1(List<String> dataList) {
		int length = dataList.size();
		coordinates_as_struct = new Number[length];
		int i = 0;
		for (String line : dataList) {
			Integer number = Integer.parseInt(line);
			Number item = new Number();
			item.current_position = i;
			item.value = number;
			coordinates_as_struct[i] = item;
			i++;
		}

		for (Number current : coordinates_as_struct) {
			long value = (current.value);
			int old_place = current.current_position;
			int new_place = calc_new_place(old_place, value, length);
			move_elements(old_place, new_place);
			current.current_position = new_place;
		}

		int[] place_of_thousands = calc_place_of_thousands(length);
		for (int place : place_of_thousands) {
			while (place > length - 1) {
				place -= length;
			}
		}

		List<Long> value_thousands = new ArrayList<Long>();

		for (Number item : coordinates_as_struct) {
			for (int place : place_of_thousands) {
				if (item.current_position == place)
					value_thousands.add(item.value); // values corresponding to the indices 1000, 2000, 3000
			}
		}
		int sum = 0;
		for (long item : value_thousands) {
			sum += item;
		}
		return sum;
	}

	private int[] calc_place_of_thousands(int length) {
		int place_of_zero = 0;
		for (int j = 0; j < length; j++) {
			if (coordinates_as_struct[j].value == 0) {
				place_of_zero = coordinates_as_struct[j].current_position;
			}
		}
		int[] place = new int[3];
		place[0] = (place_of_zero + 1000) % length;
		place[1] = (place_of_zero + 2000) % length;
		place[2] = (place_of_zero + 3000) % length;
		return place;
	}

	private void move_elements(int old_place, int new_place) {
		if (old_place < new_place) { // new place is on the right of the old place -> shift the elements to the left
										// side
			for (Number item : coordinates_as_struct) {
				if (old_place < item.current_position && item.current_position <= new_place) {
					item.current_position--;
				}
			}
		} else if (new_place < old_place) { // shift elements to the right side
			for (Number item : coordinates_as_struct) {
				if (new_place <= item.current_position && item.current_position < old_place) {
					item.current_position++;
				}
			}
		}
	}

	private int calc_new_place(int old_place, long value, int length) {
		long new_place = old_place + value;
		if (new_place <= 0) {
			new_place = new_place % (length - 1) + length - 1;
		} else if (new_place > length - 1) {
			new_place = new_place % (length - 1);
		}
		return (int) new_place;
	}

	public long solvePart2(List<String> dataList) {
		int decryption_key = 811589153;
		int length = dataList.size();
		coordinates_as_struct = new Number[length];
		int i = 0;
		for (String line : dataList) {
			Long number = Long.parseLong(line);
			Number item = new Number();
			item.current_position = i;
			item.value = number * decryption_key;
			coordinates_as_struct[i] = item;
			i++;
		}

		for (int j = 0; j < 10; j++) {
			for (Number current : coordinates_as_struct) {
				long value = (current.value);
				int old_place = current.current_position;
				int new_place = calc_new_place(old_place, value, length);
				move_elements(old_place, new_place);
				current.current_position = new_place;
			}
		}

		int[] place_of_thousands = calc_place_of_thousands(length);
		for (int place : place_of_thousands) {
			while (place > length - 1) {
				place -= length;
			}
		}

		List<Long> value_thousands = new ArrayList<Long>();

		for (Number item : coordinates_as_struct) {
			for (int place : place_of_thousands) {
				if (item.current_position == place)
					value_thousands.add(item.value); // values corresponding to the indices 1000, 2000, 3000
			}
		}
		long sum = 0;
		for (long item : value_thousands) {
			sum += item;
		}
		return sum;
	}

}