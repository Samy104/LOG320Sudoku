package log320.tp2.business;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Grid {
	private static Grid gridObject = null;
	private int[][] grid = new int[9][9];
	private int[][][] gridFinal = new int[9][9][9];
	private int threadCol = 0;
	private int threadRow = 0;
	private int threadDepth = 0;
	
	public Grid(String path) throws FileNotFoundException {
		
		
		Scanner inputs = new Scanner(new File(path));
		
		for (int rowIndex = 0; inputs.hasNextLine(); rowIndex++) {
			String row = inputs.nextLine();
			
			for (int columnIndex = 0; columnIndex < 9; columnIndex++) {
				grid[rowIndex][columnIndex] = Character.getNumericValue(row.charAt(columnIndex));
				for(int count = 0; count < 9; count++){
					gridFinal[rowIndex][columnIndex][count] = Character.getNumericValue(row.charAt(columnIndex));
				}
			}
		}
		gridObject = this;
	}
	public static Grid getGrid() {
		if(gridObject != null) {
			return gridObject;
		}
		else{
			return null;
		}
	}
	public int getGridVal(int row, int col) {
		return grid[row][col];
	}
	public int getGridFinalVal(int row, int col, int depth) {
		return gridFinal[row][col][depth];
	}
	public void setGridFinalVal(int row, int col, int depth, int valEntered) {
		 gridFinal[row][col][depth] = valEntered;
		
	}
	public void setThreadRow (int row)
	{
		threadRow = row;
	}
	public void setThreadCol (int col)
	{
		threadCol = col;
	}
	public boolean isValid(int row, int col, int val, int trd) {
		// Check horizontal
		for(int i=0; i < 9; i++) {
			if(gridFinal[row][i][trd] == val) {
				return false;
			}
		}
		// Check vertical
		for(int i=0; i < 9; i++) {
			if(gridFinal[i][col][trd] == val) {
				return false;
			}
		}
		// Check mini-grid
		int miniGridRow;
		int miniGridCol;
		// Rows
		if(row < 3) {
			miniGridRow = 3;
		}
		else if(row >= 3 && row <6) {
			miniGridRow = 6;
		}
		else {
			miniGridRow = 9;
		}
		// Columns
		if(col < 3) {
			miniGridCol = 3;
		}
		else if(col >= 3 && col <6) {
			miniGridCol = 6;
		}
		else {
			miniGridCol = 9;
		}
		
		for(int i=(miniGridRow-3); i < miniGridRow; i++) {
			for(int j=(miniGridCol-3); j < miniGridCol; j++) {
				if(gridFinal[i][j][trd] == val) {
					return false;
				}
			}
		}
		return true;
	}
	public void setDepth(int value) {
		threadDepth = value;
	}
	public int[] getLastPosition(int depth) {
		int[] vag = new int[2];
		for(int i = 8; i >= 0; i--)
		{
			for(int j = 8; j >= 0; j--)
			{
				if(gridFinal[i][j][depth] == 0 && grid[i][j] == 0)
				{
					vag[0] = i;
					vag[1] = j;
				}
			}
		}
		return vag;
	}
	public void printGrid(){
		for(int count = 0;count < 9 ; count++)
		{
			int[] tempVal = getLastPosition(count);
			if(gridFinal[tempVal[0]][tempVal[1]][count] != 0)
			{
				for(int i = 0; i <= 8; i++){
					for(int j=0;j<=8 ;j++){
						System.out.print(gridFinal[i][j][count] + " ") ;
					}
					System.out.println();
				}
			}
		}
	}
	public void printGrid(int depth){
		int[] tempVal = getLastPosition(depth);
		if(gridFinal[tempVal[0]][tempVal[1]][depth] != 0)
		{
			for(int i = 0; i <= 8; i++){
				for(int j=0;j<=8 ;j++){
					System.out.print(gridFinal[i][j][depth] + " ") ;
				}
				System.out.println();
			}
		}
	}
	
}
