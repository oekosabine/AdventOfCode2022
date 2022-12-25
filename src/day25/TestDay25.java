package day25;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class TestDay25 {

	@Test
	void test_part1() {
		Day25 testInstance = new Day25();
		List<String> testDaten = new ArrayList<String>();
		testDaten.add("1=-0-2");
		testDaten.add("12111");
		testDaten.add("2=0=");
		testDaten.add("21");
		testDaten.add("2=01");
		testDaten.add("111");
		testDaten.add("20012");
		testDaten.add("112");
		testDaten.add("1=-1=");
		testDaten.add("1-12");
		testDaten.add("12");
		testDaten.add("1=");
		testDaten.add("122");
		assertTrue((testInstance.solvePart1(testDaten)).equals("2=-1=0")); // 4890 as decimal
	}

	@Test
	void test_part2() {
		Day25 testInstance = new Day25();
		List<String> testDaten = new ArrayList<String>();
		testDaten.add("1=-0-2");
		testDaten.add("12111");
		testDaten.add("2=0=");
		testDaten.add("21");
		testDaten.add("2=01");
		testDaten.add("111");
		testDaten.add("20012");
		testDaten.add("112");
		testDaten.add("1=-1=");
		testDaten.add("1-12");
		testDaten.add("12");
		testDaten.add("1=");
		testDaten.add("122");
		assertTrue((testInstance.solvePart2(testDaten)).equals(""));
	}

	@Test
	void test_refactor() throws IOException {
		Day25 testInstance = new Day25();
		List<String> testDaten = testInstance
				.readData("C:\\Users\\boehm\\eclipse-workspace\\AdventOfCode2022\\src\\day25\\input.txt");
		assertTrue((testInstance.solvePart1(testDaten)).equals("2-=102--02--=1-12=22"));
		assertTrue((testInstance.solvePart2(testDaten)).equals(""));
	}

}