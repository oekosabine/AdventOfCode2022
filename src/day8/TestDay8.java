package day8;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class TestDay8 {

	@Test
	void test_part1() {
		Day8 testInstance = new Day8();
		List<String> testDaten = new ArrayList<String>();
		testDaten.add("30373");
		testDaten.add("25512");
		testDaten.add("65332");
		testDaten.add("33549");
		testDaten.add("35390");
		assertTrue(testInstance.solvePart1(testDaten) == 21);
	}

	@Test
	void test_part2() {
		Day8 testInstance = new Day8();
		List<String> testDaten = new ArrayList<String>();
		testDaten.add("30373");
		testDaten.add("25512");
		testDaten.add("65332");
		testDaten.add("33549");
		testDaten.add("35390");
		assertTrue(testInstance.solvePart2(testDaten) == 8);
	}

	@Test
	void test_refactor() throws IOException {
		Day8 testInstance = new Day8();
		List<String> testDaten = testInstance
				.readData("C:\\Users\\boehm\\eclipse-workspace\\AdventOfCode2022\\src\\day8\\input.txt");
		assertTrue(testInstance.solvePart1(testDaten) == 1823);
		assertTrue(testInstance.solvePart2(testDaten) == 211680);
	}

}