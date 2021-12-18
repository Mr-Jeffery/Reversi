package Asssignment4Components;

import java.io.Serializable;

public class Player implements Serializable {
    private String name;
    private String password;

    public Player(String name,String password){
        this.name=name;
        this.password=password;
    }

    public boolean logIn(String password){
        return password==this.password;
    }

    public String getName(){
        return this.name;
    }

    @Override
    public String toString(){
        return this.name;
    }

}
