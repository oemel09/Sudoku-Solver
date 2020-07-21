# Sudoku-Solver
Simple sudoku solver using backtracking. Reading sudoku data from a csv file.

Each csv file represents one sudoku. The sudoku needs to be given in the following format:
  - Nine rows in total.
  - Each row has to be structured by the following rules:
    - If a number is given, type in that number.
    - If the cell is empty, don't input anything.
    - Separate values by a comma `,`.

- Example row: `1,2,3,,5,6,,,9`

The solver is now able to compute all possible solutions for a given sudoku.
