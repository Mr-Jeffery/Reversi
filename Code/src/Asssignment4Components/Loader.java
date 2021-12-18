package Asssignment4Components;

import view.GameFrame;
import view.MainFrame;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

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
            //load player
            Player p1 = new Player("Alice","123");
            Player p2 = new Player("Bob","123");
            //load game
            Game game = new Game(s[0],p1,p2);
            String [] stepListInput = s[1].split("#");
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
                    GameFrame gameFrame=new GameFrame(board,bScore,wScore,-chess);
                    gameFrame.setGame(game);
                    game.addStep(step);

            in.close();
            return game;
        }catch (IOException e) {
            System.out.println("The Game can't be loaded!");
            return null;
        }catch (NoSuchElementException e){
            System.out.println("The Game file has been modified, can't be loaded!");
            return null;
        }
    }


//    public static void loadStep(String steps){
//        String [] stepListInput = steps.split("#%d");
//        List<Step> stepList = new ArrayList<>();
//        for (String stepInput : stepListInput){
//            Scanner scanner = new Scanner(stepInput);
//            int sid  = scanner.nextInt();
//            int chess = scanner.nextInt();
//            int X = scanner.nextInt();
//            int Y = scanner.nextInt();
//            Step step = new Step(chess,X,Y);
//            int [][] board = new int[8][8];
//            for (int ix=0;ix<8;ix++) {
//                for (int iy = 0; iy < 8; iy++) {
//                    board[iy][ix] = scanner.nextInt();
//                }
//                step.setBoard(board);
//                stepList.add(step);
//            }
//        }
//    }

}
