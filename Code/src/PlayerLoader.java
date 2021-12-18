import Asssignment4Components.Player;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerLoader {
    public static void main(String [] args) {
        List <Player>playerList = new ArrayList<>();
        try {
            File folder = new File(".//");
            File[] listOfFiles = folder.listFiles();

            for (File listOfFile : listOfFiles) {
                if (listOfFile.isFile()) {
                    if (listOfFile.getName().endsWith(".plr")) {
                        FileInputStream fileIn = new FileInputStream(listOfFile.getName());
                        ObjectInputStream in = new ObjectInputStream(fileIn);
                        Player player = (Player) in.readObject();
                        System.out.println("Deserializing");
                        System.out.printf("Name: %s\n", player.getName());
                        playerList.add(player);
                        in.close();
                        fileIn.close();
                    }
                }
            }
        }catch (Exception e){
            System.out.println("player can't be loaded!");
            e.printStackTrace();
        }
    }
}
