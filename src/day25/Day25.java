package day25;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import org.junit.jupiter.params.shadow.com.univocity.parsers.fixed.FixedWidthFieldLengths;

public class Day25 {

	public static void main(String[] args) {
		Day25 instanceOfTheDay = new Day25();
		try {
			instanceOfTheDay.solve("C:\\Users\\boehm\\eclipse-workspace\\AdventOfCode2022\\src\\day25\\input.txt");
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
		String solution = solvePart1(dataList);
		System.out.println("Solution for part 1: " + solution);
		solution = solvePart2(dataList);
		System.out.println("Solution for part 2: " + solution);
	}

	public String solvePart1(List<String> dataList) {
		List<String> numbers_base_five = new ArrayList<String>();
		for (String line : dataList) { // data as per datalist
			numbers_base_five.add(line);
		}
		// Convert to base ten
		List<Long> numbers_base_ten = new ArrayList<Long>();
		for (String item : numbers_base_five) {
			long number_base_ten = 0;
			int length = item.length();
			for (int i = 0; i < length; i++) {
				Character character = item.charAt(i);
				int factor = 0;
				if (character == '=')
					factor = -2;
				else if (character == '-')
					factor = -1;
				else if (character == '0')
					factor = 0;
				else if (character == '1')
					factor = 1;
				else if (character == '2')
					factor = 2;
				double factor2 = Math.pow(5, length - i - 1);
				number_base_ten += factor * factor2;
			}
			numbers_base_ten.add(number_base_ten);
		}
		long sum = 0;
		for (long item : numbers_base_ten) {
			sum += item;
		}
		// Convert the sum back to base 5
		List<Long> sum_as_base_five_as_number = new ArrayList<Long>();
		long division = sum;
		int i = 0;
		while (true) {
			long modulo = division % 5;
			division = division / 5;
			sum_as_base_five_as_number.add(i, modulo);
			i++;
			if (division == 0)
				break;
		}
		// only the numbers -2,-1,0,1,2 are allowed -> convert 3 and 4 (and above)
		// accordingly
		int length = sum_as_base_five_as_number.size();
		for (int j = 0; j < length; j++) {
			long item = sum_as_base_five_as_number.get(j);
			if (item >= 3L) {
				// add: 1 times (power + 1)
				long new_value_as_number = 0;
				if (j == length - 1) {
					new_value_as_number = 1;
					sum_as_base_five_as_number.add(j + 1, new_value_as_number);

				} else {
					new_value_as_number = sum_as_base_five_as_number.get(j + 1) + 1;
					sum_as_base_five_as_number.set(j + 1, new_value_as_number);
				}
				// change the X to X-5
				sum_as_base_five_as_number.set(j, item - 5);
			}

		}
		Collections.reverse(sum_as_base_five_as_number);
		// set -2 to = and -1 to -
		length = sum_as_base_five_as_number.size();
		List<Character> sum_as_base_five_converted = new ArrayList<Character>();
		for (int j = 0; j < length; j++) {
			long item = sum_as_base_five_as_number.get(j);
			if (item == -2)
				sum_as_base_five_converted.add(j, '=');
			else if (item == -1)
				sum_as_base_five_converted.add(j, '-');
			else if (item == 0)
				sum_as_base_five_converted.add(j, '0');
			else if (item == 1)
				sum_as_base_five_converted.add(j, '1');
			else if (item == 2)
				sum_as_base_five_converted.add(j, '2');
		}
		String result = "";
		for (Character item : sum_as_base_five_converted) {
			result += item;
		}
		return result;
	}

	public String solvePart2(List<String> dataList) {
		return "";
	}

}