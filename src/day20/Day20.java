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

	private class Number {
		int value;
		int original_position;
		int current_position;
	}

	Number coordinates_as_struct[];

	public int solvePart1(List<String> dataList) {
		int length = dataList.size();
		coordinates_as_struct = new Number[length];
		int i = 0;
		for (String line : dataList) {
			Integer number = Integer.parseInt(line);
			Number item = new Number();
			item.current_position = i;
			item.original_position = i;
			item.value = number;
			coordinates_as_struct[i] = item;
			i++;
		}

		for (Number current : coordinates_as_struct) {
			int number_to_move = (current.value);
			int place = current.current_position;
			int new_place = place + number_to_move;
			while (true) {
				if (new_place <= 0) {
					new_place = new_place + (length - 1);
				} else if (new_place > length - 1) {
					new_place = new_place - (length - 1);
				} else {
					break;
				}
			}
			if (place < new_place) { // new place is right from the old place -> shift the elements to the left side
				for (Number item : coordinates_as_struct) {
					if (place < item.current_position && item.current_position <= new_place) {
						item.current_position--;
					}
				}
			} else if (new_place < place) {
				for (Number item : coordinates_as_struct) {
					if (new_place <= item.current_position && item.current_position < place) {
						item.current_position++;
					}
				}
			}
			current.current_position = new_place;
		}

		int place_of_zero = 0;
		for (int j = 0; j < length; j++) {
			if (coordinates_as_struct[j].value == 0) {
				place_of_zero = coordinates_as_struct[j].current_position;
			}
		}
		int place_onethousand = (place_of_zero + (1000 % length));
		int place_twothousand = (place_of_zero + (2000 % length));
		int place_threethousand = (place_of_zero + (3000 % length));
		while (true) {
			if (place_onethousand > length - 1)
				place_onethousand -= length;
			else
				break;
		}
		while (true) {
			if (place_twothousand > length - 1)
				place_twothousand -= length;
			else
				break;
		}
		while (true) {
			if (place_threethousand > length - 1)
				place_threethousand -= length;
			else
				break;
		}
		int value_onethousend = 0;
		int value_twothousend = 0;
		int value_threethousend = 0;

		for (Number item : coordinates_as_struct) {
			if (item.current_position == place_onethousand)
				value_onethousend = item.value;
			if (item.current_position == place_twothousand)
				value_twothousend = item.value;
			if (item.current_position == place_threethousand)
				value_threethousend = item.value;
		}
		return value_onethousend + value_twothousend + value_threethousend;
	}

	public int solvePart2(List<String> dataList) {
		return 0;
	}

}