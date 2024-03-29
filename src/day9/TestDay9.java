package day9;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class TestDay9 {

	@Test
	void test_part1() {
		Day9 testInstance = new Day9();
		List<String> testDaten = new ArrayList<String>();
		testDaten.add("R 4 ");
		testDaten.add("U 4 ");
		testDaten.add("L 3 ");
		testDaten.add("D 1 ");
		testDaten.add("R 4 ");
		testDaten.add("D 1 ");
		testDaten.add("L 5 ");
		testDaten.add("R 2 ");
		assertTrue(testInstance.solvePart1(testDaten) == 13);
	}

	@Test
	void test_part2() {
		Day9 testInstance = new Day9();
		List<String> testDaten = new ArrayList<String>();
		testDaten.add("R 5 ");
		testDaten.add("U 8 ");
		testDaten.add("L 8 ");
		testDaten.add("D 3 ");
		testDaten.add("R 17 ");
		testDaten.add("D 10 ");
		testDaten.add("L 25 ");
		testDaten.add("U 20 ");
		assertTrue(testInstance.solvePart2(testDaten) == 36);
	}

	@Test
	void test_refactor() throws IOException {
		Day9 testInstance = new Day9();
		List<String> testDaten = testInstance
				.readData("C:\\Users\\selig\\Documents\\GitHub\\AdventOfCode2022\\src\\day9\\input.txt");
		assertTrue(testInstance.solvePart1(testDaten) == 6190);
		assertTrue(testInstance.solvePart2(testDaten) == 2516);
	}

}