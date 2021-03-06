package view;

import Asssignment4Components.Game;
import Asssignment4Components.Step;
import apple.laf.JRSUIUtils;
import components.ChessGridComponent;
import model.ChessPiece;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class ChessBoardPanel extends JPanel {

    public static int[][] data = new int[8][8];//内部存储棋盘
    public int blackScore;
    public int whiteScore;
    public int[][] nextStep = new int[8][8];//下一步可以下的位置
    public int[][] step = new int[8][8];
    private URL url3;
    private AudioClip ac3;

    private final int CHESS_COUNT = 8;
    private ChessGridComponent[][] chessGrids;

    public ChessGridComponent getChessGrids(int i, int j) {
        return chessGrids[i][j];
    }

    public int getBlackScore() {
        return this.blackScore;
    }

    public int getWhiteScore() {
        return this.whiteScore;
    }
    public void clear()
    {
        blackScore = 2;
        whiteScore = 2;
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                    chessGrids[i][j].setChessPiece(null);
                    data[i][j] = 0;
                }
        chessGrids[3][3].setChessPiece(ChessPiece.BLACK);
        chessGrids[4][4].setChessPiece(ChessPiece.BLACK);
        chessGrids[4][3].setChessPiece(ChessPiece.WHITE);
        chessGrids[3][4].setChessPiece(ChessPiece.WHITE);
        data[3][3]=1;
        data[4][4]=1;
        data[3][4]=-1;
        data[4][3]=-1;
    }

    public ChessBoardPanel(int width, int height, int[][] input) {
        this.setVisible(true);
        this.setFocusable(true);
        this.setLayout(null);
        this.setBackground(Color.CYAN);


        int length = Math.min(width, height);
        this.setSize(length, length);
        ChessGridComponent.gridSize = length / CHESS_COUNT;
        ChessGridComponent.chessSize = (int) (ChessGridComponent.gridSize * 0.8);
        System.out.printf("width = %d height = %d gridSize = %d chessSize = %d\n",
                width, height, ChessGridComponent.gridSize, ChessGridComponent.chessSize);

        initialChessGrids(input);//return empty chessboard
        initialGame(input);//add initial four chess


        if (MainFrame.NORAMLmode == 1 && GameFrame.g.getStepList().size() == 0)
            GameFrame.g.addStep(new Step(2, -1, -1, input));
        if (MainFrame.AImode == 1 && EasyAIFrame.g.getStepList().size() == 0)
            EasyAIFrame.g.addStep(new Step(2, -1, -1, input));
        if (MainFrame.AImode == 2 && DifAIFrame.g.getStepList().size() == 0)
            DifAIFrame.g.addStep(new Step(2, -1, -1, input));
        repaint();
    }


    /**
     * set an empty chessboard
     */
    public void initialChessGrids(int[][] input) {
        chessGrids = new ChessGridComponent[CHESS_COUNT][CHESS_COUNT];
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                data[i][j] = input[i][j];
            }
        for (int i = 0; i < CHESS_COUNT; i++) {
            for (int j = 0; j < CHESS_COUNT; j++) {
                ChessGridComponent gridComponent = new ChessGridComponent(i, j);
                gridComponent.setLocation(j * ChessGridComponent.gridSize, i * ChessGridComponent.gridSize);
                chessGrids[i][j] = gridComponent;//
                this.add(chessGrids[i][j]);
            }
        }
        repaint();
    }

    /**
     * initial origin four chess
     */
    public void initialGame(int[][] input) {
        blackScore = 0;
        whiteScore = 0;
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                if (input[i][j] == 1) {
                    chessGrids[i][j].setChessPiece(ChessPiece.BLACK);
                    data[i][j] = 1;
                    blackScore++;
                } else if (input[i][j] == -1) {
                    chessGrids[i][j].setChessPiece(ChessPiece.WHITE);
                    data[i][j] = -1;
                    whiteScore++;
                }
            }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.orange);//set white color!!!!!!!!!!!!!!!!!!!!!!!!!!!
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }

    public void Put(int chess, int PositionX, int PositionY, int moveX, int moveY) {
        if (!(PositionX + moveX > 7 &&//detect the edge
                PositionX + moveX < 0 &&
                PositionY + moveY > 7 &&
                PositionY + moveY < 0) &&
                data[PositionX + moveX][PositionY + moveY] == -chess) {
            data[PositionX + moveX][PositionY + moveY] = chess;
            if (chess == 1)
                chessGrids[PositionX + moveX][PositionY + moveY].setChessPiece(ChessPiece.BLACK);
            else if (chess == -1)
                chessGrids[PositionX + moveX][PositionY + moveY].setChessPiece(ChessPiece.WHITE);
            repaint();
            Put(chess, PositionX + moveX, PositionY + moveY, moveX, moveY);
        }
    }


    public void Put(int chess, int PositionX, int PositionY) {
        System.out.println("happened");
        data[PositionX][PositionY] = chess;
        if (chess == 1)
            chessGrids[PositionX][PositionY].setChessPiece(ChessPiece.BLACK);
        else if (chess == -1)
            chessGrids[PositionX][PositionY].setChessPiece(ChessPiece.WHITE);
        System.out.println("First putting!");
        paintImmediately(chessGrids[PositionX][PositionY].getX(), chessGrids[PositionX][PositionY].getY(), chessGrids[PositionX][PositionY].getWidth(), chessGrids[PositionX][PositionY].getHeight());

        int[] directionX = new int[]{0, 0, 1, 1, -1, -1, 1, -1};
        int[] directionY = new int[]{1, -1, 1, -1, -1, 1, 0, 0};
        ArrayList<ChessGridComponent> cg_list = new ArrayList<>();
        cg_list.add(chessGrids[PositionX][PositionY]);
        for (int i = 0; i < 8; i++) {
            if (canPut(chess, PositionX, PositionY, directionX[i], directionY[i], 0)) {
                int num = 0;
                int PositionX_copy = PositionX;
                int PositionY_copy = PositionY;
//                Put(chess, PositionX, PositionY, directionX[i], directionY[i]);
                while (PositionX_copy + directionX[i] < 8 && PositionX_copy + directionX[i] >= 0
                        && PositionY_copy + directionY[i] < 8 && PositionY_copy + directionY[i] >= 0
                        && data[PositionX_copy + directionX[i]][PositionY_copy + directionY[i]] == -chess) {
                    num++;
                    PositionX_copy += directionX[i];
                    PositionY_copy += directionY[i];
                }
                for (int j = 1; j <= num; j++) {
                    data[PositionX + directionX[i] * j][PositionY + directionY[i] * j] = chess;
                    if (chess == 1)
                        chessGrids[PositionX + directionX[i] * j][PositionY + directionY[i] * j].setChessPiece(ChessPiece.BLACK);
                    else if (chess == -1)
                        chessGrids[PositionX + directionX[i] * j][PositionY + directionY[i] * j].setChessPiece(ChessPiece.WHITE);
//                    System.out.println(num);
                    cg_list.add(chessGrids[PositionX + directionX[i] * j][PositionY + directionY[i] * j]);
//

                }
            }
        }
        Thread thread = new Thread();
        for (int i =0;i<cg_list.size();i++) {
            ChessGridComponent cg = cg_list.get(i);
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(1);
                    paintImmediately(cg.getX(), cg.getY(), cg.getWidth(), cg.getHeight());
                    File f4 = new File("Put.wav");
                    try {
                        url3 = f4.toURL();
                    } catch (
                            MalformedURLException e) {
                        e.printStackTrace();
                    }
                    ac3 = Applet.newAudioClip(url3);
                    ac3.play();
                }
            });
            thread.start();
            try {
                thread.sleep(300);
            } catch (Exception e) {

            }
        }
    }

    public boolean canPut(int chess, int PositionX, int PositionY, int moveX, int moveY, int edible) {
        if (PositionX + moveX > 7 || PositionX + moveX < 0 || PositionY + moveY > 7 || PositionY + moveY < 0) {
            return false;//detect the edge
        }
        if (data[PositionX + moveX][PositionY + moveY] == 0) {
            return false;//detect empty spaces
        } else if (data[PositionX + moveX][PositionY + moveY] == -chess) {
            return canPut(chess, PositionX + moveX, PositionY + moveY, moveX, moveY, edible + 1);
        } else if (data[PositionX + moveX][PositionY + moveY] == chess && edible != 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean canPut(int chess, int PositionX, int PositionY)//这里可以下棋,多态
    {
        int[] directionX = new int[]{0, 0, 1, 1, -1, -1, 1, -1};
        int[] directionY = new int[]{1, -1, 1, -1, -1, 1, 0, 0};
        boolean ans = false;

        for (int i = 0; i < 8; i++) {
            if (canPut(chess, PositionX, PositionY, directionX[i], directionY[i], 0)) {
                ans = true;
            }
        }
        if (data[PositionX][PositionY] != 0) ans = false;
        return ans;
    }

    public boolean canClickGrid(int row, int col, ChessPiece currentPlayer) {
        int chesscolor = 0;
        if (currentPlayer.getColor() == Color.BLACK) chesscolor = 1;
        else if (currentPlayer.getColor() == Color.WHITE) chesscolor = -1;
        if (canPut(chesscolor, row, col)) return true;
        else return false;
    }

    public boolean canClickGrid2(int row, int col, ChessPiece currentPlayer) {
        int chesscolor = 0;
        if (currentPlayer.getColor() == Color.BLACK) chesscolor = -1;
        else if (currentPlayer.getColor() == Color.WHITE) chesscolor = 1;
        if (canPut(chesscolor, row, col)) return true;
        else return false;
    }

    public boolean canClickGrid(int row, int col) {
        if (data[row][col] == 0) return true;
        else return false;
    }

    public boolean canContinue(int chess)//游戏可以继续
    {
        boolean ans = false;
        for (int PositionX = 0; PositionX < 8; PositionX++)
            for (int PositionY = 0; PositionY < 8; PositionY++) {
                if (canPut(chess, PositionX, PositionY)) ans = true;
            }
        return ans;
    }


    public void countScore() {
        blackScore = 0;
        whiteScore = 0;
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                if (data[i][j] == 1) blackScore++;
                else if (data[i][j] == -1) whiteScore++;
            }
    }

    public boolean canContinue()//游戏可以继续
    {
        if (blackScore + whiteScore == 64) return false;
        else
            return canContinue(-1) || canContinue(1);
    }

    public boolean canContinue(int a,int b){
        if (blackScore + whiteScore == 64) return false;
        else return true;
    }
    public void checkNextStep(int chess) {
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                if (data[i][j] == 0 && canPut(chess, i, j))
                    chessGrids[i][j].setChessPiece(ChessPiece.PINK);
        repaint();
    }

    public void clearNextStep() {
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                if (chessGrids[i][j].getChessPiece() == ChessPiece.PINK) {
                    System.out.println("(" + i + "," + j + ") is next");
                    chessGrids[i][j].setChessPiece(null);
                }
            }
        repaint();
    }

    public static int[][] getData() {
        return data;
    }
}
