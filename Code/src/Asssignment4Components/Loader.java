package Asssignment4Components;

import jdk.jfr.internal.tool.Main;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.List;

import static com.sun.tools.internal.xjc.reader.Ring.add;

public class Loader {

    public static int m;
    private static String[] copy = new String[100];

    private Loader() {
    }

    public static Game load(String gamePath) {
        for (int i = 0; i < 100; i++) copy[i] = "";
        try {
            BufferedReader in = new BufferedReader(new FileReader(gamePath));
            //读取参数
            int temp;
            String input = "";
            while ((temp = in.read()) != -1) {
                input += (char) temp;
            }
            String[] s = input.split("&");
            Player p1 = new Player("Alice", "123");
            Player p2 = new Player("Bob", "123");
            Game game = new Game(s[0], p1, p2);
            String[] stepListInput = s[1].split("#");
            m = stepListInput.length;
            for (int i = 0; i < stepListInput.length; i++) copy[i] = stepListInput[i];
            if (MainFrame.AImode == 1) {
                String stepInput = stepListInput[stepListInput.length - 1];
                Scanner scanner = new Scanner(stepInput);
                int chess = scanner.nextInt();
                System.out.println("chess" + chess);
                int X = scanner.nextInt();
                System.out.println("x" + X);
                int Y = scanner.nextInt();
                System.out.println("y" + Y);
                int[][] board = new int[8][8];
                int bScore = 0;
                int wScore = 0;
                for (int ix = 0; ix < 8; ix++) {
                    for (int iy = 0; iy < 8; iy++) {
                        board[ix][iy] = scanner.nextInt();
                        if (board[ix][iy] == 1) bScore++;
                        else if (board[ix][iy] == -1) wScore++;
                    }
                }
                Step step = new Step(chess, X, Y, board);
                game.addStep(step);

                EasyAIFrame gameFrame = new EasyAIFrame(board, bScore, wScore, -chess, -chess);
                JPanel panel = new JPanel();
                JLabel img2 = new JLabel(new ImageIcon("CBP.png"));
                panel.add(img2);
                img2.setBounds(0, 0, 720, 720);
                img2.setVisible(true);
                panel.setBounds(0, 0, 720, 720);
                gameFrame.add(panel);
                gameFrame.setGame(game);
                game.addStep(step);
                gameFrame.setVisible(true);
                if (!EasyAIFrame.controller.canContinue(-chess)) {
                    if (EasyAIFrame.controller.canContinue())
                        EasyAIFrame.controller.swapPlayer();
                    else {
                        EasyAIFrame.controller.countScore();
                        if (EasyAIFrame.controller.FindWinner() == 1)
                            JOptionPane.showMessageDialog(null, "<html><h2><font color='blue'>GAME ENDS!</font><font color='#cc22ff'> CONGRATULATIONS TO BLACK!</font></h2></html>");
                        else if (EasyAIFrame.controller.FindWinner() == -1)
                            JOptionPane.showMessageDialog(null, "<html><h2><font color='blue'>GAME ENDS!</font><font color='#cc22ff'> CONGRATULATIONS TO WHITE!</font></h2></html>");
                        else
                            JOptionPane.showMessageDialog(null, "<html><h2><font color='blue'>GAME ENDS!</font><font color='#cc22ff'> BUT THERE IS NO WINNER</font></h2></html>");
                    }
                }

            } else if (MainFrame.AImode == 2) {
                String stepInput = stepListInput[stepListInput.length - 1];
                Scanner scanner = new Scanner(stepInput);
                int chess = scanner.nextInt();
                int X = scanner.nextInt();
                int Y = scanner.nextInt();
                int[][] board = new int[8][8];
                int bScore = 0;
                int wScore = 0;
                for (int ix = 0; ix < 8; ix++) {
                    for (int iy = 0; iy < 8; iy++) {
                        board[ix][iy] = scanner.nextInt();
                        if (board[ix][iy] == 1) bScore++;
                        else if (board[ix][iy] == -1) wScore++;
                    }
                }
                Step step = new Step(chess, X, Y, board);
                game.addStep(step);
                DifAIFrame gameFrame = new DifAIFrame(board, bScore, wScore, -chess, -chess);
                JPanel panel = new JPanel();
                JLabel img2 = new JLabel(new ImageIcon("CBP.png"));
                panel.add(img2);
                img2.setBounds(0, 0, 720, 720);
                img2.setVisible(true);
                panel.setBounds(0, 0, 720, 720);
                gameFrame.add(panel);
                gameFrame.setGame(game);
                game.addStep(step);
                gameFrame.setVisible(true);
                if (!DifAIFrame.controller.canContinue(-chess)) {
                    if (DifAIFrame.controller.canContinue())
                        DifAIFrame.controller.swapPlayer();
                    else {
                        DifAIFrame.controller.countScore();
                        if (DifAIFrame.controller.FindWinner() == 1)
                            JOptionPane.showMessageDialog(null, "<html><h2><font color='blue'>GAME ENDS!</font><font color='#cc22ff'> CONGRATULATIONS TO BLACK!</font></h2></html>");
                        else if (DifAIFrame.controller.FindWinner() == -1)
                            JOptionPane.showMessageDialog(null, "<html><h2><font color='blue'>GAME ENDS!</font><font color='#cc22ff'> CONGRATULATIONS TO WHITE!</font></h2></html>");
                        else
                            JOptionPane.showMessageDialog(null, "<html><h2><font color='blue'>GAME ENDS!</font><font color='#cc22ff'> BUT THERE IS NO WINNER</font></h2></html>");
                    }
                }
            }
//            } else {
//                String stepInput=stepListInput[stepListInput.length-1];
//                Scanner scanner = new Scanner(stepInput);
//                int chess = scanner.nextInt();
//                int X = scanner.nextInt();
//                int Y = scanner.nextInt();
//                int [][] board = new int[8][8];
//                int bScore=0;
//                int wScore=0;
//                for (int ix=0;ix<8;ix++) {
//                    for (int iy = 0; iy < 8; iy++) {
//                        board[ix][iy] = scanner.nextInt();
//                        if(board[ix][iy]==1)bScore++;
//                        else if(board[ix][iy]==-1)wScore++;
//                    }
//                }
//                Step step = new Step(chess,X,Y,board);
//                game.addStep(step);
//
//                GameFrame gameFrame=new GameFrame(board,bScore,wScore,-chess);
//                JPanel panel=new JPanel();
//                JLabel img2 =new JLabel(new ImageIcon("CBP.png"));
//                panel.add(img2);
//                img2.setBounds(0, 0, 720, 720);
//                img2.setVisible(true);
//                panel.setBounds(0, 0, 720, 720);
//                gameFrame.add(panel);
//                gameFrame.setGame(game);
//                game.addStep(step);
//
//                gameFrame.setVisible(true);
//                if(!GameFrame.controller.canContinue(-chess)) {
//                    if (GameFrame.controller.canContinue())
//                        GameFrame.controller.swapPlayer();
//                    else {
//                        GameFrame.controller.countScore();
//                        if (GameFrame.controller.FindWinner() == 1)
//                            JOptionPane.showMessageDialog(null, "<html><h2><font color='blue'>GAME ENDS!</font><font color='#cc22ff'> CONGRATULATIONS TO BLACK!</font></h2></html>");
//                        else if (GameFrame.controller.FindWinner() == -1)
//                            JOptionPane.showMessageDialog(null, "<html><h2><font color='blue'>GAME ENDS!</font><font color='#cc22ff'> CONGRATULATIONS TO WHITE!</font></h2></html>");
//                        else
//                            JOptionPane.showMessageDialog(null, "<html><h2><font color='blue'>GAME ENDS!</font><font color='#cc22ff'> BUT THERE IS NO WINNER</font></h2></html>");
//                    }
//                }
//
//            }
            return game;
        } catch (IOException e) {
            System.out.println("un");
            return null;
        } catch (NoSuchElementException e) {
            System.out.println("un2");
            return null;
        }
//        if (MainFrame.NORAMLmode == 1) {
//            String stepInput = copy[1];
//            Scanner scanner = new Scanner(stepInput);
//            int chess = scanner.nextInt();
//            System.out.println(chess + "yy");
//            int X = scanner.nextInt();
//            System.out.println(X + "yy");
//            int Y = scanner.nextInt();
//            System.out.println(Y + "yy");
//            int[][] board = new int[8][8];
//            int bScore = 0;
//            int wScore = 0;
//            for (int ix = 0; ix < 8; ix++) {
//                for (int iy = 0; iy < 8; iy++) {
//                    board[ix][iy] = scanner.nextInt();
//                    if (board[ix][iy] == 1) bScore++;
//                    else if (board[ix][iy] == -1) wScore++;
//                }
//            }
//            Step step = new Step(chess, X, Y, board);
//            game.addStep(step);
//
//            GameFrame gameFrame = new GameFrame(board, 2, 2, 1);
//            JPanel panel = new JPanel();
//            JLabel img2 = new JLabel(new ImageIcon("CBP.png"));
//            panel.add(img2);
//            img2.setBounds(0, 0, 720, 720);
//            img2.setVisible(true);
//            panel.setBounds(0, 0, 720, 720);
//            gameFrame.add(panel);
//            gameFrame.setGame(game);
//            game.addStep(step);
//            if (X > 0 && Y > 0) {
//                GameFrame.controller.Putting(chess, X, Y);
//            }
//            GameFrame.controller.countScore();
//            gameFrame.setVisible(true);
//            if (m < stepListInput.length - 1) m++;
//
//            if (!GameFrame.controller.canContinue(-chess)) {
//                if (GameFrame.controller.canContinue())
//                    GameFrame.controller.swapPlayer();
//                else {
//                    GameFrame.controller.countScore();
//                    if (GameFrame.controller.FindWinner() == 1)
//                        JOptionPane.showMessageDialog(null, "<html><h2><font color='blue'>GAME ENDS!</font><font color='#cc22ff'> CONGRATULATIONS TO BLACK!</font></h2></html>");
//                    else if (GameFrame.controller.FindWinner() == -1)
//                        JOptionPane.showMessageDialog(null, "<html><h2><font color='blue'>GAME ENDS!</font><font color='#cc22ff'> CONGRATULATIONS TO WHITE!</font></h2></html>");
//                    else
//                        JOptionPane.showMessageDialog(null, "<html><h2><font color='blue'>GAME ENDS!</font><font color='#cc22ff'> BUT THERE IS NO WINNER</font></h2></html>");
//                }
//            }
//        }
    }
}
