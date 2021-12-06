package view;

import components.ChessGridComponent;
import model.ChessPiece;

import javax.swing.*;
import java.awt.*;

public class ChessBoardPanel extends JPanel {

    public int[][] data= new int[8][8];//内部存储棋盘

    private final int CHESS_COUNT = 8;
    private ChessGridComponent[][] chessGrids;

    public ChessBoardPanel(int width, int height) {
        this.setVisible(true);
        this.setFocusable(true);
        this.setLayout(null);
        this.setBackground(Color.BLACK);
        int length = Math.min(width, height);
        this.setSize(length, length);
        ChessGridComponent.gridSize = length / CHESS_COUNT;
        ChessGridComponent.chessSize = (int) (ChessGridComponent.gridSize * 0.8);
        System.out.printf("width = %d height = %d gridSize = %d chessSize = %d\n",
                width, height, ChessGridComponent.gridSize, ChessGridComponent.chessSize);

        initialChessGrids();//return empty chessboard
        initialGame();//add initial four chess

        repaint();
    }

    /**
     * set an empty chessboard
     */
    public void initialChessGrids() {
        chessGrids = new ChessGridComponent[CHESS_COUNT][CHESS_COUNT];
        //todo: new Game
        //draw all chess grids
        for (int i = 0; i < CHESS_COUNT; i++) {
            for (int j = 0; j < CHESS_COUNT; j++) {
                ChessGridComponent gridComponent = new ChessGridComponent(i, j);
                gridComponent.setLocation(j * ChessGridComponent.gridSize, i * ChessGridComponent.gridSize);
                chessGrids[i][j] = gridComponent;//
                this.add(chessGrids[i][j]);
            }
        }
    }

    /**
     * initial origin four chess
     */
    public void initialGame() {
        //new Game
        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++)
            {
                chessGrids[i][j].setChessPiece(null);
            }
        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++)
            {
                data[i][j]=0;
            }
        chessGrids[3][3].setChessPiece(ChessPiece.BLACK);
        chessGrids[3][4].setChessPiece(ChessPiece.WHITE);
        chessGrids[4][3].setChessPiece(ChessPiece.WHITE);
        chessGrids[4][4].setChessPiece(ChessPiece.BLACK);
        data[3][3]=1;
        data[4][4]=1;
        data[4][3]=-1;
        data[3][4]=-1;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }

    public void Put(int chess, int PositionX, int PositionY, int moveX, int moveY){
        if (!(PositionX+moveX>7&&//detect the edge
                PositionX+moveX<0&&
                PositionY+moveY>7&&
                PositionY+moveY<0)&&
                data[PositionX+moveX][PositionY+moveY]==-chess)
        {
            data[PositionX+moveX][PositionY+moveY]=chess;
            if(chess==1)
            chessGrids[PositionX+moveX][PositionY+moveY].setChessPiece(ChessPiece.BLACK);
            else if(chess==-1)
                chessGrids[PositionX+moveX][PositionY+moveY].setChessPiece(ChessPiece.WHITE);
            repaint();
            Put(chess,PositionX+moveX,PositionY+moveY,moveX,moveY);
        }
    }


    public void Put(int chess,int PositionX, int PositionY)
    {
        data[PositionX][PositionY]=chess;
        if(chess==1)
            chessGrids[PositionX][PositionY].setChessPiece(ChessPiece.BLACK);
        else if(chess==-1)
            chessGrids[PositionX][PositionY].setChessPiece(ChessPiece.WHITE);
        repaint();

        int[] directionX = new int[]{0, 0, 1, 1, -1, -1, 1, -1};
        int[] directionY = new int[]{1, -1, 1, -1, -1, 1, 0, 0};
        for(int i=0;i<8;i++)
        {
            if(canPut(chess, PositionX, PositionY,directionX[i],directionY[i],0))
            {
                Put(chess, PositionX, PositionY, directionX[i], directionY[i]);
            }
        }
    }
    public boolean canPut(int chess, int PositionX, int PositionY, int moveX, int moveY, int edible)
    {
        if (PositionX+moveX>7||PositionX+moveX<0||PositionY+moveY>7||PositionY+moveY<0){
            return false;//detect the edge
        }
        else if(data[PositionX+moveX][PositionY+moveY]==0){
            return false;//detect empty spaces
        }
        else if (data[PositionX+moveX][PositionY+moveY] == -chess){
            return canPut(chess,PositionX+moveX,PositionY+moveY,moveX,moveY,edible+1);
        }
        else if(data[PositionX+moveX][PositionY+moveY] == chess && edible!=0){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean canPut(int chess,int PositionX, int PositionY)//这里可以下棋,多态
    {
        int[] directionX = new int[]{0, 0, 1, 1, -1, -1, 1, -1};
        int[] directionY = new int[]{1, -1, 1, -1, -1, 1, 0, 0};
        boolean ans = false;

        for (int i = 0; i < 8; i++) {
            if (canPut(chess, PositionX, PositionY, directionX[i], directionY[i], 0)) {
                ans=true;
            }
        }
        if(data[PositionX][PositionY]!=0)ans=false;
        return ans;
    }

    public boolean canClickGrid(int row, int col, ChessPiece currentPlayer) {
        int chessColor=0;
        if(currentPlayer.getColor()==Color.BLACK)chessColor=1;
        else if(currentPlayer.getColor()==Color.WHITE)chessColor=-1;
        if(canPut(chessColor,row,col)){
            //todo:添加step
            return true;
        }
        else return false;
    }

    public boolean canContinue()//游戏可以继续
    {
        boolean ans=false;
        for(int PositionX=0;PositionX<8;PositionX++)
            for(int PositionY=0;PositionY<8;PositionY++)
            {
                if(canPut(PositionX,PositionY,data[PositionY][PositionX]))ans=true;
            }
        return ans;
    }
}
