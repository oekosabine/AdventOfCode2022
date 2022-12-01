package day1;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class TestDay1 {

	@Test
	void test_part1() {
		Day1 testInstance = new Day1();
		List<String> testDaten = new ArrayList<String>();
		testDaten.add("1000");
		testDaten.add("2000");
		testDaten.add("3000");
		testDaten.add("");
		testDaten.add("4000");
		testDaten.add("");
		testDaten.add("5000");
		testDaten.add("6000");
		testDaten.add("");
		testDaten.add("7000");
		testDaten.add("8000");
		testDaten.add("9000");
		testDaten.add("");
		testDaten.add("10000");
		assertTrue(testInstance.solvePart1(testDaten) == 24000);
	}

	@Test
	void test_part2() {
		Day1 testInstance = new Day1();
		List<String> testDaten = new ArrayList<String>();
		testDaten.add("1000");
		testDaten.add("2000");
		testDaten.add("3000");
		testDaten.add("");
		testDaten.add("4000");
		testDaten.add("");
		testDaten.add("5000");
		testDaten.add("6000");
		testDaten.add("");
		testDaten.add("7000");
		testDaten.add("8000");
		testDaten.add("9000");
		testDaten.add("");
		testDaten.add("10000");
		assertTrue(testInstance.solvePart2(testDaten) == 45000);
	}
}