package controller;

import jdk.nashorn.internal.scripts.JO;
import model.ChessPiece;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GameController {

    private ChessBoardPanel gamePanel;
    private StatusPanel statusPanel;
    private ChessPiece currentPlayer;
    public int getCurrentPlayer()
    {
        if(currentPlayer==ChessPiece.BLACK)return 1;
        else return -1;
    }

    public GameController(ChessBoardPanel gamePanel, StatusPanel statusPanel) {
        this.gamePanel = gamePanel;
        this.statusPanel = statusPanel;
    }

    public void setCurrentPlayer(int i) {
        if(i==1)this.currentPlayer = ChessPiece.BLACK;
        else this.currentPlayer = ChessPiece.WHITE;
        if(MainFrame.AImode==0)
        statusPanel.setPlayerText(currentPlayer.name());
        else
        {
            statusPanel.setAIText(currentPlayer.name());
        }
    }

    public void swapPlayer() {
        int next;
        if(currentPlayer == ChessPiece.BLACK)next=-1;
        else next=1;
        if(MainFrame.CHEATmode==0)
        {
            if(gamePanel.canContinue()) {
                if (gamePanel.canContinue(next)) {
                    currentPlayer = (currentPlayer == ChessPiece.BLACK) ? ChessPiece.WHITE : ChessPiece.BLACK;
                    statusPanel.setPlayerText(currentPlayer.name());
                } else {
                    JOptionPane.showMessageDialog(null, "<html><h2><font color='blue'>WE CAN NOT CHANGE !</font><font color='#cc22ff'>HE DON'T HAVE A INVALID MOVE...</font></h2></html>");
                }
            }
            else if (!canContinue()) {
                if (FindWinner() == 1)
                    JOptionPane.showConfirmDialog(null, new JLabel("<html><h2><font color='blue'>GAME ENDS!</font><font color='#cc22ff'> CONGRATULATIONS TO BLACK!</font></h2></html>"), "ending~~~", JOptionPane.YES_NO_OPTION);
                else if (FindWinner() == -1)
                    JOptionPane.showConfirmDialog(null, new JLabel("<html><h2><font color='blue'>GAME ENDS!</font><font color='#cc22ff'> CONGRATULATIONS TO WHITE!</font></h2></html>"), "ending~~~", JOptionPane.YES_NO_OPTION);
                else
                    JOptionPane.showConfirmDialog(null, new JLabel("<html><h2><font color='blue'>GAME ENDS!</font><font color='#cc22ff'> BUT THERE IS NO WINNER</font></h2></html>"), "ending~~~", JOptionPane.YES_NO_OPTION);
            }
        }
        else
        {
            if(gamePanel.canContinue(1,1))
            {
                currentPlayer = (currentPlayer == ChessPiece.BLACK) ? ChessPiece.WHITE : ChessPiece.BLACK;
                statusPanel.setPlayerText(currentPlayer.name());
            }
        }
            statusPanel.setScoreText(gamePanel.getBlackScore(),gamePanel.getWhiteScore());
    }

    public void check()
    {
        int now;
        if(currentPlayer == ChessPiece.BLACK)now=1;
        else now=-1;
        if(!gamePanel.canContinue(now))
        {
            swapPlayer();
            JOptionPane.showConfirmDialog(null, new JLabel("<html><h2><font color='blue'>We don't have valid move now.</font><font color='#cc22ff'>we MUST change color.</font></h2></html>"), "ending~~~", JOptionPane.YES_NO_OPTION);
        }
    }
    public int FindWinner()
    {
        int s=0;
        if(gamePanel.getWhiteScore()<gamePanel.getBlackScore())s=1;
        else if(gamePanel.getWhiteScore()>gamePanel.getBlackScore())s=-1;
        return s;
    }

    public ChessBoardPanel getGamePanel() {
        return gamePanel;
    }


    public void setGamePanel(ChessBoardPanel gamePanel) {
        this.gamePanel = gamePanel;
    }


    public void readFileData(String fileName) {
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

    public void clear()
    {
        gamePanel.clear();
    }
    public void writeDataToFile(String fileName) {
    }

    public boolean canClick(int row, int col) {
        return gamePanel.canClickGrid(row, col, currentPlayer);
    }

    public boolean canClick2(int row, int col) {
        return gamePanel.canClickGrid2(row, col, currentPlayer);
    }
    public boolean canClick(int row, int col,int i) {
        return gamePanel.canClickGrid(row, col);
    }

    public void Putting(int color,int row, int col) {
        gamePanel.Put(color,row,col);
    }

    public void countScore()
    {
     gamePanel.countScore();
     statusPanel.setScoreText(gamePanel.getBlackScore(),gamePanel.getWhiteScore());
     statusPanel.repaint();
    }

    public boolean canContinue(int chess)
    {
        return gamePanel.canContinue(chess);
    }
     public boolean canContinue(int a,int b)
     {
         return gamePanel.canContinue(a,b);
     }

    public boolean canContinue()
    {
        return gamePanel.canContinue();
    }



    public void checkNextStep(int chess)
    {
        gamePanel.checkNextStep(chess);
    }

    public void clearNextStep()
    {
        gamePanel.clearNextStep();
    }
    public void UNDO(int[][]input,int cplayer)
    {
        int [][] board_copy = new int[8][8];
        gamePanel.blackScore=0;
        gamePanel.whiteScore=0;
        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++)
            {
                board_copy[i][j]=input[i][j];
            }
        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++)
            {
                if(board_copy[i][j]==1)
                {
                    gamePanel.getChessGrids(i,j).setChessPiece(ChessPiece.BLACK);
                    gamePanel.data[i][j]=1;
                    gamePanel.blackScore++;
                }
                else if(board_copy[i][j]==-1)
                {
                    gamePanel.getChessGrids(i,j).setChessPiece(ChessPiece.WHITE);
                    gamePanel.data[i][j]=-1;
                    gamePanel.whiteScore++;
                }
                else
                {
                    gamePanel.getChessGrids(i,j).setChessPiece(null);
                    gamePanel.data[i][j]=0;
                }
            }
        if(cplayer==1)currentPlayer=ChessPiece.BLACK;
        else currentPlayer=ChessPiece.WHITE;
        statusPanel.setPlayerText(currentPlayer.name());
        statusPanel.setScoreText(gamePanel.blackScore,gamePanel.whiteScore) ;
        gamePanel.repaint();
        statusPanel.repaint();
    }

}
