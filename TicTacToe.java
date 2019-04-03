package assignment;

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

	public static final int COMPUTER=1;
	public static final int HUMAN=2;
	public char COMPUTER_TURN;
	public char HUMAN_TURN;
	public static final int SIDE=3;
	public static void main(String args[]) {
		TicTacToe tacToe=new TicTacToe();
		String str="0X";
		Random rand=new Random();
		int index=(int)(Math.random()*9);
		tacToe.COMPUTER_TURN=str.charAt(index%2);
		if(index%2==0) {
			tacToe.HUMAN_TURN=str.charAt(1);
		} else {
			tacToe.HUMAN_TURN=str.charAt(0);
		}
		int turn=tacToe.COMPUTER_TURN=='0'?COMPUTER:HUMAN;
		tacToe.playTicTacToe(turn);
	}
	
	public void playTicTacToe(int turn) {
		char[][] board=new char[SIDE][SIDE];
		int[] moves=new int[SIDE*SIDE];
		Scanner sc=new Scanner(System.in);
		int move=-1;
		int index=0;
		initialize_board(board);
		print_Board(board);
		while(gameOver(board)==false && index!=SIDE*SIDE) {
			if(turn==1) {
				move=computer_move(board);
				board[move/3][move%3]=COMPUTER_TURN;
				index++;
				turn=HUMAN;
			} else {
				while(move<0 || move >9 || board[move/3][move%3] != ' ') {
					System.out.println("Enter your move (0-8)");
					move=sc.nextInt();
					index++;
					turn=COMPUTER;
				}
				board[move/3][move%3]=HUMAN_TURN;
			}
			print_Board(board);
		}
		if(gameOver(board)==false && index==SIDE*SIDE) {
			System.out.println("It is a draw");
		} else {
			if(turn==1) 
				turn=HUMAN;
			 else if(turn==2) 
				turn=COMPUTER;
			declare(turn);
		}
	}
	public void declare(int turn) {
		if(turn==1) {
			System.out.println("Computer won");
		} else {
			System.out.println("Human won");
		}
	}
	public void print_Board(char[][] board) {
		System.out.print(board[0][0]);
		System.out.print(" | ");
		System.out.print(board[0][1]);
		System.out.print(" | ");
		System.out.println(board[0][2]);
		System.out.println("------------");
		System.out.print(board[1][0]);
		System.out.print(" | ");
		System.out.print(board[1][1]);
		System.out.print(" | ");
		System.out.println(board[1][2]);
		System.out.println("------------");
		System.out.print(board[2][0]);
		System.out.print(" | ");
		System.out.print(board[2][1]);
		System.out.print(" | ");
		System.out.println(board[2][2]);
		System.out.println("\n");
	}
	
	public boolean gameOver(char[][] board) {
		return (row(board) || column(board) || diagonal(board));
	}
	
	public boolean row(char[][] board) {
		for(int i=0;i<SIDE;i++) {
			if(board[i][0]==board[i][1]&& board[i][1]==board[i][2] && board[i][0]!=' ')
				return true;
		}
		return false;
	}
	
	public boolean column(char[][] board) {
		for(int i=0;i<SIDE;i++) {
			if(board[0][i]==board[1][i]&& board[1][i]==board[2][i] && board[0][i]!=' ')
				return true;
		}
		return false;
	}
	
	public boolean diagonal(char[][] board) {
		if(board[0][0]==board[1][1] && board[1][1]==board[2][2] && board[0][0]!=' ')
			return true;
		if(board[0][2]==board[1][1] && board[1][1]==board[2][0] && board[0][2]!=' ')
			return true;
		return false;
	}
	
	public void initialize_board(char[][] board) {
		for(int i=0;i<SIDE;i++) {
			for(int j=0;j<SIDE;j++) {
				board[i][j]=' ';
			}
		}
	}
	
	public int computer_move(char[][] board) {
		int move=(int)(Math.random()*9);
		while(board[move/3][move%3]!=' ') {
			move=(int)(Math.random()*9);
		}
		return move;
	}
}
