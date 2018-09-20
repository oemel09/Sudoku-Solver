package de.oemel09;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Stream;

public class Sudoku {

	private Field[][] sudoku = new Field[9][9];
	private int current = 0;

	public static void main(String[] args) throws IOException {
		Sudoku sudoku = new Sudoku("sudoku_02.csv");
		long startTime = System.currentTimeMillis();
		sudoku.solve();
		System.out.println(sudoku);
		System.out.println("needed " + (System.currentTimeMillis() - startTime) + "ms to solve");
	}

	public Sudoku(String fileName) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String row;
		int rowCounter = 0;
		while ((row = br.readLine()) != null) {
			int[] numbers = Stream.of(row.split(";")).mapToInt((x) -> {
				if (x.isEmpty())
					return 0;
				return Integer.parseInt(x);
			}).toArray();
			for (int i = 0; i < 9; i++) {
				try {
					boolean fix = numbers[i] != 0;
					sudoku[rowCounter][i] = new Field(fix, numbers[i]);
				} catch (ArrayIndexOutOfBoundsException e) {
					sudoku[rowCounter][i] = new Field(false, 0);
				}
			}
			rowCounter++;
		}
		br.close();
	}

	private void solve() {
		outer: while (this.hasNext()) {
			Field currentField = getField(current);
			if (currentField.isFix()) {
				current++;
				continue outer;
			} else {
				for (int i = currentField.getOldNumber(); i < 9; i++) {
					if (canSetNumber(current, i + 1)) {
						currentField.setNumber(i + 1);
						current++;
						continue outer;
					}
				}
				do {
					current--;
				} while (getField(current).isFix());
				getField(current).resetNumber();
				for (int i = current; i < 81 - 1; i++) {
					getField(i + 1).resetOldNumber();
				}
			}
		}
	}

	private boolean canSetNumber(int current, int number) {
		return getField(current).getNumber() == 0 && canSetInRow(current, number) && canSetInColumn(current, number)
				&& canSetInSquare(current, number);
	}

	private boolean canSetInRow(int current, int number) {
		int row = current / 9;
		boolean canSet = true;
		for (int j = 0; j < sudoku.length; j++) {
			if (sudoku[row][j].getNumber() == number) {
				canSet = false;
				break;
			}
		}
		return canSet;
	}

	private boolean canSetInColumn(int current, int number) {
		int column = current % 9;
		boolean canSet = true;
		for (int i = 0; i < sudoku.length; i++) {
			if (sudoku[i][column].getNumber() == number) {
				canSet = false;
				break;
			}
		}
		return canSet;
	}

	private boolean canSetInSquare(int current, int number) {
		int row = (current / 27) * 3;
		int column = ((current / 3) % 3) * 3;
//		System.out.println("current: " + current + " | number: " + number);
		boolean canSet = true;
		outer: for (int i = row; i < row + 3; i++) {
			for (int j = column; j < column + 3; j++) {
				if (sudoku[i][j].getNumber() == number) {
					canSet = false;
					break outer;
				}
			}
		}
		return canSet;
	}

	private Field getField(int current) {
		return sudoku[current / 9][current % 9];
	}

	private boolean hasNext() {
		return current < 81;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < sudoku.length; i++) {
			for (int j = 0; j < sudoku[0].length; j++) {
				if (sudoku[i][j].getNumber() == 0) {
					sb.append("   ");
				} else {
					sb.append(" ").append(sudoku[i][j]).append(" ");
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
