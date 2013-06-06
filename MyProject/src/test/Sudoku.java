package test;

public class Sudoku {

	private static final int WIDTH = 9;
	private static final int HEIGHT = 9;
	private static final int TOTAL = WIDTH * HEIGHT;
	private int[] board = new int[TOTAL];
	private String input = "800000000,003600000,070090200,050007000,000045700,000100030,001000068,008500010,090000400";

	private void parse() {
		String[] rows = input.split(",");
		if (rows.length != HEIGHT) {
			throw new IllegalArgumentException("Height is invalid: " + rows);
		}
		for (int y = 0; y < HEIGHT; ++y) {
			String row = rows[y];
			if (row.length() != WIDTH) {
				throw new IllegalArgumentException("Row " + y + " is invalid.");
			}
			for (int x = 0; x < WIDTH; ++x) {
				int value = Integer.parseInt(String.valueOf(row.charAt(x)));
				if (value < 0 || value > 9) {
					throw new IllegalArgumentException(String.format("%d, %d is an invalid value: %d", x, y, value));
				}
				if (value != 0) {
					if (!checkHorizon(x, y, value) || !checkVertical(x, y, value) || !check9Grid(x, y, value)) {
						throw new IllegalArgumentException(String.format("%d, %d is an illegal value: %d", x, y, value));
					}
				}
				setValue(x, y, value);
			}
		}
	}
	private int getValue(int x, int y) {
		return board[getPosition(x, y)];
	}
	
	private void setValue(int x, int y, int value) {
		board[getPosition(x, y)] = value;
	}
	
	private boolean checkHorizon(int x, int y, int value) {
		for (x = 0; x < WIDTH; ++x) {
			if (getValue(x, y) == value) return false;
		}
		return true;
	}
	
	private boolean checkVertical(int x, int y, int value) {
		for (y = 0; y < HEIGHT; ++y) {
			if (getValue(x, y) == value) return false;
		}
		return true;
	}
	
	private boolean check9Grid(int x, int y, int value) {
		int xStart = x / 3 * 3;
		int xEnd = xStart + 3;
		int yStart = y / 3 * 3;
		int yEnd = yStart + 3;
		for (x = xStart; x < xEnd; ++x) {
			for (y = yStart; y < yEnd; ++y) {
				if (getValue(x, y) == value) return false;
			}
		}
		return true;
	}
	
	private void resolve() {
		boolean resolved = true;
		for (int x = 0; x < WIDTH; ++x) {
			for (int y = 0; y < HEIGHT; ++y) {
				if (getValue(x, y) > 0) continue;
				resolved = false;
				for (int value = 1; value <= 9; ++value) {
					if (!checkHorizon(x, y, value)) continue;
					if (!checkVertical(x, y, value)) continue;
					if (!check9Grid(x, y, value)) continue;
					setValue(x, y, value);
					resolve();
					setValue(x, y, 0);
				}
				return;
			}
		}
		if (resolved) {
			printSolution();
		}
	}
	
	private void printSolution() {
		for (int y = 0; y < HEIGHT; ++y) {
			for (int x = 0; x < WIDTH; ++x) {
				System.out.print(getValue(x, y) + " ");
			}
			System.out.println();
		}
	}
	
	private static int getPosition(int x, int y) {
		return y * WIDTH + x;
	}
	
	public static void main(String[] args) {
		Sudoku sudoku = new Sudoku();
		sudoku.parse();
		long ms = System.currentTimeMillis();
		sudoku.resolve();
		System.out.println(System.currentTimeMillis() - ms);
	}
	
	@SuppressWarnings("unused")
	private static void test() {
		System.out.println(getPosition(0, 0) == 0);
		System.out.println(getPosition(8, 8) == 80);
		System.out.println(getPosition(8, 0) == 8);
		System.out.println(getPosition(0, 3) == 27);
		System.out.println(getPosition(7, 8) == 79);
	}
}
