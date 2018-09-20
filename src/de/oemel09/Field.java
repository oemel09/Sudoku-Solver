package de.oemel09;

public class Field {

	private boolean fix;
	private int number;
	private int oldNumber;

	public Field(boolean fix, int number) {
		this.fix = fix;
		this.number = number;
		this.oldNumber = number;
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

	public boolean isFix() {
		return fix;
	}

	@Override
	public String toString() {
		return (number == 0) ? "" : Integer.toString(number);
	}
}
