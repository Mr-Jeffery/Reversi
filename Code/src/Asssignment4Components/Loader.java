package Asssignment4Components;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Loader{
    private Loader(){}
    public static Game load(String gameName){
        try {
            BufferedReader in = new BufferedReader(new FileReader(".\\"+gameName+".txt"));
            //读取参数
            int temp;
            String input = "";
            while ((temp= in.read())!= -1){
                input += (char)temp;
            }
            String [] s =input.split("&");
            //load player
            Player p1 = new Player("Alice");
            Player p2 = new Player("Bob");
            //load game
            Game game = new Game(s[0],p1,p2);
            String [] stepListInput = s[1].split("#");
            for (int i = 1;i<stepListInput.length;i++){
                String stepInput=stepListInput[i];
                Scanner scanner = new Scanner(stepInput);
                int chess = scanner.nextInt();
                int X = scanner.nextInt();
                int Y = scanner.nextInt();
                Step step = new Step(chess,X,Y);
                int [][] board = new int[8][8];
                for (int ix=0;ix<8;ix++) {
                    for (int iy = 0; iy < 8; iy++) {
                        board[ix][iy] = scanner.nextInt();
                    }
                    step.setBoard(board);
                    game.addStep(step);
                }
            }
            in.close();
            return game;
        }catch (IOException e) {
            System.out.println("The Game can't be loaded!");
            return null;
        }
    }

    public static void loadStep(String steps){
        String [] stepListInput = steps.split("#%d");
        List<Step> stepList = new ArrayList<>();
        for (String stepInput : stepListInput){
            Scanner scanner = new Scanner(stepInput);
            int sid  = scanner.nextInt();
            int chess = scanner.nextInt();
            int X = scanner.nextInt();
            int Y = scanner.nextInt();
            Step step = new Step(chess,X,Y);
            int [][] board = new int[8][8];
            for (int ix=0;ix<8;ix++) {
                for (int iy = 0; iy < 8; iy++) {
                    board[iy][ix] = scanner.nextInt();
                }
                step.setBoard(board);
                stepList.add(step);
            }
        }
    }


}
