package day7;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class TestDay7 {

	@Test
	void test_part1() {
		Day7 testInstance = new Day7();
		List<String> testDaten = new ArrayList<String>();
		testDaten.add("$ cd /");
		testDaten.add("$ ls");
		testDaten.add("dir a");
		testDaten.add("14848514 b.txt");
		testDaten.add("8504156 c.dat");
		testDaten.add("dir d");
		testDaten.add("$ cd a");
		testDaten.add("$ ls");
		testDaten.add("dir e");
		testDaten.add("29116 f");
		testDaten.add("2557 g");
		testDaten.add("62596 h.lst");
		testDaten.add("$ cd e");
		testDaten.add("$ ls");
		testDaten.add("584 i");
		testDaten.add("$ cd ..");
		testDaten.add("$ cd ..");
		testDaten.add("$ cd d");
		testDaten.add("$ ls");
		testDaten.add("4060174 j");
		testDaten.add("8033020 d.log");
		testDaten.add("5626152 d.ext");
		testDaten.add("7214296 k");
		assertTrue(testInstance.solvePart1(testDaten) == 95437);
	}

	@Test
	void test_part2() {
		Day7 testInstance = new Day7();
		List<String> testDaten = new ArrayList<String>();
		testDaten.add("$ cd /");
		testDaten.add("$ ls");
		testDaten.add("dir a");
		testDaten.add("14848514 b.txt");
		testDaten.add("8504156 c.dat");
		testDaten.add("dir d");
		testDaten.add("$ cd a");
		testDaten.add("$ ls");
		testDaten.add("dir e");
		testDaten.add("29116 f");
		testDaten.add("2557 g");
		testDaten.add("62596 h.lst");
		testDaten.add("$ cd e");
		testDaten.add("$ ls");
		testDaten.add("584 i");
		testDaten.add("$ cd ..");
		testDaten.add("$ cd ..");
		testDaten.add("$ cd d");
		testDaten.add("$ ls");
		testDaten.add("4060174 j");
		testDaten.add("8033020 d.log");
		testDaten.add("5626152 d.ext");
		testDaten.add("7214296 k");
		assertTrue(testInstance.solvePart2(testDaten) == 26);
	}

	@Test
	void test_refactor() throws IOException {
		Day7 testInstance = new Day7();
		List<String> testDaten = testInstance
				.readData("C:\\Users\\boehm\\eclipse-workspace\\AdventOfCode2022\\src\\day7\\input.txt");
		assertTrue(testInstance.solvePart1(testDaten) == 1542);
		assertTrue(testInstance.solvePart2(testDaten) == 3153);
	}

}