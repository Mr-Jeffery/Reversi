package view;

import AI.*;
import Asssignment4Components.*;

import javax.xml.soap.Node;
import java.util.Arrays;

public class testAiPlayer {
    public static void main(String[] args){
        Runner runner = new Runner();
        Player p1 = new Player("Alice");
        Player p2 = new Player("Bob");
        Game game = new Game("Test",p1,p2);
        runner.setBoard(game.getBoard());
        Step step1 = new Step(-1,3,2);
        runner.put(-1,3,2);
        step1.setBoard(runner.getBoard());
        game.addStep(step1);
        Step step2 = new Step(1,2,4);
        runner.put(1,2,4);
        step2.setBoard(runner.getBoard());
        game.addStep(step2);
        AI ai = new AI();
        ai.setLayerTotal(1);
        int [][] board = step2.getBoard();
        int[] result = ai.play(board,1);
        for(int r : result){
            System.out.println(r);
        }
    }
}
