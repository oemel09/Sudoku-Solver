package de.oemel09;

import java.util.ArrayList;

public class Application {

    public static void main(String[] args) throws Exception {
        String fileName = "sudoku_03.csv";
        if (args.length == 1) {
            fileName = args[0];
        }

        SudokuSolver sudokuSolver = null;
        try {
            sudokuSolver = new SudokuSolver(fileName);
        } catch (Exception e) {
            System.err.println("Can not parse the sudoku.");
            System.exit(1);
        }
        long startTime = System.currentTimeMillis();
        ArrayList<Sudoku> solutions = new ArrayList<>(sudokuSolver.solveAll());
        long stopTime = System.currentTimeMillis() - startTime;

        for (Sudoku s : solutions) {
            System.out.println(s);
        }

        System.out.printf("Took %d ms to calculate %d solution(s)", stopTime, solutions.size());
    }
}
