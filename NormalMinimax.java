//Unbeatable Tic-Tac-Toe AI
//Normal Minimax

import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;

public class NormalMinimax {

	static boolean hasGameEnded = false;
	static boolean isTied = false;
	static boolean isWon = false;
	static int player;
	static int winner;
	static int nodeCount=0;
	//0 = tie, -1 = player 1 win, 1 = player 2 (bot) win
	static int[] possibleScores = {0,-1,1};
	public static void main(String[] args) {
		int[] state = {0,0,0,0,0,0,0,0,0};
        player = 1;
        System.out.println("============================================");
        System.out.println("         ***You are Player 1***");
        System.out.println("***Player 2 is an AI playing against you***");
        System.out.println("============================================");
        printCurrentState(state);
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();
        while (!hasGameEnded)
        {
        	if(player==1) {
            	System.out.print("Player " + player + ": ");
        		String newInput = scanner.next();
        		int userInput;
        		try {
        			userInput = Integer.parseInt(newInput);
        		}
        		catch(Exception e){
        			System.out.println("Please enter the number between 1-9");
        			continue;
        		}
        		if (userInput > 0 && userInput < 10 && state[userInput-1]==0)
        		{
        				state[userInput - 1] = 1;
        		}
        		else if(userInput > 0 && userInput < 10 && state[userInput-1]!=0){
        			System.out.println("Please enter the empty cell");
        			continue;
        		}
        		else {
        			System.out.println("Please enter the number between 1-9");
        			continue;
        		}
        	}
        	else {
        		nodeCount=0;
        		int move = findBestMove(state);
        		state[move]=2;
        		System.out.print("Player " + player + ": ");
    			System.out.println(move+1);
    			System.out.println("Number of visited nodes: " + nodeCount);
        	}
            printCurrentState(state);
            checkWinCondition(state);
            if(checkTieCondition(state)) {
            	hasGameEnded = true;
    			isTied = true;
            }
            if(!hasGameEnded) {
            	SwitchPlayer();
            }
        }
        if (hasGameEnded && isWon)
        {
        	winner = player;
        	System.out.println("Player " + winner + " has won!");
        	System.out.println("============================================");
        }
        else if(hasGameEnded && isTied) {
        	System.out.println("The game is Tied!");
        	System.out.println("============================================");
        }
	}
	
	//Show the current game board
	static void printCurrentState(int[] current)
    {
        for(int i = 0; i<current.length;i++)
        {
            if (current[i] == 0)
            {
                System.out.print(" ");
            }
            else if (current[i] == 1)
            {
            	System.out.print("X");
            }
            else if (current[i] == 2)
            {
            	System.out.print("O");
            }
            if ((i + 1) % 3 != 0)
            {
            	System.out.print("|");
            }
            else
            {
            	System.out.println("");
            }
        }
    }
	
	//Check whether there is a winner
	static void checkWinCondition(int[] currentState)
    {
        if((currentState[0] == 1 && currentState[1] == 1 && currentState[2] == 1) ||
           (currentState[0] == 1 && currentState[4] == 1 && currentState[8] == 1) ||
           (currentState[0] == 1 && currentState[3] == 1 && currentState[6] == 1) ||
           (currentState[1] == 1 && currentState[4] == 1 && currentState[7] == 1) ||
           (currentState[2] == 1 && currentState[4] == 1 && currentState[6] == 1) ||
           (currentState[2] == 1 && currentState[5] == 1 && currentState[8] == 1) ||
           (currentState[3] == 1 && currentState[4] == 1 && currentState[5] == 1) ||
           (currentState[6] == 1 && currentState[7] == 1 && currentState[8] == 1) ||

           (currentState[0] == 2 && currentState[1] == 2 && currentState[2] == 2) ||
           (currentState[0] == 2 && currentState[4] == 2 && currentState[8] == 2) ||
           (currentState[0] == 2 && currentState[3] == 2 && currentState[6] == 2) ||
           (currentState[1] == 2 && currentState[4] == 2 && currentState[7] == 2) ||
           (currentState[2] == 2 && currentState[4] == 2 && currentState[6] == 2) ||
           (currentState[2] == 2 && currentState[5] == 2 && currentState[8] == 2) ||
           (currentState[3] == 2 && currentState[4] == 2 && currentState[5] == 2) ||
           (currentState[6] == 2 && currentState[7] == 2 && currentState[8] == 2))
        {
            hasGameEnded = true;
            isWon = true;
        }
    }
	
	//Check if the game is tied
	static boolean checkTieCondition(int[] currentState) {
		int emptyCount=0;
		for(int i:currentState) {
			if(i==0) {
				emptyCount++;
			}
		}
		if(emptyCount==0 && !isWon) {
			//endGame = true;
			//isTied = true;
			return true;
		}
		return false;
	}

	//Switch player when the round is ended
	static void SwitchPlayer() {
		if(player==1) {
			player = 2;
		}
		else {
			player = 1;
		}
	}
	
	static int findBestMove(int[] currentState) {
		int bestMove = Integer.MIN_VALUE;
		//int score;
		int nextMove=0;
		int[] tempState = Arrays.copyOf(currentState, currentState.length);
		for(int i=0;i<tempState.length;i++) {
			if(tempState[i]==0) {
				tempState[i]=2;
				int score = Minimax(tempState,0,false);
				tempState[i]=0;
				if(score > bestMove) {
					bestMove = score;
					nextMove = i;
				}
			}
		}
		return nextMove;
	}
	
	static int Minimax(int[] currentState, int dept, boolean isMaximizing) {
		nodeCount++;
		if((currentState[0] == 1 && currentState[1] == 1 && currentState[2] == 1) ||
		           (currentState[0] == 1 && currentState[4] == 1 && currentState[8] == 1) ||
		           (currentState[0] == 1 && currentState[3] == 1 && currentState[6] == 1) ||
		           (currentState[1] == 1 && currentState[4] == 1 && currentState[7] == 1) ||
		           (currentState[2] == 1 && currentState[4] == 1 && currentState[6] == 1) ||
		           (currentState[2] == 1 && currentState[5] == 1 && currentState[8] == 1) ||
		           (currentState[3] == 1 && currentState[4] == 1 && currentState[5] == 1) ||
		           (currentState[6] == 1 && currentState[7] == 1 && currentState[8] == 1)) {
			return possibleScores[1];
		}
		else if((currentState[0] == 2 && currentState[1] == 2 && currentState[2] == 2) ||
		           (currentState[0] == 2 && currentState[4] == 2 && currentState[8] == 2) ||
		           (currentState[0] == 2 && currentState[3] == 2 && currentState[6] == 2) ||
		           (currentState[1] == 2 && currentState[4] == 2 && currentState[7] == 2) ||
		           (currentState[2] == 2 && currentState[4] == 2 && currentState[6] == 2) ||
		           (currentState[2] == 2 && currentState[5] == 2 && currentState[8] == 2) ||
		           (currentState[3] == 2 && currentState[4] == 2 && currentState[5] == 2) ||
		           (currentState[6] == 2 && currentState[7] == 2 && currentState[8] == 2)) {
			return possibleScores[2];
		}
		else if(checkTieCondition(currentState)) {
			return possibleScores[0];
		}
		else {
			if(isMaximizing) {
				int[] tempState = Arrays.copyOf(currentState, currentState.length);
				int bestScore = Integer.MIN_VALUE;
				for(int i = 0 ; i<tempState.length ; i++) {
					if(tempState[i]==0) {
						tempState[i]=2;
						int score = Minimax(tempState,dept+1,false);
						tempState[i]=0;
						if(score>bestScore) {
							bestScore = score;
						}
					}
				}
				return bestScore;
			}
			else {
				int[] tempState = Arrays.copyOf(currentState, currentState.length);
				int bestScore = Integer.MAX_VALUE;
				for(int i = 0 ; i<tempState.length ; i++) {
					if(tempState[i]==0) {
						tempState[i]=1;
						int score = Minimax(tempState,dept+1,true);
						tempState[i]=0;
						if(score<bestScore) {
							bestScore = score;
						}
					}
				}
				return bestScore;
			}
		}
	}
}
