/**
package Asssignment4Components;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

class GameSystem {

    private static ArrayList<Player> playerList = new ArrayList <>();
    // The list of players
    private static ArrayList<Game> gameList= new ArrayList <>();
    // The list of games

    public GameSystem(){
        ArrayList<Player> playerList = new ArrayList <>();
        ArrayList<Game> gameList = new ArrayList<>();
    }
    // Constructor. Initialize playerList and gameList. For a list with no elements in it, its size should be 0 and its reference should not be null.

    public static void savePlayerList() throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try (ObjectOutputStream output = new ObjectOutputStream(buffer)) {
            for (Player player:playerList){
                output.writeObject(player);
            }
        }
    }

    public static void  loadPlayerList() throws IOException, ClassNotFoundException {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(".src"))) {
            Player p = (Player) input.readObject();
        }
    }

    public ArrayList<Game> getGameList(){
        return this.gameList;
    }
    public ArrayList<Player> getPlayerList(){
        return this.playerList;
    }
    public boolean checkPlayer(int pid){
        for (Player p : playerList ){
            if (p.getPid()==pid){
                return true;
            }
        }
        return false;
    }
    // If the player with pid is not in the player list, return false.
    // Otherwise, return true.
    public boolean checkGame(int gid){
        for (Game g : gameList ){
            if (g.getGid()==gid){
                return true;
            }
        }
        return false;
    }
    // If the game with gid is not in the game list, return false.
    // Otherwise, return true.
    public boolean addPlayer(Player player){
        if(checkPlayer(player.getPid())){
            return false;
        }else {
            playerList.add(player);
            return true;
        }
    }
    // If the player is in the player list (i.e. his/her pid is in the player list), return false.
    // Otherwise, add the player into the player list and return true.
    public boolean addGame(Game game){
        Player p1 = game.getBlackPlayer();
        Player p2 = game.getWhitePlayer();
        if(checkGame(game.getGid()))
        {
            return false;
        }
        else
        {
            if (playerList.isEmpty()){
                return false;
            }else {
                if (playerList.contains(p1)&&playerList.contains(p2)){
                    gameList.add(game);
                    return true;
                }
                return false;
            }
        }
    }



    // If the game is in the game list (i.e. its gid is in the game list), return false.
    // Else if the corresponding players of the game are not in playerList, return false.
    // Otherwise, add the game into the game list and return true.
    public ArrayList<Game> listPlayerGame(int pid){
        ArrayList<Game> gameListTemp = new ArrayList<>();
        if(!playerList.isEmpty()){
            for (Game g : gameList ){
                Player p1 = g.getBlackPlayer();
                Player p2 = g.getWhitePlayer();
                if (p1.getPid()==pid||p2.getPid()==pid){
                    gameListTemp.add(g);
                }

            }
        }
        return gameListTemp;
    }
    // Return an ArrayList<Game> of the player with pid, the order of the games is following the game order in the gameList. Skip games the player didn't participate in.
    public float calculatePlayerWinRate(int pid){
        float playedGameCnt = 0;
        float wonGameCnt = 0;
        if (!gameList.isEmpty()){
            for (Game g : gameList ){
                Player p1 = g.getBlackPlayer();
                Player p2 = g.getWhitePlayer();
                Player pw = g.getWinner();
                if (p1.getPid()==pid||p2.getPid()==pid){
                    playedGameCnt++;
                }
                if (pid==pw.getPid()){
                    wonGameCnt++;
                }
            }
            if(playedGameCnt!=0){
                return wonGameCnt/playedGameCnt;
            }
        }
        return  0 ;
    }
// Return the win rate of the player with pid. The win rate is the ratio of total wins to total games the player has participated in.
// If the player has not participated in any games, return 0.
}

 */