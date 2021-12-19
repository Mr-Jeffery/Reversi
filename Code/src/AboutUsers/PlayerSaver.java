package AboutUsers;
import Asssignment4Components.Player;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerSaver {
    public PlayerSaver(String n1,String p1) {
        Player p = new Player(n1,p1);
            try {
                FileOutputStream fileOut = new FileOutputStream(String.format(".//%s.plr",p.getName()+String.valueOf(p.getPassword())));
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(p);
                out.close();
                fileOut.close();
                System.out.printf(String.format("Serialized data of %s is saved.\n",p.getName()+String.valueOf(p.getPassword())));
            }catch(IOException i)
            {
                i.printStackTrace();
            }
        }
    }
