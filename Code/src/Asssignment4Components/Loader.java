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

public class Loader{
    private Loader(){}
    public static Game load(String gamePath){
        try {
            BufferedReader in = new BufferedReader(new FileReader(gamePath));
            //读取参数
            int temp;
            String input = "";
            while ((temp= in.read())!= -1){
                input += (char)temp;
            }
            String [] s =input.split("&");
            Player p1 = new Player("Alice","123");
            Player p2 = new Player("Bob","123");
            Game game = new Game(s[0],p1,p2);
            String [] stepListInput = s[1].split("#");
            if(MainFrame.AImode==1)
            {
                String stepInput=stepListInput[stepListInput.length-1];
                Scanner scanner = new Scanner(stepInput);
                int chess = scanner.nextInt();
                int X = scanner.nextInt();
                int Y = scanner.nextInt();
                int [][] board = new int[8][8];
                int bScore=0;
                int wScore=0;
                for (int ix=0;ix<8;ix++) {
                    for (int iy = 0; iy < 8; iy++) {
                        board[ix][iy] = scanner.nextInt();
                        if(board[ix][iy]==1)bScore++;
                        else if(board[ix][iy]==-1)wScore++;
                    }
                }
                Step step = new Step(chess,X,Y,board);
                game.addStep(step);

                EasyAIFrame gameFrame=new EasyAIFrame(board,bScore,wScore,-chess,-chess);
                JPanel panel=new JPanel();
                JLabel img2 =new JLabel(new ImageIcon("CBP.png"));
                panel.add(img2);
                img2.setBounds(0, 0, 720, 720);
                img2.setVisible(true);
                panel.setBounds(0, 0, 720, 720);
                gameFrame.add(panel);
                gameFrame.setGame(game);
                game.addStep(step);
                gameFrame.setVisible(true);
                try{
                    add(gameFrame);
                }catch(Exception e1)
                {

                }
            }
            else if(MainFrame.AImode==2)
            {
                String stepInput=stepListInput[stepListInput.length-1];
                Scanner scanner = new Scanner(stepInput);
                int chess = scanner.nextInt();
                int X = scanner.nextInt();
                int Y = scanner.nextInt();
                int [][] board = new int[8][8];
                int bScore=0;
                int wScore=0;
                for (int ix=0;ix<8;ix++) {
                    for (int iy = 0; iy < 8; iy++) {
                        board[ix][iy] = scanner.nextInt();
                        if(board[ix][iy]==1)bScore++;
                        else if(board[ix][iy]==-1)wScore++;
                    }
                }
                Step step = new Step(chess,X,Y,board);
                game.addStep(step);
                DifAIFrame gameFrame=new DifAIFrame(board,bScore,wScore,-chess,-chess);
                JPanel panel=new JPanel();
                JLabel img2 =new JLabel(new ImageIcon("CBP.png"));
                panel.add(img2);
                img2.setBounds(0, 0, 720, 720);
                img2.setVisible(true);
                panel.setBounds(0, 0, 720, 720);
                gameFrame.add(panel);
                gameFrame.setGame(game);
                game.addStep(step);
                gameFrame.setVisible(true);
            }
            else
            {
                String stepInput=stepListInput[stepListInput.length-1];
                Scanner scanner = new Scanner(stepInput);
                int chess = scanner.nextInt();
                int X = scanner.nextInt();
                int Y = scanner.nextInt();
                int [][] board = new int[8][8];
                int bScore=0;
                int wScore=0;
                for (int ix=0;ix<8;ix++) {
                    for (int iy = 0; iy < 8; iy++) {
                        board[ix][iy] = scanner.nextInt();
                        if(board[ix][iy]==1)bScore++;
                        else if(board[ix][iy]==-1)wScore++;
                    }
                }
                Step step = new Step(chess,X,Y,board);
                game.addStep(step);

                GameFrame gameFrame=new GameFrame(board,bScore,wScore,-chess);
                JPanel panel=new JPanel();
                JLabel img2 =new JLabel(new ImageIcon("CBP.png"));
                panel.add(img2);
                img2.setBounds(0, 0, 720, 720);
                img2.setVisible(true);
                panel.setBounds(0, 0, 720, 720);
                gameFrame.add(panel);
                gameFrame.setGame(game);
                game.addStep(step);
                gameFrame.setVisible(true);
                try{
                    add(gameFrame);
                }catch(Exception e1)
                {

                }
                    }
            return game;
        }catch (IOException e) {
            System.out.println("The Game can't be loaded!");
            return null;
        }catch (NoSuchElementException e){
            System.out.println("The Game file has been modified, can't be loaded!");
            return null;
        }
    }

}
