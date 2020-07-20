package de.oemel09;

public class Cell {

    private final boolean isFixed;
    private int number;
    private int oldNumber;

    public Cell(boolean isFixed, int number) {
        this.isFixed = isFixed;
        this.number = number;
        this.oldNumber = number;
    }

    public Cell(Cell cell) {
        this.isFixed = cell.isFixed;
        this.number = cell.number;
        this.oldNumber = cell.oldNumber;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void resetNumber() {
        this.oldNumber = this.number;
        this.number = 0;
    }

    public int getOldNumber() {
        return oldNumber;
    }

    public void resetOldNumber() {
        this.oldNumber = 0;
    }

    public boolean isFixed() {
        return isFixed;
    }

    @Override
    public String toString() {
        return (number == 0) ? "" : Integer.toString(number);
    }
}
