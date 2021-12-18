package Asssignment4Components;

import java.io.Serializable;
import java.util.List;

public class Player implements Serializable {
    private int pid;
    private static int playerCnt=1;
    private String name;

    public Player(String name){
        this.pid=playerCnt++;
        this.name=name;
        //static++的原理也太神必了罢
        //写成下面这样就摁报错
        // playerCnt++;
        //this.pid=playerCnt;
        //this.name=name;
    }

    public int getPid(){
        return this.pid;
    }

    public String getName(){
        return this.name;
    }

    public static int getPlayerCnt(){
        return playerCnt;
    }

    public void setName(String newName){
        this.name=newName;
    }

    @Override
    public String toString(){
        return this.name+String.valueOf(this.pid);
    }

}
