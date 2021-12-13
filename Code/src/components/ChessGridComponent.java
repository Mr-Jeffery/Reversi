package components;

import apple.laf.JRSUIConstants;
import controller.GameController;
import model.*;
import view.CheatFrame;
import view.GameFrame;
import view.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class ChessGridComponent extends BasicComponent {
    public boolean isStart = true;
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
        System.out.println("mouse clicked");
        if (MainFrame.getGameMode() == 1) {
            System.out.println("isstart");
            if (isStart) {
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
                    GameFrame.controller.swapPlayer();////////////////////////////////////////

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
        }else if (MainFrame.getGameMode() == 2) {
                if (isStart) {
                    System.out.printf("%s clicked (%d, %d)\n", CheatFrame.controller.getCurrentPlayer(), row, col);
                    //找到接口了哈哈哈哈哈哈哈哈
                    System.out.println(CheatFrame.controller.canClick(row, col));
                    if (CheatFrame.controller.canClick(row, col)) {//如果该点为空，则可以下棋，重新绘制repaint
                        CheatFrame.controller.clearNextStep();
                        this.chessPiece = CheatFrame.controller.getCurrentPlayer();

                        int color = 0;
                        if (this.chessPiece.getColor() == Color.BLACK) color = 1;
                        else if (this.chessPiece.getColor() == Color.WHITE) color = -1;
                        CheatFrame.controller.Putting(color, row, col);

                        CheatFrame.controller.countScore();
                        CheatFrame.controller.check();

                        if (!CheatFrame.controller.canContinue()) {
                            isStart = false;
                            if (CheatFrame.controller.FindWinner() == 1)
                                JOptionPane.showMessageDialog(null, "Game ends!Congratulations to BLACK!");
                            else if (CheatFrame.controller.FindWinner() == -1)
                                JOptionPane.showMessageDialog(null, "Game ends!Congratulations to WHITE!");
                            else
                                JOptionPane.showMessageDialog(null, "Game ends!BUT there is no winner");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "This is an invalid move!!Here shows the available steps:");
                        int color2 = 0;
                        ChessPiece c = CheatFrame.controller.getCurrentPlayer();
                        if (c.getColor() == Color.BLACK) color2 = 1;
                        else if (c.getColor() == Color.WHITE) color2 = -1;
                        CheatFrame.controller.checkNextStep(color2);
                    }
                }
            }
        }


    public ChessPiece getChessPiece () {
        return chessPiece;
    }

    public void setChessPiece (ChessPiece chessPiece){
        this.chessPiece = chessPiece;
    }


    public int getRow () {
        return row;
    }

    public int getCol () {
        return col;
    }

    public void drawPiece (Graphics g){
        g.setColor(gridColor);
        g.fillRect(1, 1, this.getWidth() - 2, this.getHeight() - 2);
        if (this.chessPiece != null) {
            g.setColor(chessPiece.getColor());
            g.fillOval((gridSize - chessSize) / 2, (gridSize - chessSize) / 2, chessSize, chessSize);
        }
    }


    @Override
    public void paintComponent (Graphics g){
        super.printComponents(g);
        drawPiece(g);
    }
}
