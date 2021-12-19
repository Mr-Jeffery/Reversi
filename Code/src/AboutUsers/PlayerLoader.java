package AboutUsers;
import Asssignment4Components.Player;

import java.io.*;
import javafx.application.Application;
import java.util.ArrayList;
import java.util.List;

public class PlayerLoader {
    public PlayerLoader()
    {

    }
    public boolean PlayerLoader(String n1, String n2){

        boolean canload=false;

        try {
            File folder = new File("/Users/ronghaixi/IdeaProjects/Reversi/");
            File[] listOfFiles = folder.listFiles();

            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) {
                    if (listOfFiles[i].getName().endsWith(".plr")) {
                        Player player = null;
                        FileInputStream fileIn = new FileInputStream(listOfFiles[i].getName());
                        ObjectInputStream in = new ObjectInputStream(fileIn);
                        player = (Player) in.readObject();
                        System.out.println("Deserializing");
                        System.out.println(String.format("Name: %s",player.getName()+player.getPassword()));
                        if((n1+n2).equals(player.getName()+player.getPassword()))
                        canload=true;
                        in.close();
                        fileIn.close();
                    }
                }
            }
        }catch (Exception e){
            System.out.println("player can't be loaded!");
            e.printStackTrace();
        }
        return canload;
    }
}

