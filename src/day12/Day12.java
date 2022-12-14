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
		// int solution = solvePart1(dataList);
		// System.out.println("Solution for part 1: " + solution);
		int solution = solvePart1_dijkstra(dataList);
		System.out.println("Solution for part 1 with Dijkstra: " + solution);
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

	public int solvePart1_dijkstra(List<String> dataList) {
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
			if (keySmallest == zielknoten) {
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
		if (neighborFrom.equals('S')) {
			if (neighborTo.equals('a') || neighborTo.equals('b')) {
				return true;
			}
		}
		if (neighborTo.equals('E')) {
			if (neighborFrom.equals('y') || neighborFrom.equals('z')) {
				return true;
			}
		}
		if ((int) neighborTo - neighborFrom < 2 && neighborTo != 'E' && neighborTo != 'S' && neighborFrom != 'E'
				&& neighborFrom != 'S') {
			return true;
		}
		return false;
	}

	static int maxCosts = Integer.MAX_VALUE;

	int solvePart1(List<String> dataList) {
		int width = dataList.get(0).length(); // Anzahl Spalten
		int rows = dataList.size(); // Anzahl Zeilen
		Character[][] arrayOfHeights = new Character[rows][width];
		for (int j = 0; j < rows; j++) {
			String line = dataList.get(j);
			for (int i = 0; i < width; i++) {
				Character charAtPos = line.charAt(i);
				arrayOfHeights[j][i] = charAtPos;
			}
		}
		boolean moving = true;
		while (moving) {
			for (int j = 0; j < rows; j++) {
				for (int i = 0; i < width; i++) {
					if (arrayOfHeights[j][i] == 'S') {
						visited.add("(0,0)");
						nextNeighbor(j, i, arrayOfHeights, 0);
						moving = false;
					}
				}
			}
		}
		return maxCosts - 1;
	}

	static int count = 0;
	List<String> visited = new ArrayList<>();

	private int nextNeighbor(int j, int i, Character[][] arrayOfHeights, int sum) {
		count++;
		String member = "(" + j + "," + i + ")";
		if (count % 1000000 == 0) {
			System.out.println(count + member + arrayOfHeights[j][i]);
		}
		int width = arrayOfHeights[0].length;
		int rows = arrayOfHeights.length;
		Character fromChar, neighbor;
		int cost = sum;
		fromChar = arrayOfHeights[j][i];
		if (fromChar == 'E') {
			if (maxCosts > cost) {
				maxCosts = cost;
			}
			visited.remove(member);
			return cost;
		}
		if (cost < maxCosts) {
			if (j + 1 < rows) { // nach unten
				int index1 = j + 1;
				int index2 = i;
				neighbor = arrayOfHeights[j + 1][i];
				member = "(" + index1 + "," + index2 + ")";
				if (!visited.contains(member)) {
					if (neighborIsReachable(fromChar, neighbor)) {
						visited.add(member);
						cost++;
						nextNeighbor(j + 1, i, arrayOfHeights, cost);
						visited.remove(member);
					}
				}
			}
			// nach rechts
			if (i + 1 < width) {
				neighbor = arrayOfHeights[j][i + 1];
				int index2 = i + 1;
				member = "(" + j + "," + index2 + ")";
				if (!visited.contains(member)) {
					if (neighborIsReachable(fromChar, neighbor)) {
						visited.add(member);
						cost++;
						nextNeighbor(j, i + 1, arrayOfHeights, cost);
						visited.remove(member);
					}
				}
			}
			if (j > 0) { // nach oben
				neighbor = arrayOfHeights[j - 1][i];
				int index1 = j - 1;
				member = "(" + index1 + "," + i + ")";
				if (!visited.contains(member)) {

					if (neighborIsReachable(fromChar, neighbor)) {
						visited.add(member);
						cost++;
						nextNeighbor(j - 1, i, arrayOfHeights, cost);
						visited.remove(member);
					}
				}
			}
			// nach links
			if (i > 0) {
				neighbor = arrayOfHeights[j][i - 1];
				int index1 = j;
				int index2 = i - 1;
				member = "(" + index1 + "," + index2 + ")";
				if (!visited.contains(member)) {

					if (neighborIsReachable(fromChar, neighbor)) {
						visited.add(member);
						cost++;
						nextNeighbor(j, i - 1, arrayOfHeights, cost);
						visited.remove(member);
					}
				}
			}
		}
		return cost;
	}

	int solvePart2(List<String> dataList) {
		return 0;
	}
}