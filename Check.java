package checker;
import checker.Check;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import java.util.Scanner;

public class Check {
	public static void main(String[] args) {
		String[][] t = 
			{{ "   "," 0 "," 1 "," 2 "," 3 " ," 4 " ," 5 " ," 6 "," 7 "," ","   <- X axis"},
			{ "  ","+-","---","---","---","---","---" ,"---" ,"-----","-+","" },		
			{ "0 ","|","   "," 1 ","   "," 1 ","   " ," 1 " ,"   "," 1 "," |" },
			{ "1 ","|"," 1 ","   "," 1 ","   " ," 1 " ,"   "," 1 ","   "," |"},
			{ "2 ","|","   "," 1 ","   "," 1 ","   " ," 1 " ,"   "," 1 "," |" },
			{ "3 ","|","   ","   ","   ","   ","   " ,"   " ,"   ","   "," |"},
			{ "4 ","|","   ","   ","   ","   ","   " ,"   " ,"    ","  "," |"},
			{ "5 ","|"," 2 ","   "," 2 ","   "," 2 " ,"   " ," 2 ","   "," |"},
			{ "6 ","|","   "," 2 ","   "," 2 ","   " ," 2 " ,"   "," 2 "," |"},
			{ "7 ","|"," 2 ","   "," 2 ","   "," 2 " ,"   " ," 2 ","   "," |"},
			{ "  ","+-","---","---","---","---","---" ,"---" ,"---","---","+"},
			{ "   "," 0 "," 1 "," 2 "," 3 " ," 4 " ," 5 " ," 6 "," 7 "," ",""}
			};
		
		Player p1 = new Player();
		p1.player(true);
		p1.Name();
		board m = new board(t);
		int count=0;
		
		while (count!=10) {
			
		
		m.printBoard();
		
		System.out.println("Turn of Player no. "+p1.name+" \nCoordinate of piece to move");
		
		try {
		Input oldcord = new Input();
		oldcord.input();
		int x = oldcord.x;
		int y = oldcord.y;
		
		
		System.out.println("Coordinate of new position");
		
		Input newcord = new Input();
		newcord.input();
		int x1 = newcord.x;
		int y1 = newcord.y;
		
		
		count++;
		if (p1.isvalidmove(m.m, x, y, x1, y1)) {
			m.move(p1, x, y, x1, y1);
			p1.Name();

		}
		}
		catch (Exception e) {
			System.out.println("-----WRONG INPUT----- \n Try again please");
		}
		
		}	
	}
}

class Input {
	int x;
	int y;
	
	public void input() {
		Scanner inp = new Scanner(System.in); 
		this.x = inp.nextInt();
		System.out.println(" Enter Y: "); 
		this.y = inp.nextInt();
	}

}

class Position {
	int x;
	int y;
	
	Position(int x1, int y1) {
		x = x1;
		y = y1;
		
		}
}

class Player {
	boolean player;
	String name;
	
	public void player(boolean p) {
		player = p;
	}
	public String Name() {
		if (player) {name=" 1 ";}
		else {name = " 2 "; }
		return name;
	}


	public boolean isvalidmove(String [] [] m, int x, int y, int x1, int y1) {
		//Out of range
		if (x<0 ||x>7 || y<0 || y>7 || x1<0 ||x1>7 || y1<0 || y1>7 ) {
			System.out.println("Out of bound");
			return false;

		}
		
		//Putting something where there is already something, or picking a wrong player
		if (m[y1+2][x1+2]!= "   " ||(player && m[y+2][x+2] != " 1 ") || ! player && m[y+2][x+2] != " 2 ") 
			{
			System.out.println("Wrong input, you have nothing here");
			return false;
		}

		
		//Not moving in right direction
		if (player && (y1-y!=1 || Math.abs(x1-x)!=1)||
			!player && (y1-y!=-1 || Math.abs(x1-x)!=1)) {
			System.out.println("The move is not allowed");
			return false;
			//Move is not allowed.  
		}
	
		else {
			return true;
		}		
	}
}

class board {
	String[][] m;
	
	public board(String[][] bo) {
		m = bo;
	}

	public void printBoard() {

		int i=0;
		int j=0;
		String s = "";
		
		for (i=0;i<=11;i++) {
			
			for (j=0;j<=10;j++) {
				s=s+m[i][j];
			}
			System.out.println(s);
			s="";
			
		}
		//getInput(m,player2,player1);
	}
	
	public void move(Player p1,int x,int y,int x1,int y1) {
		m[y+2][x+2]= "   ";
		m[y1+2][x1+2]= p1.name;
		if (p1.player) {p1.player(false);}
		else p1.player(true);
		System.out.println("Piece moved!");
	}
}



// ----------------------------------------------//
// ---------------- UNIT TEST -------------------//

import static org.junit.jupiter.api.Assertions.*;

class MyTest {
    private String[][] board;
    private int[] coords;
    private Player player1;


    @Test
    void outofbound() {
        player1 = new Player();
        player1.player(true);
        
        this.board = new String[][]	{{ "   "," 0 "," 1 "," 2 "," 3 " ," 4 " ," 5 " ," 6 "," 7 "," ","   <- X axis"},
    				{ "  ","+-","---","---","---","---","---" ,"---" ,"-----","-+","" },		
    				{ "0 ","|","   "," 1 ","   "," 1 ","   " ," 1 " ,"   "," 1 "," |" },
    				{ "1 ","|"," 1 ","   "," 1 ","   " ," 1 " ,"   "," 1 ","   "," |"},
    				{ "2 ","|","   "," 1 ","   "," 1 ","   " ," 1 " ,"   "," 1 "," |" },
    				{ "3 ","|","   ","   ","   ","   ","   " ,"   " ,"   ","   "," |"},
    				{ "4 ","|","   ","   ","   ","   ","   " ,"   " ,"    ","  "," |"},
    				{ "5 ","|"," 2 ","   "," 2 ","   "," 2 " ,"   " ," 2 ","   "," |"},
    				{ "6 ","|","   "," 2 ","   "," 2 ","   " ," 2 " ,"   "," 2 "," |"},
    				{ "7 ","|"," 2 ","   "," 2 ","   "," 2 " ,"   " ," 2 ","   "," |"},
    				{ "  ","+-","---","---","---","---","---" ,"---" ,"---","---","+"},
    				{ "   "," 0 "," 1 "," 2 "," 3 " ," 4 " ," 5 " ," 6 "," 7 "," ",""}
    				};
        };
        assertEquals(true, player1.isvalidmove(board,1,2,0,3));
    }
}

// I has some problems with running the code. 'import org can not be resolved'. 
// I tried fixing this by importing some libraries and changing paths for them, without luck.
// I would like to talk to you about this in class, since this has made it hard for me
// to do the unit testing. 
