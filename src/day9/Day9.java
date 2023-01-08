package day9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import day8.Day8.Tupel;

public class Day9 {

	public static void main(String[] args) {
		Day9 instanceOfTheDay = new Day9();
		try {
			instanceOfTheDay.solve("C:\\Users\\boehm\\eclipse-workspace\\AdventOfCode2022\\src\\day9\\input.txt");
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

	public class Tupel {
		int index_x;
		int index_y;

		public Tupel(int x, int y) {
			index_x = x;
			index_y = y;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + Objects.hash(index_x, index_y);
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Tupel other = (Tupel) obj;
			return (index_x == other.index_x) && (index_y == other.index_y);
		}

		public boolean nearby(Tupel other) {
			if (this.index_x == other.index_x - 1 && this.index_y == other.index_y - 1)
				return true;
			if (this.index_x == other.index_x - 1 && this.index_y == other.index_y)
				return true;
			if (this.index_x == other.index_x - 1 && this.index_y == other.index_y + 1)
				return true;
			if (this.index_x == other.index_x && this.index_y == other.index_y - 1)
				return true;
			if (this.index_x == other.index_x && this.index_y == other.index_y)
				return true;
			if (this.index_x == other.index_x && this.index_y == other.index_y + 1)
				return true;
			if (this.index_x == other.index_x + 1 && this.index_y == other.index_y - 1)
				return true;
			if (this.index_x == other.index_x + 1 && this.index_y == other.index_y)
				return true;
			if (this.index_x == other.index_x + 1 && this.index_y == other.index_y + 1)
				return true;
			return false;
		}

		public void moveto(Tupel other) {
			if (this.index_x == other.index_x) {
				if (this.index_y < other.index_y) {
					this.index_y = other.index_y - 1;
				}
				if (this.index_y > other.index_y) {
					this.index_y = other.index_y + 1;
				}
			} else if (this.index_y == other.index_y) {
				if (this.index_x < other.index_x) {
					this.index_x = other.index_x - 1;
				}
				if (this.index_x > other.index_x) {
					this.index_x = other.index_x + 1;
				}
			} else {
				if (this.index_x - other.index_x == 2) {
					this.index_x--;
					this.index_y = other.index_y;
				}
				if (this.index_x - other.index_x == -2) {
					this.index_x++;
					this.index_y = other.index_y;
				}
				if (this.index_y - other.index_y == 2) {
					this.index_y--;
					this.index_x = other.index_x;
				}
				if (this.index_y - other.index_y == -2) {
					this.index_y++;
					this.index_x = other.index_x;
				}

			}
		}
	}

	int solvePart1(List<String> dataList) {
		List<Tupel> visited_coordinates = new ArrayList<Tupel>();
		Tupel starting_point = new Tupel(0, 0);
		Tupel place_of_head = new Tupel(0, 0);
		Tupel place_of_tail = new Tupel(0, 0);
		visited_coordinates.add(starting_point); // Starting point is (0,0)
		for (String line : dataList) {
			String[] splitted_line = line.split(" ");
			String direction = splitted_line[0];
			int number_of_steps = Integer.parseInt(splitted_line[1]);
			if (direction.equals("R")) {
				for (int i = 0; i < number_of_steps; i++) {
					place_of_head.index_x++;
					if (!place_of_tail.nearby(place_of_head)) {
						place_of_tail.moveto(place_of_head);
						Tupel place_to_add = new Tupel(place_of_tail.index_x, place_of_tail.index_y);
						visited_coordinates.add(place_to_add);
					}
				}
			}
			if (direction.equals("L")) {
				for (int i = 0; i < number_of_steps; i++) {
					place_of_head.index_x--;
					if (!place_of_tail.nearby(place_of_head)) {
						place_of_tail.moveto(place_of_head);

						Tupel place_to_add = new Tupel(place_of_tail.index_x, place_of_tail.index_y);
						visited_coordinates.add(place_to_add);
					}
				}
			}
			if (direction.equals("U")) {
				for (int i = 0; i < number_of_steps; i++) {
					place_of_head.index_y++;
					if (!place_of_tail.nearby(place_of_head)) {
						place_of_tail.moveto(place_of_head);
						Tupel place_to_add = new Tupel(place_of_tail.index_x, place_of_tail.index_y);
						visited_coordinates.add(place_to_add);
					}
				}
			}
			if (direction.equals("D")) {
				for (int i = 0; i < number_of_steps; i++) {
					place_of_head.index_y--;
					if (!place_of_tail.nearby(place_of_head)) {
						place_of_tail.moveto(place_of_head);
						Tupel place_to_add = new Tupel(place_of_tail.index_x, place_of_tail.index_y);
						visited_coordinates.add(place_to_add);
					}
				}
			}
		}
		List<Tupel> listWithoutDuplicates = new ArrayList<>(new HashSet<>(visited_coordinates));
		int result = listWithoutDuplicates.size();
		return result;
	}

	int solvePart2(List<String> dataList) {
		return 0;
	}
}