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

	HashMap<String, ArrayList<String>> directory_content = new HashMap<String, ArrayList<String>>();
	HashMap<String, Long> file_sizes = new HashMap<String, Long>();
	HashMap<String, Long> directory_size = new HashMap<String, Long>();

	long solvePart1(List<String> dataList) {
		String directory = "/";
		for (int i = 0; i < dataList.size(); i++) {
			String line = dataList.get(i);
			if (line.charAt(0) == '$') { // command from me
				if (line.equals("$ ls")) { // get the content of the current directory
					// Name of the directory is one line above
					String previous_line = dataList.get(i - 1);
					if (previous_line.contains("$ cd ")) { // directory content
						directory = (previous_line.split(" "))[2];
					}
					ArrayList<String> list = new ArrayList<String>();
					for (int j = i + 1; j < dataList.size(); j++) { // below ls the content of the directory is given
						String current_line = dataList.get(j);
						if (current_line.contains("$"))
							break;
						if (current_line.contains("dir ")) // add directory name
							list.add(current_line.split(" ")[1]);
						else { // add filename
							String filename = current_line.split(" ")[1];
							list.add(filename);
							long filesize = Integer.parseInt(current_line.split(" ")[0]);
							file_sizes.put(filename, filesize);
						}
					}
					directory_content.put(directory, list);
				}
			}
		}

		for (String dir : directory_content.keySet()) {
			directory_size.put(dir, 0L);
		}
		for (String dir : directory_content.keySet()) {
			long size = calc_directory_size(dir);
			directory_size.put(dir, size);
		}
		long sum = 0;
		for (String dir : directory_size.keySet()) {
			long current_size = directory_size.get(dir);
			if (current_size < 100000)
				sum += current_size;
		}
		return sum;
	}

	private long calc_directory_size(String dir) {
		long sum = 0;
		if (directory_content.containsKey(dir)) { // it is a directory
			long dir_size = directory_size.get(dir);
			if (dir_size != 0)
				return dir_size;
			else {
				for (String item_inside : directory_content.get(dir)) {
					sum += calc_directory_size(item_inside);
				}
			}
		} else {
			return file_sizes.get(dir);
		}
		return sum;
	}

	int solvePart2(List<String> dataList) {
		return 0;
	}
}