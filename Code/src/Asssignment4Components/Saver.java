package Asssignment4Components;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Saver{
    private Saver(){}
    public static void save(Game game){
        File file = new File(".\\"+game.getGid()+".txt");
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(".\\"+game.getName()+".txt"));
            out.write(String.valueOf(game.toString()));
            out.close();
            System.out.println("The Game has been saved!");
        } catch (IOException e) {
            System.out.println("The Game can't be saved!");
            e.printStackTrace();
        }

    }
}
