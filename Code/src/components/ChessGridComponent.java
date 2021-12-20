package components;

//import apple.laf.JRSUIConstants;

import AI.AI;
import Asssignment4Components.Step;
import controller.GameController;
import model.*;
import sun.jvm.hotspot.runtime.JavaThread;
import view.*;

import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageObserver;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import static java.awt.Toolkit.getDefaultToolkit;

public class ChessGridComponent extends BasicComponent {
    public boolean isStart = true;
    public static int chessSize;
    public static int gridSize;
    public static Color gridColor = new Color(211, 170, 130);

    private ChessPiece chessPiece;
    private int row;
    private int col;

    private URL url3;
    private AudioClip ac3;
    private boolean setDelay = false;

    public ChessGridComponent(int row, int col) {
        this.setSize(gridSize, gridSize);

        this.row = row;
        this.col = col;
    }

    @Override
    public void onMouseClicked() {
        System.out.println("mouse clicked");
        if (MainFrame.NORAMLmode == 1) {
            if (isStart) {
                System.out.println("isstart");
                //找到接口了哈哈哈哈哈哈哈哈
                if (GameFrame.controller.canClick(row, col)) {

                    System.out.println("canclick happened");//如果该点为空，则可以下棋，重新绘制repaint
                    GameFrame.controller.clearNextStep();
                    int color = GameFrame.controller.getCurrentPlayer();
                    GameFrame.controller.Putting(color, row, col);
                    GameFrame.controller.countScore();

                    /**save one step*/
                    int[][] board_copy = new int[8][8];
                    for (int i = 0; i < 8; i++) {
                        for (int j = 0; j < 8; j++) {
                            board_copy[i][j] = ChessBoardPanel.getData()[i][j];
                        }
                    }
                    Step a = new Step(color, row, col, board_copy);
                    GameFrame.g.addStep(a);
                    GameFrame.controller.swapPlayer();
                } else {
                    File f4 = new File("error.wav");
                    try {
                        url3 = f4.toURL();
                    } catch (
                            MalformedURLException e) {
                        e.printStackTrace();
                    }
                    ac3 = Applet.newAudioClip(url3);
                    ac3.play();
                    JOptionPane.showConfirmDialog(null, new JLabel("<html><h2><font color='blue'>this is an </font><font color='#cc22ff'>INVALID</font><font color='#cc22ff'>move.Here are the avalable steps.</font></h2></html>"), "ending~~~", JOptionPane.YES_NO_OPTION);
                    int color1 = GameFrame.controller.getCurrentPlayer();
                    GameFrame.controller.checkNextStep(color1);
                }
                if (!GameFrame.controller.canContinue()) {
                    isStart = false;
                    if (GameFrame.controller.FindWinner() == 1)
                        JOptionPane.showMessageDialog(null, "<html><h2><font color='blue'>GAME ENDS!</font><font color='#cc22ff'> CONGRATULATIONS TO BLACK!</font></h2></html>");
                    else if (GameFrame.controller.FindWinner() == -1)
                        JOptionPane.showMessageDialog(null,"<html><h2><font color='blue'>GAME ENDS!</font><font color='#cc22ff'> CONGRATULATIONS TO WHITE!</font></h2></html>");
                    else
                        JOptionPane.showMessageDialog(null, "<html><h2><font color='blue'>GAME ENDS!</font><font color='#cc22ff'> BUT THERE IS NO WINNER</font></h2></html>");
                }
            }
        } else if (MainFrame.CHEATmode == 1) {
            if (isStart) {
                if (CheatFrame.controller.canClick(row, col, 1)) {
                    File f4 = new File("Put.wav");
                    try {
                        url3 = f4.toURL();
                    } catch (
                            MalformedURLException e) {
                        e.printStackTrace();
                    }
                    ac3 = Applet.newAudioClip(url3);
                    ac3.play();
                    //如果该点为空，则可以下棋，重新绘制repaint
                    CheatFrame.controller.clearNextStep();
                    int color = CheatFrame.controller.getCurrentPlayer();
                    CheatFrame.controller.Putting(color, row, col);
                    CheatFrame.controller.countScore();
                    /**save one step*/
                    int[][] board_copy = new int[8][8];
                    for (int i = 0; i < 8; i++) {
                        for (int j = 0; j < 8; j++) {
                            board_copy[i][j] = ChessBoardPanel.getData()[i][j];
                        }
                    }
                    Step a = new Step(color, row, col, board_copy);
                    GameFrame.g.addStep(a);

                    CheatFrame.controller.swapPlayer();
                    repaint();

                    if (!CheatFrame.controller.canContinue(1,1)) {
                        isStart = false;
                        if (CheatFrame.controller.FindWinner() == 1)
                            JOptionPane.showMessageDialog(null, "<html><h2><font color='blue'>GAME ENDS!</font><font color='#cc22ff'> CONGRATULATIONS TO BLACK!</font></h2></html>");
                        else if (CheatFrame.controller.FindWinner() == -1)
                            JOptionPane.showMessageDialog(null,"<html><h2><font color='blue'>GAME ENDS!</font><font color='#cc22ff'> CONGRATULATIONS TO WHITE!</font></h2></html>");
                        else
                            JOptionPane.showMessageDialog(null, "<html><h2><font color='blue'>GAME ENDS!</font><font color='#cc22ff'> BUT THERE IS NO WINNER</font></h2></html>");
                    }
                }
            }
        } else if (MainFrame.AImode == 1) {
            if (isStart) {
                System.out.println("isstart");
                //找到接口了哈哈哈哈哈哈哈哈
                if (EasyAIFrame.controller.canClick(row, col)) {
                    File f4 = new File("Put.wav");
                    try {
                        url3 = f4.toURL();
                    } catch (
                            MalformedURLException e) {
                        e.printStackTrace();
                    }
                    ac3 = Applet.newAudioClip(url3);
                    ac3.play();

                    EasyAIFrame.controller.clearNextStep();
                    int color = EasyAIFrame.controller.getCurrentPlayer();
                    EasyAIFrame.controller.Putting(color, row, col);
                    EasyAIFrame.controller.countScore();
                    repaint();
                    /**save one step*/
                    int[][] board_copy = new int[8][8];
                    for (int i = 0; i < 8; i++) {
                        for (int j = 0; j < 8; j++) {
                            board_copy[i][j] = ChessBoardPanel.getData()[i][j];
                        }
                    }
                    Step a = new Step(color, row, col, board_copy);
                    EasyAIFrame.g.addStep(a);

                    int[][] board_copy2 = new int[8][8];
                    for (int i = 0; i < 8; i++) {
                        for (int j = 0; j < 8; j++) {
                            board_copy2[j][i] = -ChessBoardPanel.getData()[i][j];
                        }
                    }//change color
                    AI ai = new AI();
                    int[] coordinates = ai.play(board_copy2, color,2);
                    int setX = coordinates[0];//
                    int setY = coordinates[1];//
                    System.out.println("aiX:" + setY + " aiY:" + setX);
                    if (EasyAIFrame.controller.canClick2(setY, setX)) {
                        EasyAIFrame.controller.Putting(-color, setY, setX);
                    } else System.out.println("AI WRONG!!");
                    EasyAIFrame.controller.countScore();
                    repaint();
                } else if (EasyAIFrame.controller.canContinue(EasyAIFrame.controller.getCurrentPlayer()) && !EasyAIFrame.controller.canClick(row, col)) {
                    File f4 = new File("error.wav");
                    try {
                        url3 = f4.toURL();
                    } catch (
                            MalformedURLException e) {
                        e.printStackTrace();
                    }
                    ac3 = Applet.newAudioClip(url3);
                    ac3.play();
                    JOptionPane.showConfirmDialog(null, new JLabel("<html><h2><font color='blue'>this is an </font><font color='#cc22ff'>INVALID</font><font color='#cc22ff'>move.Here are the avalable steps.</font></h2></html>"), "ending~~~", JOptionPane.YES_NO_OPTION);
                    int color1 = EasyAIFrame.controller.getCurrentPlayer();
                    EasyAIFrame.controller.checkNextStep(color1);
                } else if (!EasyAIFrame.controller.canContinue(EasyAIFrame.controller.getCurrentPlayer()) && EasyAIFrame.controller.canContinue()) {
                    while (!EasyAIFrame.controller.canContinue(EasyAIFrame.controller.getCurrentPlayer()) && EasyAIFrame.controller.canContinue()) {
                        int[][] board_copy2 = new int[8][8];
                        for (int i = 0; i < 8; i++) {
                            for (int j = 0; j < 8; j++) {
                                board_copy2[j][i] = -ChessBoardPanel.getData()[i][j];
                            }
                        }//change color
                        AI ai = new AI();
                        int setX = ai.play(board_copy2, EasyAIFrame.controller.getCurrentPlayer(),2)[0];//
                        int setY = ai.play(board_copy2, EasyAIFrame.controller.getCurrentPlayer(),2)[1];//
                        System.out.println("aiX:" + setX + " aiY:" + setY);
                        EasyAIFrame.controller.Putting(-EasyAIFrame.controller.getCurrentPlayer(), setY, setX);
                        EasyAIFrame.controller.countScore();
                        repaint();
                    }

                }
                if (!EasyAIFrame.controller.canContinue()) {
                    isStart = false;
                    if (EasyAIFrame.controller.FindWinner() == 1)
                        JOptionPane.showMessageDialog(null, "<html><h2><font color='blue'>GAME ENDS!</font><font color='#cc22ff'> CONGRATULATIONS TO BLACK!</font></h2></html>");
                    else if (EasyAIFrame.controller.FindWinner() == -1)
                        JOptionPane.showMessageDialog(null,"<html><h2><font color='blue'>GAME ENDS!</font><font color='#cc22ff'> CONGRATULATIONS TO WHITE!</font></h2></html>");
                    else
                        JOptionPane.showMessageDialog(null, "<html><h2><font color='blue'>GAME ENDS!</font><font color='#cc22ff'> BUT THERE IS NO WINNER</font></h2></html>");
                }
            }
        } else if (MainFrame.AImode == 2) {
            if (isStart) {
                System.out.println("isstart");
                //找到接口了哈哈哈哈哈哈哈哈
                if (DifAIFrame.controller.canClick(row, col)) {
                    File f4 = new File("Put.wav");
                    try {
                        url3 = f4.toURL();
                    } catch (
                            MalformedURLException e) {
                        e.printStackTrace();
                    }
                    ac3 = Applet.newAudioClip(url3);
                    ac3.play();

                    DifAIFrame.controller.clearNextStep();
                    int color = DifAIFrame.controller.getCurrentPlayer();
                    DifAIFrame.controller.Putting(color, row, col);
                    DifAIFrame.controller.countScore();
                    repaint();
                    /**save one step*/
                    int[][] board_copy = new int[8][8];
                    for (int i = 0; i < 8; i++) {
                        for (int j = 0; j < 8; j++) {
                            board_copy[i][j] = ChessBoardPanel.getData()[i][j];
                        }
                    }
                    Step a = new Step(color, row, col, board_copy);
                    DifAIFrame.g.addStep(a);

                    int[][] board_copy2 = new int[8][8];
                    for (int i = 0; i < 8; i++) {
                        for (int j = 0; j < 8; j++) {
                            board_copy2[j][i] = -ChessBoardPanel.getData()[i][j];
                        }
                    }//change color
                    AI ai = new AI();
                    int setX = ai.play(board_copy2, color,6)[0];//
                    int setY = ai.play(board_copy2, color,6)[1];//
                    System.out.println("aiX:" + setY + " aiY:" + setX);
                    if (DifAIFrame.controller.canClick2(setY, setX)) {
                        DifAIFrame.controller.Putting(-color, setY, setX);
                    } else System.out.println("AI WRONG!!");
                    DifAIFrame.controller.countScore();
                    repaint();
                } else if (DifAIFrame.controller.canContinue(DifAIFrame.controller.getCurrentPlayer()) && !DifAIFrame.controller.canClick(row, col)) {
                    File f4 = new File("error.wav");
                    try {
                        url3 = f4.toURL();
                    } catch (
                            MalformedURLException e) {
                        e.printStackTrace();
                    }
                    ac3 = Applet.newAudioClip(url3);
                    ac3.play();
                    JOptionPane.showConfirmDialog(null, new JLabel("<html><h2><font color='blue'>this is an </font><font color='#cc22ff'>INVALID</font><font color='#cc22ff'>move.Here are the avalable steps.</font></h2></html>"), "ending~~~", JOptionPane.YES_NO_OPTION);
                    int color1 = DifAIFrame.controller.getCurrentPlayer();
                    DifAIFrame.controller.checkNextStep(color1);
                } else if (!DifAIFrame.controller.canContinue(DifAIFrame.controller.getCurrentPlayer()) && DifAIFrame.controller.canContinue()) {
                    while (!DifAIFrame.controller.canContinue(DifAIFrame.controller.getCurrentPlayer()) && DifAIFrame.controller.canContinue()) {
                        JOptionPane.showConfirmDialog(null, new JLabel("You dont have any VALID move.It's AI's turn."), "ending~~~", JOptionPane.YES_NO_OPTION);
                        int[][] board_copy2 = new int[8][8];
                        for (int i = 0; i < 8; i++) {
                            for (int j = 0; j < 8; j++) {
                                board_copy2[j][i] = -ChessBoardPanel.getData()[i][j];
                            }
                        }//change color
                        AI ai = new AI();
                        int[] answer = ai.play(board_copy2, DifAIFrame.controller.getCurrentPlayer(),6);
                        int setX = answer[0];//
                        int setY = answer[1];//
                        System.out.println("aiX:" + setX + " aiY:" + setY);
                        DifAIFrame.controller.Putting(-DifAIFrame.controller.getCurrentPlayer(), setY, setX);
                        DifAIFrame.controller.countScore();
                        DifAIFrame.controller.Putting(-DifAIFrame.controller.getCurrentPlayer(), setY, setX);
                        DifAIFrame.controller.countScore();
                    }
                }
                if (!DifAIFrame.controller.canContinue()) {
                    isStart = false;
                    if (DifAIFrame.controller.FindWinner() == 1)
                        JOptionPane.showMessageDialog(null, "<html><h2><font color='blue'>GAME ENDS!</font><font color='#cc22ff'> CONGRATULATIONS TO BLACK!</font></h2></html>");
                    else if (DifAIFrame.controller.FindWinner() == -1)
                        JOptionPane.showMessageDialog(null,"<html><h2><font color='blue'>GAME ENDS!</font><font color='#cc22ff'> CONGRATULATIONS TO WHITE!</font></h2></html>");
                    else
                        JOptionPane.showMessageDialog(null, "<html><h2><font color='blue'>GAME ENDS!</font><font color='#cc22ff'> BUT THERE IS NO WINNER</font></h2></html>");
                }
            }
        }
    }


    public ChessPiece getChessPiece() {
        return chessPiece;
    }

    public void setChessPiece(ChessPiece chessPiece) {
        this.chessPiece = chessPiece;;
    }


    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }


    @Override
    public void paintComponent(Graphics g) {
        super.printComponents(g);
        g.setColor(gridColor);

        g.fillRect(3, 3, this.getWidth() - 2, this.getHeight() - 2);

        if (chessPiece != null) {
//            System.out.println(chessPiece.getColor());
            if (chessPiece.getColor() == Color.BLACK) {
                ImageIcon icon = new ImageIcon("黑.JPG");
                Image img = icon.getImage();
                g.drawImage(img, 5, 5, icon.getIconWidth()/10-14,
                        icon.getIconHeight()/10-12 , icon.getImageObserver());
            } else if (chessPiece.getColor() == Color.WHITE) {
                ImageIcon icon = new ImageIcon("白.JPG");
                Image img = icon.getImage();
                g.drawImage(img, 5, 5, icon.getIconWidth()/10-14 ,
                        icon.getIconHeight()/10-12 , icon.getImageObserver());

            }else if(chessPiece == ChessPiece.PINK){
                g.setColor(chessPiece.getColor());
                g.fillOval((gridSize - chessSize) / 2, (gridSize - chessSize) / 2, chessSize, chessSize);
            }
        }
        repaint();
    }

}
