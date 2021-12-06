package Asssignment4Components;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Saver{
    private Saver(){}
    public static void canSave(Game game){
        File file = new File(".\\"+game.getGid()+".txt");
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(".\\"+game.getName()+".txt"));
            out.write(String.valueOf(game.toString()));
            out.close();
        } catch (IOException e) {
            System.out.println("Can't create new file!");
            e.printStackTrace();
        }
        if (!file.exists()) {
            if(true/**设置点击按钮确认储存*/){Saver.save(game);}
        } else {
            System.out.println("The Game had been saved,do you want to overwrite it?");
            if(true/**设置点击按钮确认覆盖*/){
                try {
                    BufferedWriter out = new BufferedWriter(new FileWriter(".\\"+game.getName()+".txt"));
                    out.write(String.valueOf(game.toString()));
                    out.close();
                } catch (IOException e) {
                    System.out.println("Can't create new file!");
                    e.printStackTrace();
                }
            }
        }

    }

    public static void save(Game game){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("..\\"+game.getName()+".txt"));
            String temp = String.valueOf(game.toString());
            writer.write(temp);
            writer.close();
        } catch (IOException e) {
            System.out.println("The Game can't be saved!");
            e.printStackTrace();

        }
        System.out.println("The Game has been saved!");
    }


}
