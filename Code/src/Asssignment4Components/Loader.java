package Asssignment4Components;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Loader{
    private Loader(){}
    public static void load(Game game){
        try {
            BufferedReader in = new BufferedReader(new FileReader(".\\src\\"+game.getGid()+".txt"));
            //读取参数
            //load player
            //load game
            //Game game = new Game()
            in.close();
        }catch (IOException e) {
            System.out.println("The Game can't be loaded!");
        }
    }
}
