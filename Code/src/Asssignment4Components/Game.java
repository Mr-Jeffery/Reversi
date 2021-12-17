package Asssignment4Components;

import java.util.ArrayList;

public class Game {
    final private int gid;
    // Game ID. The game ID of each game is unique and is automatically generated by class Game. Starting from 1, each new game’s ID will increase by 1.
    private String name;
    // Game name.
    private static int gameCnt=1;
    // Initialized to 1, increased by 1 every time a Game object is created
    final private Player whitePlayer;
    // The white player object corresponding to this game.
    final private Player blackPlayer;
    // The black player object corresponding to this game.
    private ArrayList<Step> stepList = new ArrayList <>();
    // The list of steps.
    private int[][] board;


    public Game(String name, Player whitePlayer, Player blackPlayer){
        this.gid = Game.gameCnt++;
        this.name = name;
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.board = new int[8][8];
        for(int ix=0;ix<8;ix++){
            for(int iy=0;iy<8;iy++){
                this.board[ix][iy] = 0;
            }
        }
        this.board[3][3]=1;this.board[4][4]=1;//put black
        this.board[3][4]=-1;this.board[4][3]=-1;//place white
    }

    public Game(String name, Player whitePlayer, Player blackPlayer, int gid){
        this.gid = gid;
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
    }
// Constructor, automatically generate game ID and Game object according to the name provided by the user.
// Initialize white player, black player, stepList, and board.
// For a list with no elements in it, its size should be 0 and its reference should not be null.
// For a board that has not to be set:
// 	1. Its size should be 8*8.
//  2. board[3][3] and board[4][4] should be white(1). board[3][4] and board[4][3] should be black(-1)
//  3. Its reference should not be null.


    public int getGid(){
        return this.gid;
    }


    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name=name;
    }
    // Modify game name.


    public static int getGameCnt(){
        return gameCnt;
    }
    public Player getWhitePlayer(){
        return this.whitePlayer;
    }
    public Player getBlackPlayer(){
        return this.blackPlayer;
    }


    public ArrayList<Step> getStepList(){/**
     */
        return stepList;
    }
    public boolean checkStep(int sid){
        for (Step s : stepList ){
            if (s.getSid()==sid){
                return true;
            }
        }
        return false;
    }
    // If a step is not in the step list, return false.
// Otherwise, return true.
    public void addStep(Step step){
        /**
         */
//        if(checkStep(step.getSid())){
//            return false;
//        }else {
            stepList.add(step);
//            return true;
//        }
    }
    // If a step is in the step list (i.e. The sid is in the step list), return false.
    // Otherwise, add the step into the step list and return true.
    // You are not required to change the board in this method.


    public int[][] getBoard(){
        return this.board;}
    public void setBoard(int[][] board){
        for (int ix=0;ix<8;ix++){
            for (int iy=0;iy<8;iy++){
                this.board[ix][iy]=board[ix][iy];
            }
        }
    }
    // Change the game result
    // Instead of pass the reference of board, you should copy the value from the argument board into the board of Game.




    public String toString(){
        return  String.format("%s\n&%s\n",
                this.name,
                //this.getGid(),
                //this.getWhitePlayer(),
                //this.getBlackPlayer(),
                this.getStepList())
                .replace("]","")
                .replace("[","")
                .replace(",","");
    }
    // When print an object of this class, follow the format: "Game: %s, gid: %d, whitePlayerId: %d, blackPlayerId: %d, stepList: %s, board: %s"
    // 1. The stepList String should be in the format: "[sid: x, rowIndex: x, columnIndex: x, color: x, sid: x, rowIndex: x, columnIndex: x, color: x, ...]"
    // 2. The board string should be in the format: "[[x, x, x, x, x, x, x, x], [x, x, x, x, x, x, x, x], ...]"
    // 3. Without quotes
    /**public String toString(){
     return  String.format("%s\n%s\n%s\n%s\n&\n%s\n",
     this.getName(),
     this.getGid(),
     this.getWhitePlayer(),
     this.getBlackPlayer(),
     this.getStepList())
     .replace("]","")
     .replace("[","")
     .replace(",","");
     }*/



    public Player getWinner() {
        int black=0,white=0;
        for (int ix=0;ix<8;ix++){
            for (int iy=0;iy<8;iy++){
                if (board[ix][iy]==1){
                    black++;
                }else if(this.board[ix][iy]==-1){
                    white++;
                }
            }
        }
        if(black>white){
            return blackPlayer;
        }else {
            return whitePlayer;
        }
    }
}
