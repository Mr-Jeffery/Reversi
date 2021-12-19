package AboutUsers;

import Asssignment4Components.Player;

        import java.io.*;
        import java.util.ArrayList;
        import java.util.List;

public class PlayerSaver {
    public PlayerSaver(String N1,String P1,String N2,String P2) {
        Player player1 = new Player(N1,P1);
        Player player2 = new Player(N2,P2);
        List<Player> playerList = new ArrayList<>();
        playerList.add(player1);
        playerList.add(player2);
        for (Player p :playerList){
            try {
                FileOutputStream fileOut = new FileOutputStream(String.format(".//%s.plr",p.getName()));
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(p);
                out.close();
                fileOut.close();
                System.out.printf(String.format("Serialized data of %s is saved.\n",p.getName()));
            }catch(IOException i)
            {
                i.printStackTrace();
            }
        }
    }
}