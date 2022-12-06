package day4;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class TestDay4 {

	@Test
	void test_part1() {
		Day4 testInstance = new Day4();
		List<String> testDaten = new ArrayList<String>();
		testDaten.add("2-4,6-8");
		testDaten.add("2-3,4-5");
		testDaten.add("5-7,7-9");
		testDaten.add("2-8,3-7");
		testDaten.add("6-6,4-6");
		testDaten.add("2-6,4-8");
		assertTrue(testInstance.solvePart1(testDaten) == 2);
	}

	@Test
	void test_part2() {
		Day4 testInstance = new Day4();
		List<String> testDaten = new ArrayList<String>();
		testDaten.add("2-4,6-8");
		testDaten.add("2-3,4-5");
		testDaten.add("5-7,7-9");
		testDaten.add("2-8,3-7");
		testDaten.add("6-6,4-6");
		testDaten.add("2-6,4-8");
		assertTrue(testInstance.solvePart2(testDaten) == 4);
	}

	@Test
	void test_refactor() throws IOException {
		Day4 testInstance = new Day4();
		List<String> testDaten = testInstance
				.readData("C:\\Users\\boehm\\eclipse-workspace\\AdventOfCode2022\\src\\day4\\input.txt");
		assertTrue(testInstance.solvePart1(testDaten) == 602);
		assertTrue(testInstance.solvePart2(testDaten) == 891);
	}

}