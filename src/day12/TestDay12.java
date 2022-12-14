package day12;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class TestDay12 {

	@Test
	void test_part1() {
		Day12 testInstance = new Day12();
		List<String> testDaten = new ArrayList<String>();
		testDaten.add("Sabqponm");
		testDaten.add("abcryxxl");
		testDaten.add("accszExk");
		testDaten.add("acctuvwj");
		testDaten.add("abdefghi");
		assertTrue(testInstance.solvePart1_dijkstra(testDaten) == 31);
	}

	@Test
	void test_part2() {
		Day12 testInstance = new Day12();
		List<String> testDaten = new ArrayList<String>();
		testDaten.add("mjqjpqmgbljsphdztnvjfqwrcgsmlb");
		assertTrue(testInstance.solvePart2(testDaten) == 19);
		testDaten = new ArrayList<String>();
		testDaten.add("bvwbjplbgvbhsrlpgdmjqwftvncz");
		assertTrue(testInstance.solvePart2(testDaten) == 23);
		testDaten = new ArrayList<String>();
		testDaten.add("nppdvjthqldpwncqszvftbrmjlhg");
		assertTrue(testInstance.solvePart2(testDaten) == 23);
		testDaten = new ArrayList<String>();
		testDaten.add("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg");
		assertTrue(testInstance.solvePart2(testDaten) == 29);
		testDaten = new ArrayList<String>();
		testDaten.add("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw");
		assertTrue(testInstance.solvePart2(testDaten) == 26);
	}

	@Test
	void test_refactor() throws IOException {
		Day12 testInstance = new Day12();
		List<String> testDaten = testInstance
				.readData("C:\\Users\\boehm\\eclipse-workspace\\AdventOfCode2022\\src\\day12\\input.txt");
		assertTrue(testInstance.solvePart1(testDaten) == 1542);
		assertTrue(testInstance.solvePart2(testDaten) == 3153);
	}

}