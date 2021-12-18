import Asssignment4Components.Player;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerLoader {
    public static void main(String [] args) {
        List playerList = new ArrayList();
        try {
            File folder = new File(".//");
            File[] listOfFiles = folder.listFiles();

            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) {
                    if (listOfFiles[i].getName().endsWith(".plr")) {
                        Player player = null;
                        FileInputStream fileIn = new FileInputStream(listOfFiles[i].getName());
                        ObjectInputStream in = new ObjectInputStream(fileIn);
                        player = (Player) in.readObject();
                        System.out.println("Deserializing");
                        System.out.println(String.format("Name: %s pid: %d",player.getName(),player.getPid()));
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
