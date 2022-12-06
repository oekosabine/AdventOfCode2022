package day3;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class TestDay3 {

	@Test
	void test_part1() {
		Day3 testInstance = new Day3();
		List<String> testDaten = new ArrayList<String>();
		testDaten.add("vJrwpWtwJgWrhcsFMMfFFhFp");
		testDaten.add("jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL");
		testDaten.add("PmmdzqPrVvPwwTWBwg");
		testDaten.add("wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn");
		testDaten.add("ttgJtRGJQctTZtZT");
		testDaten.add("CrZsJsPPZsGzwwsLwLmpwMDw");
		assertTrue(testInstance.solvePart1(testDaten) == 157);
	}

	@Test
	void test_part2() {
		Day3 testInstance = new Day3();
		List<String> testDaten = new ArrayList<String>();
		testDaten.add("vJrwpWtwJgWrhcsFMMfFFhFp");
		testDaten.add("jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL");
		testDaten.add("PmmdzqPrVvPwwTWBwg");
		testDaten.add("wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn");
		testDaten.add("ttgJtRGJQctTZtZT");
		testDaten.add("CrZsJsPPZsGzwwsLwLmpwMDw");
		assertTrue(testInstance.solvePart2(testDaten) == 70);
	}

	@Test
	void test_refactor() throws IOException {
		Day3 testInstance = new Day3();
		List<String> testDaten = testInstance
				.readData("C:\\Users\\boehm\\eclipse-workspace\\AdventOfCode2022\\src\\day3\\input.txt");
		assertTrue(testInstance.solvePart1(testDaten) == 7967);
		assertTrue(testInstance.solvePart2(testDaten) == 2716);
	}

}