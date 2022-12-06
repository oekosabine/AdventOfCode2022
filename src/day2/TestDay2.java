package day2;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class TestDay2 {

	@Test
	void test_part1() {
		Day2 testInstance = new Day2();
		List<String> testDaten = new ArrayList<String>();
		testDaten.add("A Y");
		testDaten.add("B X");
		testDaten.add("C Z");
		assertTrue(testInstance.solvePart1(testDaten) == 15);
	}

	@Test
	void test_part2() {
		Day2 testInstance = new Day2();
		List<String> testDaten = new ArrayList<String>();
		testDaten.add("A Y");
		testDaten.add("B X");
		testDaten.add("C Z");
		assertTrue(testInstance.solvePart2(testDaten) == 12);
	}

}