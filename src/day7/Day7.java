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

	long solvePart1(List<String> dataList) {
		List<String> directories = new ArrayList<String>();
		String directory = "";
		for (int i = 0; i < dataList.size(); i++) {
			String line = dataList.get(i);
			String[] line_splittet = line.split(" ");
			if (line_splittet[0].equals("$")) { // line starts with "$"
				if (line_splittet[1].equals("cd")) {
					if (line_splittet[2].equals("..")) { // one directory up
						String[] directory_woslash = directory.split("/");
						directory = "/";
						for (int j = 0; j < directory_woslash.length - 1; j++) {
							directory += directory_woslash[j] + "/";
						}
					} else { // one directory down
						directory += line_splittet[2]; // add directory to current pathname
						if (!line_splittet[2].equals("/"))
							directory += "/"; // add a slash to divide the directories
						directories.add(directory); // add directory to the list of directories
					}
				}
			} else if (!line_splittet[0].equals("dir")) {
				// line does not start with "$"
				// and line does not start with "dir"
				// means: it is a filesize + filename in this line
				String filename = directory + line_splittet[1]; // filename with whole path
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
			if (file.contains(dir)) // all files, where the given directory is part of the filename
				sum += file_sizes.get(file);
		}
		return sum;
	}

	long solvePart2(List<String> dataList) {
		solvePart1(dataList);
		long total_space = 70000000;
		long space_needed = 30000000;
		long all_directories_sum = directory_sizes.get("/");
		long free_space = 0;
		long store_size = all_directories_sum;
		for (String dir : directory_sizes.keySet()) {
			long size_of_current_directory = directory_sizes.get(dir);
			free_space = total_space - all_directories_sum + size_of_current_directory;
			if (free_space > space_needed && size_of_current_directory < store_size) {
				// another directory is smaller, but still enough space gets free
				store_size = directory_sizes.get(dir);
			}
		}
		return store_size;
	}
}