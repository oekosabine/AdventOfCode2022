package day20;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class TestDay20 {

	@Test
	void test_part1() {
		Day20 testInstance = new Day20();
		List<String> testDaten = new ArrayList<String>();
		testDaten.add("1");
		testDaten.add("2");
		testDaten.add("-3");
		testDaten.add("3");
		testDaten.add("-2");
		testDaten.add("0");
		testDaten.add("4");
		assertTrue(testInstance.solvePart1(testDaten) == 3);
	}

	@Test
	void test_part2() {
		Day20 testInstance = new Day20();
		List<String> testDaten = new ArrayList<String>();
		testDaten.add("1");
		testDaten.add("2");
		testDaten.add("-3");
		testDaten.add("3");
		testDaten.add("-2");
		testDaten.add("0");
		testDaten.add("4");
		assertTrue(testInstance.solvePart2(testDaten) == 29);
	}

	@Test
	void test_refactor() throws IOException {
		Day20 testInstance = new Day20();
		List<String> testDaten = testInstance
				.readData("C:\\Users\\boehm\\eclipse-workspace\\AdventOfCode2022\\src\\day20\\input.txt");
		assertTrue(testInstance.solvePart1(testDaten) == 528);
		assertTrue(testInstance.solvePart2(testDaten) == 522);
	}

}