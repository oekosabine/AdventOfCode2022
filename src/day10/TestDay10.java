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
	void test_part2() throws IOException {
		Day10 testInstance = new Day10();
		List<String> testDaten = testInstance.readData("C:\\Users\\selig\\Documents\\GitHub\\AdventOfCode2022\\src\\day10\\input_testdata.txt");
		String[] solution = testInstance.solvePart2(testDaten);
		assertTrue(solution[0].equals("##..##..##..##..##..##..##..##..##..##.."));
		assertTrue(solution[1].equals("###...###...###...###...###...###...###."));
		assertTrue(solution[2].equals("####....####....####....####....####...."));
		assertTrue(solution[3].equals("#####.....#####.....#####.....#####....."));
		assertTrue(solution[4].equals("######......######......######......####"));
		assertTrue(solution[5].equals("#######.......#######.......#######....."));
	}	

	@Test
	void test_refactor() throws IOException {
		Day10 testInstance = new Day10();
		List<String> testDaten = testInstance
				.readData("C:\\Users\\selig\\Documents\\GitHub\\AdventOfCode2022\\src\\day10\\input.txt");
		assertTrue(testInstance.solvePart1(testDaten) == 15360);
		String[] solution = testInstance.solvePart2(testDaten);
		assertTrue(solution[0].equals("###..#..#.#....#..#...##..##..####..##.."));
		assertTrue(solution[1].equals("#..#.#..#.#....#..#....#.#..#....#.#..#."));
		assertTrue(solution[2].equals("#..#.####.#....####....#.#......#..#..#."));
		assertTrue(solution[3].equals("###..#..#.#....#..#....#.#.##..#...####."));
		assertTrue(solution[4].equals("#....#..#.#....#..#.#..#.#..#.#....#..#."));
		assertTrue(solution[5].equals("#....#..#.####.#..#..##...###.####.#..#."));
	}

}