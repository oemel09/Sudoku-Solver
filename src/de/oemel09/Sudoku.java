package de.oemel09;

import java.io.BufferedReader;
import java.io.FileReader;

public class Sudoku {

    private final Cell[][] grid;

    Sudoku() {
        this.grid = new Cell[9][9];
    }

    Sudoku(String fileName) throws Exception {
        this();
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        for (int i = 0; i < 9; i++) {
            String[] row = br.readLine().split(",");
            this.setRow(i, parseInputRow(row));
        }
        br.close();
    }

    void setRow(int rowIndex, Cell[] row) {
        this.grid[rowIndex] = row;
    }

    private Cell[] parseInputRow(String[] inputRow) {
        Cell[] gridRow = new Cell[9];
        for (int i = 0; i < 9; i++) {
            boolean isFixed;
            int inputNumber;
            try {
                isFixed = !inputRow[i].isEmpty();
                inputNumber = parseIntWithDefault(inputRow[i], 0);
            } catch (IndexOutOfBoundsException e) {
                isFixed = false;
                inputNumber = 0;
            }
            gridRow[i] = new Cell(isFixed, inputNumber);
        }
        return gridRow;
    }

    private int parseIntWithDefault(String input, int defaultValue) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public Cell[][] get() {
        return grid;
    }

    public Cell getCell(int x, int y) {
        return grid[x][y];
    }

    public Sudoku copy() {
        Sudoku sudoku = new Sudoku();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudoku.grid[i][j] = new Cell(this.grid[i][j]);
            }
        }
        return sudoku;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j].getNumber() == 0) {
                    sb.append("   ");
                } else {
                    sb.append(" ").append(grid[i][j]).append(" ");
                }
                if (j % 3 == 2 && j != 8) {
                    sb.append("|");
                }
            }
            sb.append("\n");
            if (i % 3 == 2 && i != 8) {
                sb.append("-----------------------------").append("\n");
            }
        }
        return sb.toString();
    }
}
