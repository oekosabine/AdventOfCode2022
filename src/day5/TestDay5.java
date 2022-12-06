package day5;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class TestDay5 {

	@Test
	void test_part1() {
		Day5 testInstance = new Day5();
		List<String> testDaten = new ArrayList<String>();
		testDaten.add("    [D]    ");
		testDaten.add("[N] [C]    ");
		testDaten.add("[Z] [M] [P]");
		testDaten.add(" 1   2   3 ");
		testDaten.add("");
		testDaten.add("move 1 from 2 to 1");
		testDaten.add("move 3 from 1 to 3");
		testDaten.add("move 2 from 2 to 1");
		testDaten.add("move 1 from 1 to 2");
		assertTrue((testInstance.solvePart1(testDaten)).equals("CMZ"));
	}

	@Test
	void test_part2() {
		Day5 testInstance = new Day5();
		List<String> testDaten = new ArrayList<String>();
		testDaten.add("    [D]    ");
		testDaten.add("[N] [C]    ");
		testDaten.add("[Z] [M] [P]");
		testDaten.add(" 1   2   3 ");
		testDaten.add("");
		testDaten.add("move 1 from 2 to 1");
		testDaten.add("move 3 from 1 to 3");
		testDaten.add("move 2 from 2 to 1");
		testDaten.add("move 1 from 1 to 2");
		assertTrue(testInstance.solvePart2(testDaten).equals("MCD"));
	}

	@Test
	void test_refactor() throws IOException {
		Day5 testInstance = new Day5();
		List<String> testDaten = testInstance
				.readData("C:\\Users\\boehm\\eclipse-workspace\\AdventOfCode2022\\src\\day5\\input.txt");
		assertTrue(testInstance.solvePart1(testDaten).equals("JDTMRWCQJ"));
		assertTrue(testInstance.solvePart2(testDaten).equals("VHJDDCWRD"));
	}

}