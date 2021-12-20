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

public class Loader2 {

    public static int m;
    private static String[] copy = new String[100];

    public Loader2() {}


    public static ArrayList<Step>loaded(String gamePath)
    {
        try {
            ArrayList<Step>ans=new ArrayList<Step>();
            BufferedReader in = new BufferedReader(new FileReader(gamePath));
            //读取参数
            int temp;
            String input = "";
            while ((temp = in.read()) != -1) {
                input += (char) temp;
            }
            String[] s = input.split("&");
            String[] stepListInput = s[1].split("#");
            m = stepListInput.length;
            for (int i = 1; i < m; i++)
            {
                String stepInput=stepListInput[i];
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
                ans.add(step);
            }
            return ans;
        } catch (IOException e) {
            return null;
        } catch (NoSuchElementException e) {
            return null;
        }
    }
    public static ArrayList<Integer> load(String gamePath) {
        try {
            ArrayList<Integer>ans=new ArrayList<Integer>();
            BufferedReader in = new BufferedReader(new FileReader(gamePath));
            //读取参数
            int temp;
            String input = "";
            while ((temp = in.read()) != -1) {
                input += (char) temp;
            }
            String[] s = input.split("&");
            String[] stepListInput = s[1].split("#");
            m = stepListInput.length;
            for (int i = 2; i < stepListInput.length; i++)
            {
                String stepInput=stepListInput[i];
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
                ans.add(chess);
                ans.add(X);
                ans.add(Y);
            }
             return ans;
        } catch (IOException e) {
            System.out.println("un");
            return null;
        } catch (NoSuchElementException e) {
            System.out.println("un2");
            return null;
        }

    }
}
