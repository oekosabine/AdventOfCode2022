package day11;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class TestDay11 {

	@Test
	void test_part1() throws IOException{
		Day11 testInstance = new Day11();
		List<String> testDaten = testInstance.readData("C:\\Users\\selig\\Documents\\GitHub\\AdventOfCode2022\\src\\day11\\input_testdata.txt");
		assertTrue(testInstance.solvePart1(testDaten) == 10605);
	}

	@Test
	void test_part2() throws IOException {
		Day11 testInstance = new Day11();
		List<String> testDaten = testInstance.readData("C:\\Users\\selig\\Documents\\GitHub\\AdventOfCode2022\\src\\day11\\input_testdata.txt");
		String solution = testInstance.solvePart2(testDaten);
		assertTrue(solution.equals("##..##..##..##..##..##..##..##..##..##.."));
			}	

	@Test
	void test_refactor() throws IOException {
		Day11 testInstance = new Day11();
		List<String> testDaten = testInstance
				.readData("C:\\Users\\selig\\Documents\\GitHub\\AdventOfCode2022\\src\\day11\\input.txt");
		assertTrue(testInstance.solvePart1(testDaten) == 15360);
		String solution = testInstance.solvePart2(testDaten);
		assertTrue(solution.equals("###..#..#.#....#..#...##..##..####..##.."));
		}

}