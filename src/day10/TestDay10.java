package day10;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class TestDay10 {

	@Test
	void test_part1() throws IOException{
		Day10 testInstance = new Day10();
		List<String> testDaten = testInstance.readData("C:\\Users\\selig\\Documents\\GitHub\\AdventOfCode2022\\src\\day10\\input_testdata.txt");
		assertTrue(testInstance.solvePart1(testDaten) == 13140);
	}

	@Test
	void test_part2() {
		Day10 testInstance = new Day10();
		List<String> testDaten = new ArrayList<String>();
		testDaten.add("Sabqponm");
		testDaten.add("abcryxxl");
		testDaten.add("accszExk");
		testDaten.add("acctuvwj");
		testDaten.add("abdefghi");
		assertTrue(testInstance.solvePart2(testDaten) == 29);
	}

	@Test
	void test_refactor() throws IOException {
		Day10 testInstance = new Day10();
		List<String> testDaten = testInstance
				.readData("C:\\Users\\selig\\Documents\\GitHub\\AdventOfCode2022\\src\\day10\\input.txt");
		assertTrue(testInstance.solvePart1(testDaten) == 15360);
		assertTrue(testInstance.solvePart2(testDaten) == 522);
	}

}