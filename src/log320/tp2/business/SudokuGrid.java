package log320.tp2.business;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SudokuGrid {
	private Grid GridSet;
	private int threadCol = 0;
	private int threadRow = 0;
	private int threadDepth = 0;
	ThreadGroup tg = new ThreadGroup ("Threading");
	public SudokuGrid(String path) throws FileNotFoundException {
		GridSet = new Grid(path);
	}

	public void isGridSolvable(){
		/*
		We need to pass in the backtracking function(another like fillGrid) the parameters of the first column first row
		The function will have to call itself while incrementing it's values of col and row.
		If he did 9 tries(Number 9) and failed it will then backtrack to the previous number and increment the number until a new solution occurs.
		
		This function will then get the answer and call the print grid function
		*/
		
		for(int i=0; i < 9; i++) {
			for(int j=0; j < 9; j++) {
				if(GridSet.getGridVal(i, j) == 0) {
					// This will be the start of your 9 threads. This check is necessary if you populated the first rows/columns.
					threadRow = i;
					threadCol = j;
					
				}
			}
		}
		for(int i = 0; i< 4; i++) {
			SolverThread t = new SolverThread(tg, i*2);
			t.start();
			//GridSet.printGrid(i);
		}		
		
		//GridSet.printGrid();
	}
	/*
	public boolean fillGrid(int row, int col, int trd){
		if(GridSet.getGridVal(row, col) == 0) {
			int count = 0;
			while(count < 9 ) {
				if(GridSet.isValid(row, col, count+1, trd)) {
					GridSet.setGridFinalVal(row, col, trd,count+1);
					if(col == 8) {
						if(row == 8)
						{
							return true;
						}
						else {
							if(fillGrid(row+1,0,trd))
							{
								return true;
							}
						}
					}
					else {
						if(fillGrid(row,col+1,trd))
						{
							return true;
						}
					}
				}
				count++;
			}
			if(count == 9 && !GridSet.isValid(row, col, count,trd)) {
				GridSet.setGridFinalVal(row, col, count, 0);
				return false;
			}
			
		} else {
			if(col == 8) {
				if(row == 8)
				{
					return true;
				}
				else {
					if(fillGrid(row+1,0,trd)) 
					{
						return true;
					}
					else
					{
						return false;
					}
				}
			}
			else {
				if(fillGrid(row,col+1,trd))
				{
					return true;
				}
				else
				{
					return false;
				}
			}
		}
		
		return true;
	}*/
	
}
