package components;

import apple.laf.JRSUIConstants;
import model.*;
import view.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class ChessGridComponent extends BasicComponent {
    public boolean isStart=true;
    public static int chessSize;
    public static int gridSize;
    public static Color gridColor = new Color(211, 170, 130);

    private ChessPiece chessPiece;
    private int row;
    private int col;

    public ChessGridComponent(int row, int col) {
        this.setSize(gridSize, gridSize);

        this.row = row;
        this.col = col;
    }

    @Override
    public void onMouseClicked() {
        if(isStart)
        {
            System.out.printf("%s clicked (%d, %d)\n", GameFrame.controller.getCurrentPlayer(), row, col);
            //找到接口了哈哈哈哈哈哈哈哈
            System.out.println(GameFrame.controller.canClick(row, col));
            if (GameFrame.controller.canClick(row, col)) {//如果该点为空，则可以下棋，重新绘制repaint
                GameFrame.controller.clearNextStep();
                this.chessPiece = GameFrame.controller.getCurrentPlayer();

                int color=0;
                if(this.chessPiece.getColor()==Color.BLACK)color=1;
                else if(this.chessPiece.getColor()==Color.WHITE)color=-1;
                GameFrame.controller.Putting(color,row,col);

                GameFrame.controller.countScore();
                GameFrame.controller.swapPlayer();
                /*java.util.Timer timer=new Timer("2");
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        System.out.println("repaint delayed!");
                        repaint();
                    }
                },1000);*/

                if(!GameFrame.controller.canContinue())
                {
                    isStart=false;
                    if(GameFrame.controller.FindWinner()==1)
                        JOptionPane.showMessageDialog(null,"Game ends!Congratulations to BLACK!");
                        else if(GameFrame.controller.FindWinner()==-1)
                    JOptionPane.showMessageDialog(null,"Game ends!Congratulations to WHITE!");
                        else
                        JOptionPane.showMessageDialog(null,"Game ends!BUT there is no winner");
                }
                //if(整个棋盘两方都不能下棋)
                //弹窗 谁赢了 锁死棋盘
            }
            else
            {
                JOptionPane.showMessageDialog(null,"This is an invalid move!!Here shows the available steps:");
                int color1=0;
                ChessPiece c=GameFrame.controller.getCurrentPlayer();
                if( c.getColor()==Color.BLACK)color1=1;
                else if(c.getColor()==Color.WHITE)color1=-1;
                GameFrame.controller.checkNextStep(color1);
            }
        }
    }


    public ChessPiece getChessPiece() {
        return chessPiece;
    }

    public void setChessPiece(ChessPiece chessPiece) {
        this.chessPiece = chessPiece;
    }


    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void drawPiece(Graphics g) {
        g.setColor(gridColor);
        g.fillRect(1, 1, this.getWidth() - 2, this.getHeight() - 2);
        if (this.chessPiece != null) {
            g.setColor(chessPiece.getColor());
            g.fillOval((gridSize - chessSize) / 2, (gridSize - chessSize) / 2, chessSize, chessSize);
        }
    }


    @Override
    public void paintComponent(Graphics g) {
        super.printComponents(g);
        drawPiece(g);
    }


}
