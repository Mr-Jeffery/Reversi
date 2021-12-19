package AboutUsers;

import Asssignment4Components.Player;

        import java.io.*;
        import java.util.ArrayList;
        import java.util.List;

public class PlayerLoader {
    public  List <Player>playerList = new ArrayList<>();
    public boolean PlayerLoader(String N1,String P1,String N2,String P2) {
        boolean canLoad1=false;
        boolean canLoad2=false;
        boolean canLoad=false;
        try {
            File folder = new File(".//");
            File[] listOfFiles = folder.listFiles();

            for (File listOfFile : listOfFiles) {
                if (listOfFile.isFile()) {
                    if (listOfFile.getName().endsWith(".plr")) {
                        FileInputStream fileIn = new FileInputStream(listOfFile.getName());
                        ObjectInputStream in = new ObjectInputStream(fileIn);
                        Player player = (Player) in.readObject();
                        if(player.getName().equals(N1) && player.getPassword().equals(P1))
                            canLoad1=true;
                        if(player.getName().equals(N2) && player.getPassword().equals(P2))
                            canLoad1=true;
                        playerList.add(player);
                        in.close();
                        fileIn.close();
                    }
                }
            }
        }catch (Exception e){
            System.out.println("player can't be loaded!");
            canLoad=false;
            e.printStackTrace();
        }
        if(canLoad1 && canLoad2)canLoad=true;
        return canLoad;
    }
   public  ArrayList<Player>GetPlayerName()
   {
       return (ArrayList<Player>) playerList;
   }
}
