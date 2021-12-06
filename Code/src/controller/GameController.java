package controller;

import model.ChessPiece;
import view.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class GameController {


    private ChessBoardPanel gamePanel;
    private StatusPanel statusPanel;
    private endGameStatus endGameStatus;
    private ChessPiece currentPlayer;

    public GameController(ChessBoardPanel gamePanel, StatusPanel statusPanel) {
        this.gamePanel = gamePanel;
        this.statusPanel = statusPanel;
        this.currentPlayer = ChessPiece.BLACK;
    }

    public void swapPlayer() {
        int next;
        if(currentPlayer == ChessPiece.BLACK)next=-1;
        else next=1;
        if(gamePanel.canContinue(next))
        {
            currentPlayer = (currentPlayer == ChessPiece.BLACK) ? ChessPiece.WHITE : ChessPiece.BLACK;
            statusPanel.setPlayerText(currentPlayer.name());
        }
            statusPanel.setScoreText(gamePanel.getBlackScore(),gamePanel.getWhiteScore());
    }

    public String FindWinner()
    {
        String s;
        if(gamePanel.getWhiteScore()<gamePanel.getBlackScore())s="Black";
        else if(gamePanel.getWhiteScore()>gamePanel.getBlackScore())s="White";
        else s="NO";
        return s;
    }

    public ChessPiece getCurrentPlayer() {
        return currentPlayer;
    }

    public ChessBoardPanel getGamePanel() {
        return gamePanel;
    }


    public void setGamePanel(ChessBoardPanel gamePanel) {
        this.gamePanel = gamePanel;
    }


    public void readFileData(String fileName) {
        //todo: read date from file
        List<String> fileData = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                fileData.add(line);
            }
            fileData.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeDataToFile(String fileName) {
        //todo: write data into file
    }

    public boolean canClick(int row, int col) {
        return gamePanel.canClickGrid(row, col, currentPlayer);
    }

    public void Putting(int color,int row, int col)
    {
        gamePanel.Put(color,row,col);
    }

    public void countScore()
    {
     gamePanel.countScore();
    }

    public boolean canContinue(int chess)
    {
        return gamePanel.canContinue(chess);
    }
    public boolean canContinue()
    {
        return gamePanel.canContinue();
    }
}
