package view;

import components.ChessGridComponent;
import model.ChessPiece;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class ChessBoardPanel extends JPanel {

    public int[][] data= new int[8][8];//内部存储棋盘
    private int blackScore;
    private int whiteScore;
    public int[][] nextStep=new int[8][8];//下一步可以下的位置

    private final int CHESS_COUNT = 8;
    private ChessGridComponent[][] chessGrids;

    public int getBlackScore()
    {
        return this.blackScore;
    }

    public int getWhiteScore()
    {
        return this.whiteScore;
    }
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
        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++)
            {
                data[i][j]=0;
            }
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
        chessGrids[3][3].setChessPiece(ChessPiece.BLACK);
        chessGrids[3][4].setChessPiece(ChessPiece.WHITE);
        chessGrids[4][3].setChessPiece(ChessPiece.WHITE);
        chessGrids[4][4].setChessPiece(ChessPiece.BLACK);
        data[3][3]=1;
        data[4][4]=1;
        data[4][3]=-1;
        data[3][4]=-1;
        blackScore=2;
        whiteScore=2;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

            //g.setColor(new Color(1, 1, 1));
            //g.setFont(new Font("微软雅黑", Font.BOLD, 30));
            //g.drawString("点击空格键开始游戏", 225, 225);

    }

    public void Put(int chess, int PositionX, int PositionY, int moveX, int moveY) {
        if (!(PositionX+moveX>7&&//detect the edge
                PositionX+moveX<0&&
                PositionY+moveY>7&&
                PositionY+moveY<0)&&
                data[PositionX+moveX][PositionY+moveY]==-chess)
        {
            data[PositionX + moveX][PositionY + moveY] = chess;
            if (chess == 1)
                chessGrids[PositionX + moveX][PositionY + moveY].setChessPiece(ChessPiece.BLACK);
            else if (chess == -1)
                chessGrids[PositionX + moveX][PositionY + moveY].setChessPiece(ChessPiece.WHITE);
            Timer timer = new Timer();
            TimerTask task=new TimerTask() {
                @Override
                public void run() {
                    System.out.println("repaint delayed!");
                    repaint();
                }
            };
            timer.schedule(task,1000);
            Put(chess,PositionX+moveX,PositionY+moveY,moveX,moveY);
        }
    }




    public void Put(int chess,int PositionX, int PositionY) {
                data[PositionX][PositionY] = chess;
                if (chess == 1)
                    chessGrids[PositionX][PositionY].setChessPiece(ChessPiece.BLACK);
                else if (chess == -1)
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
        if(data[PositionX+moveX][PositionY+moveY]==0){
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
            if (canPut(chess, PositionX, PositionY, directionX[i], directionY[i], 0))
            {
                ans=true;
            }
        }
        if(data[PositionX][PositionY]!=0)ans=false;
        return ans;
    }

    public boolean canClickGrid(int row, int col, ChessPiece currentPlayer) {
        int chesscolor=0;
        if(currentPlayer.getColor()==Color.BLACK)chesscolor=1;
        else if(currentPlayer.getColor()==Color.WHITE)chesscolor=-1;
        if(canPut(chesscolor,row,col))return true;
        else return false;
    }
    public boolean canContinue(int chess)//游戏可以继续
    {
        boolean ans=false;
        for(int PositionX=0;PositionX<8;PositionX++)
            for(int PositionY=0;PositionY<8;PositionY++)
            {
                if(canPut(chess,PositionX,PositionY))ans=true;
            }
        return ans;
    }


    public void countScore()
    {
        blackScore=0;
        whiteScore=0;
        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++)
            {
                if(data[i][j]==1)blackScore++;
                else if(data[i][j]==-1)whiteScore++;
            }
        System.out.println("blackscore"+blackScore);
        System.out.println("whitescore"+whiteScore);
    }

    public boolean canContinue()//游戏可以继续
    {
        if(blackScore+whiteScore==64)return false;
        else
        return canContinue(-1)||canContinue(1);
    }
    public void checkNextStep(int chess)
    {
        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++)
            {
                if(data[i][j]==0 && canPut(chess,i,j))
                {
                    chessGrids[i][j].setChessPiece(ChessPiece.GRAY);
                    repaint();
                }
            }
    }
    public void clearNextStep()
    {
        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++)
            {
                if(chessGrids[i][j].getChessPiece()==ChessPiece.GRAY)
                {
                    System.out.println("("+i+","+j+") is next");
                    chessGrids[i][j].setChessPiece(null);
                }
            }
        repaint();
    }
}
