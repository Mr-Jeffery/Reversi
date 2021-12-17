package Asssignment4Components;

import java.util.Arrays;

public class Step {
    private int sid;
    private int rowIndex;
    private int columnIndex;
    private int color;
    private static int stepCnt=1;
    private int[][] board;/**
     */

    public String getBoardString(){
        return Arrays.deepToString(this.getBoard())
                .replace("]"," \n")
                .replace("["," ")
                .replace(","," ");
    }

    public Step(int color,int rowIndex, int columnIndex ,int[][]board){
        this.sid = stepCnt++;
        this.color = color;
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
        this.board=board;
    }

    public int getSid(){
        return this.sid;
    }


    public int getColor(){
        return this.color;
    }
    public void setColor(int color){
        this.color = color;
    }


    public int getRowIndex(){
        return this.rowIndex;
    }
    public void setRowIndex(int rowIndex){
        this.rowIndex = rowIndex;
    }


    public int getColumnIndex(){
        return this.columnIndex;
    }
    public void setColumnIndex(int columnIndex){
        this.columnIndex = columnIndex;
    }


    public static int getStepCnt(){
        return stepCnt;
    }// Get the value of StepCnt

    @Override
    public String toString(){

        return String.format("# %d %d %d\n%s",
                this.color,
                this.rowIndex,
                this.columnIndex,
                this.getBoardString());
    }
    public int[][] getBoard(){
        return this.board;
    }
}
