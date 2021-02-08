package minesweeper;
import java.util.Scanner;
import java.util.Random;
public class MineSweeper {
    char[][] mineSweeperBoard;
    boolean[][] isCellEnable;
    Random r;
    int total_bomb;
    public MineSweeper()
    {
        this.mineSweeperBoard=new char[9][9];
        this.isCellEnable=new boolean[9][9];
        this.r=new Random();
        this.total_bomb=10;
        this.initialize();
    }
    public void initialize()
    {
        for(int i=0;i<mineSweeperBoard.length;i++)
        {
            for(int j=0;j<mineSweeperBoard[i].length;j++)
            {
                this.mineSweeperBoard[i][j]='0';
                this.isCellEnable[i][j]=true;
            }
        }
        insertMine();
    }
    public void insertMine()
    {
        int i=r.nextInt(9);
        int j=r.nextInt(9);
        this.mineSweeperBoard[i][j]='M';
        for(int k=1;k<this.total_bomb;k++)
        {
            while(true){
                i=r.nextInt(9);
                j=r.nextInt(9);
                if(this.mineSweeperBoard[i][j]!='M')
                    break;
            }
            this.mineSweeperBoard[i][j]='M';
        }
        calculateNeighbour();
    }
    public void calculateNeighbour()
    {
        for(int i=0;i<this.mineSweeperBoard.length;i++)
        {
            for(int j=0;j<this.mineSweeperBoard[i].length;j++)
            {
                if(this.mineSweeperBoard[i][j]!='M')
                {
                    int count=0;
                    if(i>0 && j>0 && this.mineSweeperBoard[i-1][j-1]=='M')
                        count++;
                    if(i>0 && this.mineSweeperBoard[i-1][j]=='M')
                        count++;
                    if(i>0 && j+1<this.mineSweeperBoard.length && this.mineSweeperBoard[i-1][j+1]=='M')
                        count++;
                    if((i+1) < this.mineSweeperBoard.length && j+1 < this.mineSweeperBoard.length && this.mineSweeperBoard[i+1][j+1]=='M')
                        count++;
                    if((i+1) < this.mineSweeperBoard.length && this.mineSweeperBoard[i+1][j]=='M')
                        count++;
                    if((i+1) < this.mineSweeperBoard.length && j>0 && this.mineSweeperBoard[i+1][j-1]=='M')
                        count++;
                    if(j>0 && this.mineSweeperBoard[i][j-1]=='M')
                        count++;
                    if((j+1) < this.mineSweeperBoard.length && this.mineSweeperBoard[i][j+1]=='M')
                        count++;
                    this.mineSweeperBoard[i][j]=(char)(count+'0');
                }
            }
        }
        print();
    }
    public void enableNeighbourZeroCells(int i,int j){
        if(this.mineSweeperBoard[i][j]!='0')
        {
            this.isCellEnable[i][j]=false;
            return;
        }
        this.isCellEnable[i][j]=false;
        if(i>0 && j>0 && this.mineSweeperBoard[i-1][j-1]!='M' && this.isCellEnable[i-1][j-1]==true)
            enableNeighbourZeroCells(i-1,j-1);
        if(i>0 && this.mineSweeperBoard[i-1][j]!='M' && this.isCellEnable[i-1][j]==true)
            enableNeighbourZeroCells(i-1,j);
        if(i>0 && j+1<this.mineSweeperBoard.length && this.mineSweeperBoard[i-1][j+1]!='M' && this.isCellEnable[i-1][j+1]==true)
            enableNeighbourZeroCells(i-1,j+1);
        if((i+1) < this.mineSweeperBoard.length && j+1 < this.mineSweeperBoard.length && this.mineSweeperBoard[i+1][j+1]!='M' && this.isCellEnable[i+1][j+1]==true)
            enableNeighbourZeroCells(i+1,j+1);
        if((i+1) < this.mineSweeperBoard.length && this.mineSweeperBoard[i+1][j]!='M' && this.isCellEnable[i+1][j]==true)
            enableNeighbourZeroCells(i+1,j);
        if((i+1) < this.mineSweeperBoard.length && j>0 && this.mineSweeperBoard[i+1][j-1]!='M' && this.isCellEnable[i+1][j-1]==true)
            enableNeighbourZeroCells(i+1,j-1);
        if(j>0 && this.mineSweeperBoard[i][j-1]!='M' && this.isCellEnable[i][j-1]==true)
            enableNeighbourZeroCells(i,j-1);
        if((j+1) < this.mineSweeperBoard.length && this.mineSweeperBoard[i][j+1]!='M' && this.isCellEnable[i][j+1]==true)
            enableNeighbourZeroCells(i,j+1);
    }
    public void print()
    {
        System.out.print("    ");
        for(int i=0;i<this.mineSweeperBoard.length;i++)
        {
            System.out.print(i+"   ");
        }
        System.out.println("\n");
        for(int i=0;i<this.mineSweeperBoard.length;i++)
        {
            System.out.print(i+"   ");
            for(int j=0;j<this.mineSweeperBoard[i].length;j++){
                if(this.isCellEnable[i][j]==false)
                    System.out.print(this.mineSweeperBoard[i][j]+"   ");
                else
                    System.out.print("X   ");
            }
            System.out.println();
        }
    }
    public void enableAllMine()
    {
        for(int i=0;i<this.mineSweeperBoard.length;i++)
        {
            for(int j=0;j<this.mineSweeperBoard[i].length;j++)
            {
                if(this.mineSweeperBoard[i][j]=='M')
                    this.isCellEnable[i][j]=false;
            }
        }
    }
    public boolean isWon()
    {
        for(int i=0;i<this.mineSweeperBoard.length;i++)
        {
            for(int j=0;j<this.mineSweeperBoard[i].length;j++)
            {
                if(this.isCellEnable[i][j]==true && this.mineSweeperBoard[i][j]!='M')
                    return false;
            }
        }
        return true;
    }
    public void startExecution(int row,int column){
        if(this.mineSweeperBoard[row][column]=='M')
        {
            this.enableAllMine();
            this.print();
            System.out.println("You lose the game.");
            System.exit(0);
        }
        else if(this.mineSweeperBoard[row][column]!='0' && this.isCellEnable[row][column]==true)
        {
            this.isCellEnable[row][column]=false;
            this.print();
        }
        else if(this.mineSweeperBoard[row][column]=='0' && this.isCellEnable[row][column]==true)
        {
            this.enableNeighbourZeroCells(row, column);
            this.print();
        }
        if(this.isWon()==true)
        {
            System.out.println("You won the match");
            System.exit(0);
        }
    }
    public static void main(String[] args) {
        MineSweeper ms=new MineSweeper();
        Scanner scanner=new Scanner(System.in);
        while(true)
        {
            System.out.println("Enter the row number");
            int row=scanner.nextInt();
            System.out.println("Enter the column number");
            int column=scanner.nextInt();
            ms.startExecution(row , column);
        }
    }
}
