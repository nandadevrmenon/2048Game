import java.util.*;
public class Main {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);		
		Random random = new Random();
		
		boolean wrongGridSize = true;
		int size = 4;									//size of grid
		
		while(wrongGridSize) {							//keeps asking for valid grid size from user
		System.out.println("Enter the size of the grid you want to play in (valid values 4-10) : ");
		size = in.nextInt();
		if(size<11 && size>3) wrongGridSize = false;
		}
		
		int[][] board = new int [size][size];							//creates board according to size and adds 1's to two random locations.
		
		int[][] emptySpots = new int [(board.length*board.length)][2]; // creates empty array to hold coordinates of all empty slots
		
		int emptySpotsCounter;											//hold value for number of empty spots
		
		emptySpotsCounter = findEmptySpots(emptySpots,board);				//updates all coordinates in emptySpots array and assigns correct value to numOfEmptySpots 
		addRandom(emptySpots,emptySpotsCounter,board);						//adds 1 to random position on grid
		emptySpotsCounter = findEmptySpots(emptySpots,board);				//updates empty spots
		addRandom(emptySpots,emptySpotsCounter,board);						//adds 1 to another random location in grid
		
		
		
		
		printBoard(board);
		
		

		
		
		
		
		
		
		
		
		
		 
		while(emptySpotsCounter != 0) {
			
			boolean validMove = false;
			
			char moveTo = ' ';
			
			while(!validMove) {
				System.out.println("Enter input(use W,A,S,D) to decide which way you want to move the tiles: ");
				moveTo = Character.toUpperCase(in.next().charAt(0));
				if( moveTo == 'W'|| moveTo == 'A' || moveTo == 'S' || moveTo == 'D') {
					validMove = true;
				}
				else System.out.println("invalid");
			}
			System.out.println(moveTo);
			switch(moveTo) {
			case 'W' :{
				moveUp(board);
			}
			break;
			case 'A' :{
				moveLeft(board);
			}
			break;
			case 'S' :{
				moveDown(board);
			}
			break;
			case 'D':{
				moveRight(board);
			}
			}
			
			printBoard(board);
			
			emptySpotsCounter = findEmptySpots(emptySpots,board);
			
			
			
			addRandom(emptySpots,emptySpotsCounter,board);
			
			emptySpotsCounter = findEmptySpots(emptySpots,board);
			printBoard(board);
			

		
		}	 
		
		
/////////LOGIC FOR PRINTING EMPTY SPOT COORINATES	
//			for (int i = 0 ; i<emptySpots.length ; i++) {
//			for(int j = 0 ;j<emptySpots[i].length;j++) {
//				System.out.print(emptySpots[i][j]+"  ");
//				}
//			System.out.println();
//			}
		 
		 
		 
	}
	
	
	
	
	
	
	
	
	
	
	
	//-----FUNCTIONS----------
	
	public static void printBoard(int[][] board) {
		for(int k = 0 ;k<board.length;k++){
			System.out.print("+----");
		}
		System.out.println("+");
		
		for (int i =0 ; i<board.length ; i++){
			System.out.print("|");
			
			for (int j = 0 ; j <board.length;j++) {
				if(board[i][j] == 0)		System.out.print("    |");
				else if(board[i][j]<10) 	System.out.print(" "+board[i][j]+"  |");
				else if(board[i][j]<100) 	System.out.print(" "+board[i][j]+" |");
				else if(board[i][j]<1000) 	System.out.print(" "+board[i][j]+"|");
				else if(board[i][j]<10000) 	System.out.print(board[i][j]+"|");
			}
			System.out.println();
			
			for(int k = 0 ;k<board.length;k++){
				System.out.print("+----");
			}
			System.out.println("+");
			
		}
		
		
		
	}
	
	
	public static int findEmptySpots(int[][] emptySpots,int[][] board){
		
		
		int counter = 0; 
			
		for (int i = 0 ; i<board.length ; i++) {
			for(int j = 0 ;j<board[i].length;j++) {
				if(board[i][j] == 0 ) {
						emptySpots[counter][0] = i;
						emptySpots[counter][1] = j;
						counter++;
				}
			}
		}
			return counter;
	}
	
	
	
	
	
	public static void addRandom(int[][] emptySpots,int numOfEmptySpots,int[][] board) {
		if(numOfEmptySpots !=0) {
			Random random = new Random();		//based on how the code is placed if this statement does not exits there is a change that num will be 0 and the random class cant accept 0 for nextint();
			int randomIndex = random.nextInt(numOfEmptySpots);
			board[(emptySpots[randomIndex][0])][(emptySpots[randomIndex][1])] = 1;	// we put '1' into board using one of the coordinates inside emptySpots array.
		}
			}
	

	
	
	public static void moveUp(int[][]board) {
		for (int i = 0 ; i<board.length;i++) {
			int[] tempArr = new int[board.length];
			for(int j = 0 ; j < board.length ; j++){
				tempArr[j] = board[(board.length-1)-j][i];
			}
			moveTiles(tempArr);
			mergeTiles(tempArr);
			moveTiles(tempArr);
			for (int k = 0 ; k<board.length;k++) {
				board[(board.length-1)-k][i] = tempArr[k];
			}
		}
			
		
	}
	public static void moveDown(int[][]board) {
			
			for (int i = 0 ; i<board.length;i++) {
				int[] tempArr = new int[board.length];
				for(int j = 0 ; j < board.length ; j++){
					tempArr[j] = board[j][i];
				}
				moveTiles(tempArr);
				mergeTiles(tempArr);

				for (int k = 0 ; k<board.length;k++) {
					board[k][i] = tempArr[k];
				}
			}
		}
	public static void moveRight(int[][]board) {
		
		for (int i = 0 ; i<board.length;i++) {
			int[] tempArr = new int[board.length];
			
			for (int j = 0 ; j <board[i].length; j++) {
				tempArr[j] = board[i][j];
			}
			
			moveTiles(tempArr);
			mergeTiles(tempArr);

			
			for (int k = 0 ; k <board.length ; k++) {
				board[i][k] = tempArr[k];
			}
		}
	}
	

	
	public static void moveLeft(int[][] board) {
		
		for (int i = 0 ; i < board.length; i ++) {
			int arr[] = new int[board.length];
			for(int j = 0 ; j <board.length; j++) {
				arr[j] = board[i][board.length-1-j];
			}
			moveTiles(arr);
			mergeTiles(arr);
			moveTiles(arr);
			for (int k = 0 ; k <board.length; k++) {
				board[i][k] = arr[board.length-1-k];
			}
		}
	}
	
	public static void moveTiles(int[] arr) { 			//function that shifts all tiles in a row to extreme left
		for (int i = arr.length-2 ; i>=0; i--) {		//it starts checking from the second last element of the array as the last element will not need to be moved
			if(arr[i] != 0) {						// it checks if element is 0 because if element is 0 it does not need to be moved.
				for(int j = i; j<arr.length-1;j++) { 	// for loop runs through all elements in front of arr[i]
					if(arr[j+1] ==0) {					// if element in front is 0, they are swapped.
						arr[j+1] = arr[j];
						arr[j] = 0;
					}
					
				}
			}
		}
	}	
	
	public static void mergeTiles(int[] arr) {		//function that merges consecutive equal elements of an array
		for (int i = 0 ; i<arr.length-1;i++) {
			if(arr[i] == arr[i+1]) {
				arr[i+1] = 2*(arr[i+1]);
				arr[i] = 0 ;
				i++;
				}
		}
		moveTiles(arr);
	}
	
}


