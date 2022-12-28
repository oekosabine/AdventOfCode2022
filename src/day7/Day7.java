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
	HashMap<String, Long> directory_sizes = new HashMap<String, Long>();
	List<String> directories = new ArrayList<String>();

	long solvePart1(List<String> dataList) {
		String directory = "";
		for (int i = 0; i < dataList.size(); i++) {
			String line = dataList.get(i);
			String[] line_splittet = line.split(" ");
			if (line_splittet[0].equals("$")) {
				if (line_splittet[1].equals("cd")) {
					if (line_splittet[2].equals("..")) {
						String[] directory_woslash = directory.split("/");
						directory = "/";
						for (int j = 0; j < directory_woslash.length - 1; j++) {
							directory += directory_woslash[j] + "/";
						}
					} else {
						directory += line_splittet[2];
						if (!line_splittet[2].equals("/"))
							directory += "/";
						directories.add(directory);
					}
				}
			} else if (!line_splittet[0].equals("dir")) { // add file size
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

	long solvePart2(List<String> dataList) {
		long solution = solvePart1(dataList);
		long total_space = 70000000;
		long space_needed = 30000000;
		long all_directories_sum = directory_sizes.get("/");
		long unused_space = total_space - all_directories_sum;
		long temp = 0;
		String store_directory = "";
		long store_size = all_directories_sum;
		for (String dir : directory_sizes.keySet()) {
			temp = total_space - all_directories_sum + directory_sizes.get(dir);
			if (temp > space_needed && directory_sizes.get(dir) < store_size) {
				store_directory = dir;
				store_size = directory_sizes.get(dir);
			}
		}
		return store_size;
	}
}