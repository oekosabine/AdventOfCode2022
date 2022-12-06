package day5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day5 {

	public static void main(String[] args) {
		Day5 instanceOfTheDay = new Day5();
		try {
			instanceOfTheDay.solve("C:\\Users\\boehm\\eclipse-workspace\\AdventOfCode2022\\src\\day5\\input.txt");
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

	String solvePart1(List<String> dataList) {
		List<String> crates = new ArrayList<String>(); // first lines: crates + empty line
		for (String line : dataList) { // it is a crate
			crates.add(line);
			if (line.isEmpty()) {
				break;
			}
		}
		crates.remove(crates.size() - 1); // remove the empty line at the end
		String stacksLine = crates.get(crates.size() - 1); // last item -> numbers of the stacks
		stacksLine = stacksLine.trim();
		String[] stacksSeperated = stacksLine.split("   ");
		ArrayList<String[]> allStacks = new ArrayList<String[]>();
		int stackHeight = crates.size();
		for (String stackNumber : stacksSeperated) {
			String[] stack = new String[100]; // FIXME: change to something where the length can be adapted dynamically
			for (int i = 0; i < 100; i++) {
				stack[i] = " ";
			}
			stack[0] = stackNumber; // one List for every stack
			allStacks.add(stack);
		}
		crates.remove(crates.size() - 1); // remove the line with the stackNumbers
		int k = 1;
		for (int i = crates.size() - 1; i >= 0; i--) {
			String line = crates.get(i);
			for (int j = 0; j < line.length(); j = j + 4) {
				(allStacks.get(j / 4))[k] = "" + line.charAt(j + 1);
			}
			k++;
		}
		List<String> moves = new ArrayList<String>(); // lines below empty line: moves
		for (String line : dataList) {
			if (line.contains("move")) {
				moves.add(line);
			}
		}
		for (String line : moves) {
			String[] movesSeperated = line.split("move ");
			String[] movesSeperated2 = movesSeperated[1].split(" from ");
			String[] movesSeperated3 = movesSeperated2[1].split(" to ");
			int numberMoves = Integer.parseInt(movesSeperated2[0]);
			int fromHere = Integer.parseInt(movesSeperated3[0]);
			int toThere = Integer.parseInt(movesSeperated3[1]);
			String[] fromStack = allStacks.get(fromHere - 1);
			String store = "";
			for (int j = 1; j <= numberMoves; j++) {
				for (int i = fromStack.length - 1; i >= 0; i--) {
					if (fromStack[i].isBlank() == false) {
						store = fromStack[i];
						fromStack[i] = " ";
						i = 0;
					}
				}
				String[] toStack = allStacks.get(toThere - 1);
				for (int i = toStack.length - 1; i >= 0; i--) {
					if (toStack[i].isBlank() == false) {
						toStack[i + 1] = store;
						i = 0;
					}
				}
			}
		}
		String result = "";
		for (String[] container : allStacks) {
			for (int i = container.length - 1; i > 0; i--) {
				if (container[i].isBlank() == false) {
					result += container[i];
					i = 0;
				}
			}
		}
		return result;
	}

	String solvePart2(List<String> dataList) {
		List<String> crates = new ArrayList<String>(); // first lines: crates + empty line
		for (String line : dataList) { // it is a crate
			crates.add(line);
			if (line.isEmpty()) {
				break;
			}
		}
		crates.remove(crates.size() - 1); // remove the empty line at the end
		String stacksLine = crates.get(crates.size() - 1); // last item -> numbers of the stacks
		stacksLine = stacksLine.trim();
		String[] stacksSeperated = stacksLine.split("   ");
		ArrayList<String[]> allStacks = new ArrayList<String[]>();
		int stackHeight = crates.size();
		for (String stackNumber : stacksSeperated) {
			String[] stack = new String[100]; // FIXME: change to something where the length can be adapted dynamically
			for (int i = 0; i < 100; i++) {
				stack[i] = " ";
			}
			stack[0] = stackNumber; // one List for every stack
			allStacks.add(stack);
		}
		crates.remove(crates.size() - 1); // remove the line with the stackNumbers
		int k = 1;
		for (int i = crates.size() - 1; i >= 0; i--) {
			String line = crates.get(i);
			for (int j = 0; j < line.length(); j = j + 4) {
				(allStacks.get(j / 4))[k] = "" + line.charAt(j + 1);
			}
			k++;
		}
		List<String> moves = new ArrayList<String>(); // lines below empty line: moves
		for (String line : dataList) {
			if (line.contains("move")) {
				moves.add(line);
			}
		}
		for (String line : moves) {
			String[] movesSeperated = line.split("move ");
			String[] movesSeperated2 = movesSeperated[1].split(" from ");
			String[] movesSeperated3 = movesSeperated2[1].split(" to ");
			int numberMoves = Integer.parseInt(movesSeperated2[0]);
			int fromHere = Integer.parseInt(movesSeperated3[0]);
			int toThere = Integer.parseInt(movesSeperated3[1]);
			String[] fromStack = allStacks.get(fromHere - 1);
			String[] store = new String[numberMoves];
			for (int j = 0; j < numberMoves; j++) {
				for (int i = fromStack.length - 1; i >= 0; i--) {
					if (fromStack[i].isBlank() == false) {
						store[j] = fromStack[i];
						fromStack[i] = " ";
						i = 0;
					}
				}
			}
			for (int j = numberMoves - 1; j >= 0; j--) {
				String[] toStack = allStacks.get(toThere - 1);
				for (int i = toStack.length - 1; i >= 0; i--) {
					if (toStack[i].isBlank() == false) {
						toStack[i + 1] = store[j];
						i = 0;
					}
				}
			}
		}
		String result = "";
		for (String[] container : allStacks) {
			for (int i = container.length - 1; i > 0; i--) {
				if (container[i].isBlank() == false) {
					result += container[i];
					i = 0;
				}
			}
		}
		return result;
	}
}