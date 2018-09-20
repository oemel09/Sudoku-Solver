# Sudoku-Solver
Simple sudoku solver using backtracking. Reading sudoku data from a csv file.

Each csv file represents one sudoku. It needs the following format:
  - Nine rows in total.
  - Each row has to look like this: 1,,,3,,6,,,
    - So if there is a number, type in that number.
    - Separate columns by a comma (,).
    - If the field is empty, just type in the next comma.
