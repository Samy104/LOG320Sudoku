package log320.tp2.business;

class SolverThread extends Thread {
// This class will create n threads(9) and will run the code with a grid attached to each. 
// If a grid returns true. Then return the answer and the time taken. If the method returns false. Then GG.
	   private int currentDepth;
	   private int threadRow;
	   private int threadCol;
	   private Grid GridSet = Grid.getGrid();
	   
	   public SolverThread(ThreadGroup a,int depth) {
		 
		  currentDepth = depth;
	      GridSet.setDepth(depth);
	      
	   }
	   public boolean fillGrid(int row, int col, int trd){
			if(GridSet.getGridVal(row, col) == 0) {
				int count = 0;
				while(count < 9 ) {
					if(GridSet.isValid(row, col, count+1, trd)) {
						GridSet.setGridFinalVal(row, col, trd,count+1);
						if(col == 8) {
							if(row == 8)
							{
								//System.out.println(Thread.currentThread().getThreadGroup().toString());
								Thread.currentThread().getThreadGroup().interrupt();
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
					GridSet.setGridFinalVal(row, col, trd, 0);
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
		}
	   // This is the entry point for the second thread.
	   public void run() {
		   long start = System.nanoTime();
		   try {
	    	  	fillGrid(threadRow,threadCol,currentDepth);
	     	} catch (Exception e) {
	     		//System.out.println("Child interrupted.");
	     		long end = System.nanoTime();
	     		System.out.println("Thread "+(currentDepth+1)+" : " + (end-start)/1000000 + "ms");
	  	      	GridSet.printGrid(currentDepth);
	     	}
	      long end = System.nanoTime();
	      System.out.println("Thread "+(currentDepth+1)+" : " + (end-start)/1000000 + "ms");
	      //GridSet.printGrid(currentDepth);
	      //System.out.println("Thread "+(currentDepth+1)+" is done.");
	   }
	}