package components;

//import apple.laf.JRSUIConstants;
import Asssignment4Components.Step;
import controller.GameController;
import model.*;
import view.CheatFrame;
import view.ChessBoardPanel;
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
        if (MainFrame.getMode()%2==1) {
            if (isStart) {
                System.out.println("isstart");
                //找到接口了哈哈哈哈哈哈哈哈
                if (GameFrame.controller.canClick(row, col)) {
                    System.out.println("canclick happened");//如果该点为空，则可以下棋，重新绘制repaint
                    GameFrame.controller.clearNextStep();
                    int color=GameFrame.controller.getCurrentPlayer();
                    GameFrame.controller.Putting(color,row,col);
                    GameFrame.controller.countScore();
                    /**save one step*/
                    int [][] board_copy = new int[8][8];
                    for (int i= 0;i<8;i++){
                        for(int j=0;j<8;j++){
                            board_copy[i][j] = ChessBoardPanel.getData()[i][j];
                        }
                    }
                    Step a=new Step(color,row,col,board_copy);
                    GameFrame.g.addStep(a);
                    for(int k=0;k<GameFrame.g.getStepList().size();k++)
                    {
                        for(int i=0;i<8;i++) {
                            for (int j = 0; j < 8; j++) {
                                System.out.printf("%d ",GameFrame.g.getStepList().get(k).getBoard()[i][j]);
                            }
                            System.out.println();
                        }
                        System.out.println();
                    }
                    System.out.println("habusdkgahgdvbkfucywgcdhjskgf");
                    GameFrame.controller.swapPlayer();System.out.println("tried to swap!!!!!!!!!!!!!!!!!");
                }
                else
                {
                    JOptionPane.showConfirmDialog(null, new JLabel("<html><h2><font color='blue'>this is an </font><font color='#cc22ff'>INVALID</font><font color='#cc22ff'>move.Here are the avalable steps.</font></h2></html>"), "ending~~~", JOptionPane.YES_NO_OPTION);
                    int color1=GameFrame.controller.getCurrentPlayer();
                    GameFrame.controller.checkNextStep(color1);
                }
                if(!GameFrame.controller.canContinue())
                {
                    isStart=false;
                    if (CheatFrame.controller.FindWinner() == 1)
                        JOptionPane.showConfirmDialog(null, new JLabel("<html><h2><font color='blue'>GAME ENDS!</font><font color='#cc22ff'> CONGRATULATIONS TO BLACK!</font></h2></html>"), "ending~~~", JOptionPane.YES_NO_OPTION);
                    else if (CheatFrame.controller.FindWinner() == -1)
                        JOptionPane.showConfirmDialog(null, new JLabel("<html><h2><font color='blue'>GAME ENDS!</font><font color='#cc22ff'> CONGRATULATIONS TO WHITE!</font></h2></html>"), "ending~~~", JOptionPane.YES_NO_OPTION);
                    else
                        JOptionPane.showConfirmDialog(null, new JLabel("<html><h2><font color='blue'>GAME ENDS!</font><font color='#cc22ff'> BUT THERE IS NO WINNER</font></h2></html>"), "ending~~~", JOptionPane.YES_NO_OPTION);
                }
            }
        }else if (MainFrame.getMode()%2==0) {
                if (isStart) {
                    //找到接口了哈哈哈哈哈哈哈哈
                    System.out.println(CheatFrame.controller.canClick(row, col));
                    if (CheatFrame.controller.canClick(row, col)) {
                        //如果该点为空，则可以下棋，重新绘制repaint
                        CheatFrame.controller.clearNextStep();
                        int color= CheatFrame.controller.getCurrentPlayer();
                        CheatFrame.controller.Putting(color, row, col);
                        CheatFrame.controller.countScore();
                        /**save one step*/
                        int [][] board_copy = new int[8][8];
                        for (int i= 0;i<8;i++){
                            for(int j=0;j<8;j++){
                                board_copy[i][j] = ChessBoardPanel.getData()[i][j];
                            }
                        }
                        Step a=new Step(color,row,col,board_copy);
                        GameFrame.g.addStep(a);

                        CheatFrame.controller.swapPlayer();
                        repaint();

                        if (!CheatFrame.controller.canContinue()) {
                            isStart = false;
                            if (CheatFrame.controller.FindWinner() == 1)
                                JOptionPane.showConfirmDialog(null, new JLabel("<html><h2><font color='blue'>GAME ENDS!</font><font color='#cc22ff'> CONGRATULATIONS TO BLACK!</font></h2></html>"), "ending~~~", JOptionPane.YES_NO_OPTION);
                            else if (CheatFrame.controller.FindWinner() == -1)
                                JOptionPane.showConfirmDialog(null, new JLabel("<html><h2><font color='blue'>GAME ENDS!</font><font color='#cc22ff'> CONGRATULATIONS TO WHITE!</font></h2></html>"), "ending~~~", JOptionPane.YES_NO_OPTION);
                            else
                                JOptionPane.showConfirmDialog(null, new JLabel("<html><h2><font color='blue'>GAME ENDS!</font><font color='#cc22ff'> BUT THERE IS NO WINNER</font></h2></html>"), "ending~~~", JOptionPane.YES_NO_OPTION);
                        }
                    } else {
                        JOptionPane.showConfirmDialog(null, new JLabel("<html><h2><font color='blue'>this is an </font><font color='#cc22ff'>INVALID</font><font color='#cc22ff'>move.Here are the avalable steps.</font></h2></html>"), "ending~~~", JOptionPane.YES_NO_OPTION);
                        int color2 = CheatFrame.controller.getCurrentPlayer();
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
