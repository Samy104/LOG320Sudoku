package log320.tp2.presentation;

import java.io.FileNotFoundException;

import log320.tp2.business.SudokuGrid;

public class Main {
	public static void main(String[] argv) throws FileNotFoundException {
		SudokuGrid grid = new SudokuGrid("puzzle/sudo_worst.sud");
		grid.isGridSolvable();
		
		
		/*System.out.println(grid.getValue(1, 2));
		System.out.println(grid.getValue(0, 0));
		System.out.println(grid.getValue(0, 3));*/
	}
}
