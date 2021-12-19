package components;

//import apple.laf.JRSUIConstants;
import AI.AI;
import Asssignment4Components.Step;
import controller.GameController;
import model.*;
import view.*;

import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;


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

                    File f4 = new File("Put.wav");
                    try {
                        url3 = f4.toURL();
                    } catch (
                            MalformedURLException e) {
                        e.printStackTrace();
                    }
                    ac3 = Applet.newAudioClip(url3);
                    ac3.play();

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
                    for (int k = 0; k < GameFrame.g.getStepList().size(); k++) {
                        for (int i = 0; i < 8; i++) {
                            for (int j = 0; j < 8; j++) {
                                System.out.printf("%d ", GameFrame.g.getStepList().get(k).getBoard()[i][j]);
                            }
                            System.out.println();
                        }
                        System.out.println();
                    }
                    System.out.println("habusdkgahgdvbkfucywgcdhjskgf");
                    GameFrame.controller.swapPlayer();
                    System.out.println("tried to swap!!!!!!!!!!!!!!!!!");
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
                        JOptionPane.showConfirmDialog(null, new JLabel("<html><h2><font color='blue'>GAME ENDS!</font><font color='#cc22ff'> CONGRATULATIONS TO BLACK!</font></h2></html>"), "ending~~~", JOptionPane.YES_NO_OPTION);
                    else if (GameFrame.controller.FindWinner() == -1)
                        JOptionPane.showConfirmDialog(null, new JLabel("<html><h2><font color='blue'>GAME ENDS!</font><font color='#cc22ff'> CONGRATULATIONS TO WHITE!</font></h2></html>"), "ending~~~", JOptionPane.YES_NO_OPTION);
                    else
                        JOptionPane.showConfirmDialog(null, new JLabel("<html><h2><font color='blue'>GAME ENDS!</font><font color='#cc22ff'> BUT THERE IS NO WINNER</font></h2></html>"), "ending~~~", JOptionPane.YES_NO_OPTION);
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

                    if (!CheatFrame.controller.canContinue()) {
                        isStart = false;
                        if (CheatFrame.controller.FindWinner() == 1)
                            JOptionPane.showConfirmDialog(null, new JLabel("<html><h2><font color='blue'>GAME ENDS!</font><font color='#cc22ff'> CONGRATULATIONS TO BLACK!</font></h2></html>"), "ending~~~", JOptionPane.YES_NO_OPTION);
                        else if (CheatFrame.controller.FindWinner() == -1)
                            JOptionPane.showConfirmDialog(null, new JLabel("<html><h2><font color='blue'>GAME ENDS!</font><font color='#cc22ff'> CONGRATULATIONS TO WHITE!</font></h2></html>"), "ending~~~", JOptionPane.YES_NO_OPTION);
                        else
                            JOptionPane.showConfirmDialog(null, new JLabel("<html><h2><font color='blue'>GAME ENDS!</font><font color='#cc22ff'> BUT THERE IS NO WINNER</font></h2></html>"), "ending~~~", JOptionPane.YES_NO_OPTION);
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
                    ai.setLayerTotal(2);
                    int[] set = ai.play(board_copy2, color);//
                    if (EasyAIFrame.controller.canClick2(set[1], set[0])) {
                        EasyAIFrame.controller.Putting(-color, set[1], set[0]);
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
                        ai.setLayerTotal(2);
                        int [] set = ai.play(board_copy2, EasyAIFrame.controller.getCurrentPlayer());
                        EasyAIFrame.controller.Putting(-EasyAIFrame.controller.getCurrentPlayer(), set[1], set[0]);
                        EasyAIFrame.controller.countScore();
                        repaint();
                    }

                }
                if (!EasyAIFrame.controller.canContinue()) {
                    isStart = false;
                    if (EasyAIFrame.controller.FindWinner() == 1)
                        JOptionPane.showConfirmDialog(null, new JLabel("<html><h2><font color='blue'>GAME ENDS!</font><font color='#cc22ff'> CONGRATULATIONS TO BLACK!</font></h2></html>"), "ending~~~", JOptionPane.YES_NO_OPTION);
                    else if (EasyAIFrame.controller.FindWinner() == -1)
                        JOptionPane.showConfirmDialog(null, new JLabel("<html><h2><font color='blue'>GAME ENDS!</font><font color='#cc22ff'> CONGRATULATIONS TO WHITE!</font></h2></html>"), "ending~~~", JOptionPane.YES_NO_OPTION);
                    else
                        JOptionPane.showConfirmDialog(null, new JLabel("<html><h2><font color='blue'>GAME ENDS!</font><font color='#cc22ff'> BUT THERE IS NO WINNER</font></h2></html>"), "ending~~~", JOptionPane.YES_NO_OPTION);
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
                    ai.setLayerTotal(6);
                    int []set = ai.play(board_copy2, color);
                    if (DifAIFrame.controller.canClick2(set[1], set[0])) {
                        DifAIFrame.controller.Putting(-color, set[1], set[0]);
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
                        int[][] board_copy2 = new int[8][8];
                        for (int i = 0; i < 8; i++) {
                            for (int j = 0; j < 8; j++) {
                                board_copy2[j][i] = -ChessBoardPanel.getData()[i][j];
                            }
                        }//change color
                        AI ai = new AI();
                        ai.setLayerTotal(6);
                        int setX = ai.play(board_copy2, DifAIFrame.controller.getCurrentPlayer())[0];//
                        int setY = ai.play(board_copy2, DifAIFrame.controller.getCurrentPlayer())[1];//
                        System.out.println("aiX:" + setX + " aiY:" + setY);
                        DifAIFrame.controller.Putting(-DifAIFrame.controller.getCurrentPlayer(), setY, setX);
                        DifAIFrame.controller.countScore();
                        repaint();
                    }

                }
                if (!DifAIFrame.controller.canContinue()) {
                    isStart = false;
                    if (DifAIFrame.controller.FindWinner() == 1)
                        JOptionPane.showConfirmDialog(null, new JLabel("<html><h2><font color='blue'>GAME ENDS!</font><font color='#cc22ff'> CONGRATULATIONS TO BLACK!</font></h2></html>"), "ending~~~", JOptionPane.YES_NO_OPTION);
                    else if (DifAIFrame.controller.FindWinner() == -1)
                        JOptionPane.showConfirmDialog(null, new JLabel("<html><h2><font color='blue'>GAME ENDS!</font><font color='#cc22ff'> CONGRATULATIONS TO WHITE!</font></h2></html>"), "ending~~~", JOptionPane.YES_NO_OPTION);
                    else
                        JOptionPane.showConfirmDialog(null, new JLabel("<html><h2><font color='blue'>GAME ENDS!</font><font color='#cc22ff'> BUT THERE IS NO WINNER</font></h2></html>"), "ending~~~", JOptionPane.YES_NO_OPTION);
                }
            }
        }
    }


        public ChessPiece getChessPiece() {
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
//        if (this.chessPiece != null && !setDelay) {
//            g.setColor(chessPiece.getColor());
//            g.fillOval((gridSize - chessSize) / 2, (gridSize - chessSize) / 2, chessSize, chessSize);
//        }
//        else if(this.chessPiece != null && setDelay)
//        {
//            g.setColor(chessPiece.getColor());
//            try {
//                TimeUnit.SECONDS.sleep(1);
//            } catch (InterruptedException ie) {
//                Thread.currentThread().interrupt();
//            }
//            g.fillOval((gridSize - chessSize) / 2, (gridSize - chessSize) / 2, chessSize, chessSize);
//        }
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
