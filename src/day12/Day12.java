package day12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;

public class Day12 {

	public static void main(String[] args) {
		Day12 instanceOfTheDay = new Day12();
		try {
			instanceOfTheDay.solve("C:\\Users\\boehm\\eclipse-workspace\\AdventOfCode2022\\src\\day12\\input.txt");
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

	private List<String> neighbors(String fromNode, int rows, int columns, Character[][] arrayOfHeights) {
		List<String> neighbors = new ArrayList<>();
		String[] fromNodeChanged = fromNode.replace("(", "").replace(")", "").split(",");
		int x = Integer.parseInt(fromNodeChanged[0]);
		int y = Integer.parseInt(fromNodeChanged[1]);
		if (x + 1 < rows)
			if (neighborIsReachable(arrayOfHeights[x][y], arrayOfHeights[x + 1][y]))
				neighbors.add("(" + (x + 1) + "," + y + ")");
		if (x > 0)
			if (neighborIsReachable(arrayOfHeights[x][y], arrayOfHeights[x - 1][y]))
				neighbors.add("(" + (x - 1) + "," + y + ")");
		if (y + 1 < columns)
			if (neighborIsReachable(arrayOfHeights[x][y], arrayOfHeights[x][y + 1]))
				neighbors.add("(" + x + "," + (y + 1) + ")");
		if (y > 0)
			if (neighborIsReachable(arrayOfHeights[x][y], arrayOfHeights[x][y - 1]))
				neighbors.add("(" + x + "," + (y - 1) + ")");
		return neighbors;
	}

	public int solvePart1(List<String> dataList) {
		int width = dataList.get(0).length(); // Anzahl Spalten
		int rows = dataList.size(); // Anzahl Zeilen
		String zielknoten = "";
		Character[][] arrayOfHeights = new Character[rows][width];
		HashMap<String, Integer> distances = new HashMap<>();
		HashMap<String, String> predecessors = new HashMap<>();
		HashSet<String> Q = new HashSet<>();
		for (int j = 0; j < rows; j++) {
			String line = dataList.get(j);
			for (int i = 0; i < width; i++) {
				Character charAtPos = line.charAt(i);
				arrayOfHeights[j][i] = charAtPos;
				String member = "(" + j + "," + i + ")";
				distances.put(member, Integer.MAX_VALUE);
				predecessors.put(member, null);
				Q.add(member);
				if (charAtPos == 'S') {
					distances.put(member, 0);
				}
				if (charAtPos == 'E') {
					zielknoten = member;
				}
			}
		}
		while (!Q.isEmpty()) {
			int smallest = Integer.MAX_VALUE;
			String keySmallest = null;
			for (String entry : Q) {
				if (smallest > distances.get(entry)) {
					smallest = distances.get(entry);
					keySmallest = entry;
				}
			}
			Q.remove(keySmallest);
			if (keySmallest.equals(zielknoten)) {
				break;
			}
			for (String item : neighbors(keySmallest, rows, width, arrayOfHeights)) {
				if (Q.contains(item)) {
					distance_update(keySmallest, item, distances, predecessors);
				}
			}
		}
		return distances.get(zielknoten);
	}

	private void distance_update(String keySmallest, String item, HashMap<String, Integer> distances,
			HashMap<String, String> predecessors) {
		int alternativ = distances.get(keySmallest) + abstand_zwischen(keySmallest, item);
		if (alternativ < distances.get(item)) {
			distances.put(item, alternativ);
			predecessors.put(item, keySmallest);
		}
	}

	private Integer abstand_zwischen(String keySmallest, String item) {
		return 1;
	}

	boolean neighborIsReachable(Character neighborFrom, Character neighborTo) {
		HashMap<Character, Integer> zuordnung = new HashMap<>();
		int i = 0;
		for (Character c = 'a'; c <= 'z'; c++) {
			zuordnung.put(c, i);
			i++;
		}
		zuordnung.put('S', 0);
		zuordnung.put('E', 25);
		if (zuordnung.get(neighborTo) - zuordnung.get(neighborFrom) < 2) {
			return true;
		}
		return false;
	}

	Character[][] arrayOfHeights;
	int width;
	int rows;
	String zielknoten = "";

	public int solvePart2(List<String> dataList) {
		HashSet<String> Startknoten = new HashSet<>();
		width = dataList.get(0).length(); // Anzahl Spalten
		rows = dataList.size(); // Anzahl Zeilen
		arrayOfHeights = new Character[rows][width];
		int smallestPath = Integer.MAX_VALUE;
		int pathLength = Integer.MAX_VALUE;
		for (int j = 0; j < rows; j++) {
			String line = dataList.get(j);
			for (int i = 0; i < width; i++) {
				Character charAtPos = line.charAt(i);
				arrayOfHeights[j][i] = charAtPos;
				String member = "(" + j + "," + i + ")";
				if (charAtPos == 'a' || charAtPos == 'S') {
					Startknoten.add(member);
				}
				if (charAtPos == 'E') {
					zielknoten = member;
				}
			}
		}
		for (String start : Startknoten) {
			pathLength = dijkstra(dataList, start);
			if (pathLength < smallestPath) {
				smallestPath = pathLength;
			}
		}
		return smallestPath;
	}

	int dijkstra(List<String> dataList, String start) {
		HashMap<String, Integer> distances = new HashMap<>();
		HashMap<String, String> predecessors = new HashMap<>();
		HashSet<String> Q = new HashSet<>();
		for (int j = 0; j < rows; j++) {
			String line = dataList.get(j);
			for (int i = 0; i < width; i++) {
				Character charAtPos = line.charAt(i);
				arrayOfHeights[j][i] = charAtPos;
				String member = "(" + j + "," + i + ")";
				distances.put(member, Integer.MAX_VALUE);
				predecessors.put(member, null);
				Q.add(member);
			}
		}
		distances.put(start, 0);

		while (!Q.isEmpty()) {
			int smallest = Integer.MAX_VALUE;
			String keySmallest = null;
			for (String entry : Q) {
				if (smallest > distances.get(entry)) {
					smallest = distances.get(entry);
					keySmallest = entry;
				}
			}
			Q.remove(keySmallest);
			if (keySmallest == null)
				break;
			if (keySmallest.equals(zielknoten)) {
				break;
			}
			for (String item : neighbors(keySmallest, rows, width, arrayOfHeights)) {
				if (Q.contains(item)) {
					distance_update(keySmallest, item, distances, predecessors);
				}
			}
		}
		return distances.get(zielknoten);
	}

}