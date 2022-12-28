package day7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;

import junit.framework.AssertionFailedError;

public class Day7 {

	public static void main(String[] args) {
		Day7 instanceOfTheDay = new Day7();
		try {
			instanceOfTheDay.solve("C:\\Users\\boehm\\eclipse-workspace\\AdventOfCode2022\\src\\day7\\input.txt");
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

	HashMap<String, Long> file_sizes = new HashMap<String, Long>();

	long solvePart1(List<String> dataList) {
		HashMap<String, Long> directory_sizes = new HashMap<String, Long>();
		List<String> directories = new ArrayList<String>();
		String directory = "";
		for (int i = 0; i < dataList.size(); i++) {
			String line = dataList.get(i);
			String[] line_splittet = line.split(" ");
			if (line_splittet[0].equals("$")) {
				if (line_splittet[1].equals("cd")) {
					if (!line_splittet[2].equals("..")) {
						if (!line_splittet[2].equals("/"))
							directory += line_splittet[2] + "/";
						else
							directory += line_splittet[2];
						directories.add(directory);
					} else {
						String[] directory_woslash = directory.split("/");
						directory = "/";
						for (int j = 0; j < directory_woslash.length - 1; j++) {
							directory += directory_woslash[j] + "/";
						}
					}
				}
			} else if (!line_splittet[0].equals("dir")) { // add filesize
				String filename = directory + line_splittet[1];
				long filesize = Integer.parseInt(line_splittet[0]);
				file_sizes.put(filename, filesize);
			}
		}

		long sum = 0;
		for (String dir : directories) {
			long current_size = calc_directory_size(dir);
			directory_sizes.put(dir, current_size);
			if (current_size <= 100000)
				sum += current_size;
		}
		return sum;
	}

	private long calc_directory_size(String dir) {
		long sum = 0;
		for (String file : file_sizes.keySet()) {
			if (file.contains(dir))
				sum += file_sizes.get(file);
		}
		return sum;
	}

	int solvePart2(List<String> dataList) {
		return 0;
	}
}