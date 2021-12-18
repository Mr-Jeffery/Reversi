package AboutUsers;

import Asssignment4Components.Player;

import java.io.*;
import java.util.List;

public class GameSystem {
    static Player computer = new Player("computer","computer");

    public static boolean logIn(String playerName,String password){
        Player player = null;
        try {
            File folder = new File(".//");
            File[] listOfFiles = folder.listFiles();
            for (File listOfFile : listOfFiles) {
                if (listOfFile.isFile()) {
                    if (listOfFile.getName()==String.format("%s.plr",playerName)) {
                        FileInputStream fileIn = new FileInputStream(listOfFile.getName());
                        ObjectInputStream in = new ObjectInputStream(fileIn);
                        player = (Player) in.readObject();
                        System.out.println("Deserializing");
                        System.out.printf("Name: %s\n", player.getName());
                        in.close();
                        fileIn.close();
                    }
                }
            }
        }catch (Exception e){
            System.out.println("Log in failed!");
            e.printStackTrace();
            return false;
        }
        return player!=null&&player.logIn(password);
    }

    public static boolean register(Player player){
        File folder = new File(".//");
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if(file.getName()==player.getName()+".plr"){
                return false;
            }
        }
        try {
            FileOutputStream fileOut = new FileOutputStream(String.format(".//%s.plr",player.getName()));
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(player);
            out.close();
            fileOut.close();
            System.out.printf(String.format("Serialized data of %s is saved.\n",player.getName()));
            return true;
        }catch(IOException i)
        {
            i.printStackTrace();
            return false;
        }
    }

}
