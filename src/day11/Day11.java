package day11;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;

import day8.Day8.Tupel;

public class Day11 {

	public static void main(String[] args) {
		Day11 instanceOfTheDay = new Day11();
		try {
			instanceOfTheDay.solve("C:\\Users\\selig\\Documents\\GitHub\\AdventOfCode2022\\src\\day11\\input.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<String> readData(String inputData) throws IOException {
		Path path = Paths.get(inputData);
		List<String> dataList = Files.readAllLines(path);
		return dataList;
	}

	private void solve(String inputData) throws IOException {
		List<String> dataList = readData(inputData);
		int solution = solvePart1(dataList);
		System.out.println("Solution for part 1: " + solution);
		String solution_string = solvePart2(dataList);
		System.out.println("Solution for part 2: " + solution_string);
	}
	
	public class Monkey {
	    int monkeyNumber;
	    List<Integer> itemList = new ArrayList<Integer>();
	    String operation;
	    int operationValue;
	    int testDivisibleBy;
	    int[] monkeySuccessor = {0,0};
	    public Monkey(int number, List<Integer> items, String op, int opVal, int testDiv, int[] sucMonkey) {
  		    monkeyNumber = number;
  		    for (Integer item : items) {
  	  		    itemList.add(item);
  		    }
  		    operation = op;
  		    operationValue = opVal;
  		    testDivisibleBy = testDiv;	   
  		    monkeySuccessor[0] = sucMonkey[0];
  		    monkeySuccessor[1] = sucMonkey[1];		    
	    }
	}
	
	public int solvePart1(List<String> dataList) {
		int monkey = 0; 
		String operation = null;
		int opValue = 0;
		int divValue = 1;
		int[] monkeySuc = {0,0};
		List<Integer> items = new ArrayList<Integer>(); 
		List<Monkey> Affen = new ArrayList<Monkey>();
		for (String line : dataList) {
			String[] lineSplit = line.split(" ");
			if (lineSplit[0].equals("Monkey")) {
				String[] lineSplit2 = (lineSplit[1]).split(":");
				monkey = Integer.parseInt(lineSplit2[0]);
			}
			else if (lineSplit.length > 2 &&
					lineSplit[2].equals("Starting")) {
				for (int i=4; i< lineSplit.length; i++) {
					String[] lineSplit3 = (lineSplit[i]).split(",");
					items.add(Integer.parseInt(lineSplit3[0]));
				}
			}
			else if (lineSplit.length > 2 && lineSplit[2].equals("Operation:")) {
				if (lineSplit[7].equals("old")) {
					operation = "quad";
					opValue = 1;
				}
				else {
					operation = lineSplit[6]; 
					opValue = Integer.parseInt(lineSplit[7]);
				}
			}
			else if (lineSplit.length > 2 && lineSplit[2].equals("Test:")) {
				divValue = Integer.parseInt(lineSplit[5]);
			}
			else if (lineSplit.length > 5 && lineSplit[4].equals("If")) {
				if (lineSplit[5].equals("true:")) {
					monkeySuc[0] = Integer.parseInt(lineSplit[9]);
				}
				else if (lineSplit[5].equals("false:")) {
					monkeySuc[1] = Integer.parseInt(lineSplit[9]);
				}
			}
			else if (line.isEmpty()) {
				Monkey neuerAffe = new Monkey(monkey, items, operation, opValue, divValue, monkeySuc);
				Affen.add(neuerAffe);
				monkey = 0; 
				operation = null;
				opValue = 0;
				divValue = 1;
				monkeySuc[0] = 0;
				monkeySuc[1] = 0;
				items = new ArrayList<Integer>(); 
			}
		
		}
		return 0;
	}
	
	public String solvePart2(List<String> dataList) {
		return "lines";
	}
}