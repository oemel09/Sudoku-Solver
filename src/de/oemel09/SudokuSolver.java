package de.oemel09;

import java.util.ArrayList;

public class SudokuSolver {

    private final Sudoku sudoku;
    private int current = 0;

    public SudokuSolver(String fileName) throws Exception {
        this.sudoku = new Sudoku(fileName);
    }

    ArrayList<Sudoku> solveAll() {
        ArrayList<Sudoku> solutions = new ArrayList<>();
        Sudoku solution;
        while ((solution = solve()) != null) {
            solutions.add(solution);
            boolean couldStepBack = stepBack();
            if (!couldStepBack) break;
        }
        return solutions;
    }

    Sudoku solve() {
        while (this.hasNext()) {
            Cell currentCell = getCell(current);
            if (currentCell.isFixed()) {
                current++;
            } else {
                boolean isPossible = insertNextPossibleNumber(currentCell);
                if (!isPossible) {
                    boolean couldStepBack = stepBack();
                    if (!couldStepBack) return null;
                }
            }
        }
        return sudoku.copy();
    }

    private boolean stepBack() {
        try {
            stepBackToLastEditableCell();
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
        getCell(current).resetNumber();
        resetAllNextCells();
        return true;
    }

    private boolean insertNextPossibleNumber(Cell currentCell) {
        for (int i = currentCell.getOldNumber(); i < 9; i++) {
            if (canSetNumber(current, i + 1)) {
                currentCell.setNumber(i + 1);
                current++;
                return true;
            }
        }
        return false;
    }

    private void stepBackToLastEditableCell() throws IndexOutOfBoundsException {
        do {
            current--;
        } while (getCell(current).isFixed());
    }

    private void resetAllNextCells() {
        for (int i = current; i < 81 - 1; i++) {
            getCell(i + 1).resetOldNumber();
        }
    }

    private boolean canSetNumber(int currentCell, int number) {
        return getCell(currentCell).getNumber() == 0 && canSetInRow(currentCell, number) && canSetInColumn(currentCell, number)
                && canSetInSquare(currentCell, number);
    }

    private boolean canSetInRow(int currentCell, int number) {
        int row = currentCell / 9;
        boolean canSet = true;
        for (int j = 0; j < sudoku.get().length; j++) {
            if (sudoku.getCell(row, j).getNumber() == number) {
                canSet = false;
                break;
            }
        }
        return canSet;
    }

    private boolean canSetInColumn(int currentCell, int number) {
        int column = currentCell % 9;
        boolean canSet = true;
        for (Cell[] cells : sudoku.get()) {
            if (cells[column].getNumber() == number) {
                canSet = false;
                break;
            }
        }
        return canSet;
    }

    private boolean canSetInSquare(int currentCell, int number) {
        int row = (currentCell / 27) * 3;
        int column = ((currentCell / 3) % 3) * 3;
        boolean canSet = true;
        outer:
        for (int i = row; i < row + 3; i++) {
            for (int j = column; j < column + 3; j++) {
                if (sudoku.getCell(i, j).getNumber() == number) {
                    canSet = false;
                    break outer;
                }
            }
        }
        return canSet;
    }

    private Cell getCell(int currentCell) throws IndexOutOfBoundsException {
        return sudoku.getCell(currentCell / 9, currentCell % 9);
    }

    private boolean hasNext() {
        return current < 81;
    }
}
